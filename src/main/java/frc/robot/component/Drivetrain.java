package frc.robot.component;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class Drivetrain{

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

    private PIDController turnController;
    private PIDController distanceController;

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

        turnController = new PIDController(0.05, 0, 0.007);
        turnController.setTolerance(2);
        turnController.enableContinuousInput(-180, 180);

        distanceController = new PIDController(0.1, 0, 10);
        distanceController.setTolerance(1); // TODO: Tune

        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(navx.getYaw()));
        resetOdometry(new Pose2d(0,0,new Rotation2d(0)));
    }

    public void teleopDrive() {
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getPosition());
        double rotation;
        if (Controls.driver.getRawButton(1) || (Math.abs(Controls.driver.getRawAxis(4))< 0.3 && Math.abs(Controls.driver.getRawAxis(1))>0.7)){
            turnController.setSetpoint(0);
            rotation = turnController.calculate(navx.getYaw());
        } else {
            navx.reset();
            rotation = Controls.driver.getRawAxis(4);
        }

        differentialDrive.arcadeDrive(Controls.driver.getRawAxis(1),
                rotation);
    }

    public void update(){
        m_odometry.update(Rotation2d.fromDegrees(navx.getYaw()), leftEncoder.getPosition(),
                rightEncoder.getPosition());
        SmartDashboard.putNumber("Odometry X", m_odometry.getPoseMeters().getTranslation().getX());
        SmartDashboard.putNumber("Odometry X", m_odometry.getPoseMeters().getTranslation().getY());
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
     * Test Mode: driving a certain amount of distance
     */
    public void testPIDDrive(){
        double input;
        if(Controls.driver.getRawButton(2)){
            distanceController.setSetpoint(12*TICKS_PER_INCH);
            SmartDashboard.putNumber("Distance Controller Output", distanceController.calculate((Math.abs(leftEncoder.getPosition())+rightEncoder.getPosition())/2));
            differentialDrive.arcadeDrive(-distanceController.calculate((Math.abs(leftEncoder.getPosition())+rightEncoder.getPosition())/2),0);
            SmartDashboard.putNumber("Approx Inch Travled", leftEncoder.getPosition()/TICKS_PER_INCH );
            SmartDashboard.putBoolean("Test Mode", true);
        }else{
            differentialDrive.arcadeDrive(0,0);
            resetEncoders();
            SmartDashboard.putBoolean("Test Mode", false);
        }
    }

    /**
     * Test Mode: Turn and Drive PID control on polar vector component inputs, tests autoDrive() method for autonomous
     */
    public void testAutoDrive(){
        if(Controls.driver.getRawButton(2)){
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
        turnController.setSetpoint(angle%180);
        if(turnController.atSetpoint()){
            leftEncoder.setPosition(0);
            rightEncoder.setPosition(0);
            distanceController.setSetpoint(distance/TICKS_PER_INCH);
            if(distanceController.atSetpoint()){
                differentialDrive.arcadeDrive(distanceController.calculate((leftEncoder.getPosition()+rightEncoder.getPosition())/2),
                turnController.calculate(navx.getYaw()));
                return false;
            }else{
                return true;
            }
        }else{
            differentialDrive.arcadeDrive(0, turnController.calculate(navx.getYaw()));
            return false;
        }
        
    
        
    }

    public void resetEncoders(){
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, Rotation2d.fromDegrees(navx.getYaw()));
    }


}