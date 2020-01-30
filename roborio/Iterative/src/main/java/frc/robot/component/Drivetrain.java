package frc.robot.component;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.Controls;
import frc.robot.utils.RobotComponent;
import frc.robot.utils.Utils;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class Drivetrain implements RobotComponent{

    private DifferentialDrive differentialDrive;

    private CANSparkMax frontLeft;
    private CANSparkMax frontRight;
    private CANSparkMax backLeft;
    private CANSparkMax backRight;

    private SpeedControllerGroup right;
    private SpeedControllerGroup left;

    private CANEncoder leftEncoder;
    private CANEncoder rightEncoder;

    /**
     * Calculated from original (13/5) ticks per inch which after PID testing, kept going 36 inches when targeting 12
     * so therefore it has been updated to reflect said testing
     *
     * Note: Current val drives 2.5 feet, possible PID tuning error or just need a better val
     */
    private final double TICKS_PER_INCH = (13/5)*12/36;

    private AHRS navx;

    private PIDController gyroTurnController;
    private PIDController distanceController;
    private PIDController powerCellTurnController;
    private PIDController fuelPortTurnController;


    enum KnownResetPositions{
        START_LEFT(new Pose2d(0,0, new Rotation2d(0))),
        START_RIGHT(new Pose2d(0,0, new Rotation2d(0))),
        START_MID(new Pose2d(0,0, new Rotation2d(0)));

        //TODO: Add More i.e. color wheel and shooting position

        Pose2d pose2d;
        private KnownResetPositions(Pose2d pose2d){
            this.pose2d = pose2d;
        }
    }

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    public Drivetrain(){
        frontLeft = new CANSparkMax(3, MotorType.kBrushless);
        frontRight = new CANSparkMax(2, MotorType.kBrushless);
        backLeft = new CANSparkMax(4, MotorType.kBrushless);
        backRight = new CANSparkMax(1, MotorType.kBrushless);

        left = new SpeedControllerGroup(frontLeft, backLeft);
        right = new SpeedControllerGroup(frontRight, backRight);

        leftEncoder = new CANEncoder(frontLeft);
        rightEncoder = new CANEncoder(frontRight);

        leftEncoder.setPositionConversionFactor(TICKS_PER_INCH);
        rightEncoder.setPositionConversionFactor(TICKS_PER_INCH);

        differentialDrive = new DifferentialDrive(left, right);

        try {
            navx = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
        }

        gyroTurnController = new PIDController(0.05, 0, 0.007);
        gyroTurnController.setTolerance(2);
        gyroTurnController.enableContinuousInput(-180, 180);

        distanceController = new PIDController(0.1, 0, 10);
        distanceController.setTolerance(1); // TODO: Tune

        powerCellTurnController = new PIDController(0.05, 0, 0.007); //TODO : Tune
        powerCellTurnController.setTolerance(2); //TODO: Tune
        powerCellTurnController.enableContinuousInput(-180, 180); //TODO: Tune

        fuelPortTurnController = new PIDController(0.05, 0, 0.007); //TODO : Tune
        fuelPortTurnController.setTolerance(2); //TODO: Tune
        fuelPortTurnController.enableContinuousInput(-180, 180); //TODO: Tune

        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(navx.getYaw()));
        resetOdometry(new Pose2d(0,0,new Rotation2d(0)));
    }

    @Override
    public void teleopPeriodic() {
        double rotation;
        if(Controls.driver.getStickButton(GenericHID.Hand.kLeft) || Utils.threshold(Math.abs(Controls.driver.getY(GenericHID.Hand.kLeft)),0.3,0.7)){
            gyroTurnController.setSetpoint(0);
            rotation = gyroTurnController.calculate(navx.getYaw());
        } else if(Controls.driver.getPOV()!= -1){
            switch(Controls.driver.getPOV()){
                case 0:
                    gyroTurnController.setSetpoint(0);
                    rotation = gyroTurnController.calculate(navx.getYaw());
                    break;

                case 90:
                    gyroTurnController.setSetpoint(90);
                    rotation = gyroTurnController.calculate(navx.getYaw());
                    break;

                case 180:
                    gyroTurnController.setSetpoint(180);
                    rotation = gyroTurnController.calculate(navx.getYaw());
                    break;

                case 270:
                    gyroTurnController.setSetpoint(270);
                    rotation = gyroTurnController.calculate(navx.getYaw());
                    break;

                default:
                    rotation = Controls.driver.getX(GenericHID.Hand.kRight);
            }

        } else if(SmartDashboard.getBoolean("Power Cell Found",false) && Controls.driver.getBumper(GenericHID.Hand.kLeft)){
            powerCellTurnController.setSetpoint(0); //TODO: Tune
            rotation = powerCellTurnController.calculate(SmartDashboard.getNumber("Power Cell X", 0)); // TODO: Tune
        } else if(SmartDashboard.getBoolean("Fuel Port Found",false) && Controls.driver.getBumper(GenericHID.Hand.kLeft)){
            fuelPortTurnController.setSetpoint(0); //TODO: Tune
            rotation = fuelPortTurnController.calculate(SmartDashboard.getNumber("Fuel Port X", 0)); // TODO: Tune
        } else {
            navx.reset();
            rotation = Controls.driver.getX(GenericHID.Hand.kRight);
        }

        differentialDrive.arcadeDrive(Controls.driver.getY(GenericHID.Hand.kLeft),
                rotation);
    }

    @Override
    public void robotPeriodic() {
        m_odometry.update(Rotation2d.fromDegrees(navx.getYaw()), leftEncoder.getPosition(),
                rightEncoder.getPosition());

        SmartDashboard.putNumber("Odometry X", m_odometry.getPoseMeters().getTranslation().getX());
        SmartDashboard.putNumber("Odometry Y", m_odometry.getPoseMeters().getTranslation().getY());
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getPosition());
    }

    //TODO: idk if trajectory output is position or velocity
    public void splineDrive(){
        Waypoint[] points = new Waypoint[] {
                new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
                new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
                new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
        };

        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
        Trajectory trajectory = Pathfinder.generate(points, config);

        TankModifier modifier = new TankModifier(trajectory).modify(0.5);
        EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
        EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());

        //TODO: Check Ticks per revolution, and wheel diameter (meters), and check upscaling
        left.configureEncoder((int)leftEncoder.getPosition()*100, 100, 0.15);

        //TODO: tune kv (4th parameter)
        left.configurePIDVA(1.0, 0.0, 0.0, 1 / 5, 0);

        double l = left.calculate((int) leftEncoder.getPosition()*100);
        double r = right.calculate((int)rightEncoder.getPosition()*100);

        double desired_heading = Pathfinder.r2d(left.getHeading());

        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - navx.getYaw());
        angleDifference = angleDifference % 360.0;
        if (Math.abs(angleDifference) > 180.0) {
            angleDifference = (angleDifference > 0) ? angleDifference - 360 : angleDifference + 360;
        }

        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        differentialDrive.arcadeDrive(l,turn);
    }

    /**
     * Test Mode: Turn and Drive PID control on polar vector component inputs, tests autoDrive() method for autonomous
     */
    public void testAutoDrive(){
        if(Controls.driver.getAButton()){
            autoDrive(12,0);
        }else{
            differentialDrive.arcadeDrive(0,0);
            resetEncoders();
            SmartDashboard.putBoolean("Test Mode", false);
        }
    }

    public void autoDrive(double distance){
        autoDrive(distance, 0);
    }
    
    public boolean autoDrive(double distance, double angle){
        gyroTurnController.setSetpoint(angle%180);
        if(gyroTurnController.atSetpoint()){
            leftEncoder.setPosition(0);
            rightEncoder.setPosition(0);
            distanceController.setSetpoint(distance/TICKS_PER_INCH);
            if(distanceController.atSetpoint()){
                differentialDrive.arcadeDrive(distanceController.calculate((leftEncoder.getPosition()+rightEncoder.getPosition())/2),
                        gyroTurnController.calculate(navx.getYaw()));
                return false;
            }else{
                return true;
            }
        }else{
            differentialDrive.arcadeDrive(0, gyroTurnController.calculate(navx.getYaw()));
            return false;
        }

    }

    private void resetEncoders(){
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    private void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, Rotation2d.fromDegrees(navx.getYaw()));
    }


}