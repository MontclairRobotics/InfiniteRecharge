package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.LogMessage;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.utils.Input;
import frc.robot.utils.Profile;
import frc.robot.utils.Utils;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {

    public static final DriveState kStartDriveState = DriveState.EASED;
    public static final DriveState kAltDriveState = DriveState.SIMPLE;

    private final CANSparkMax frontLeftMotor = new CANSparkMax(kFrontLeftMotorPort, kBrushless);
    private final CANSparkMax frontRightMotor = new CANSparkMax(kFrontRightMotorPort, kBrushless);
    private final CANSparkMax backLeftMotor = new CANSparkMax(kBackLeftMotorPort, kBrushless);
    private final CANSparkMax backRightMotor = new CANSparkMax(kBackRightMotorPort, kBrushless);

    private final SpeedControllerGroup leftMotors =
            new SpeedControllerGroup(frontLeftMotor, backLeftMotor);

    private final SpeedControllerGroup rightMotors =
            new SpeedControllerGroup(frontRightMotor, backRightMotor);

    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

    private final CANEncoder leftEncoder =
            new CANEncoder(frontLeftMotor);

    private final CANEncoder rightEncoder =
            new CANEncoder(frontRightMotor);

    private final AHRS navx = new AHRS(SPI.Port.kMXP);

    private Profile fwdProfile;
    private Profile rotProfile;

    private double previousFwd = 0;
    private double previousRot = Constants.DriveConstants.kTurnCompensate;

    private long previousTime;

    private DriveState currentState;

    private boolean acceptingInput = false;

    // Construction
    public DriveSubsystem() 
    {
        leftEncoder.setPositionConversionFactor(kEncoderDistancePerPulse);
        rightEncoder.setPositionConversionFactor(kEncoderDistancePerPulse);

        differentialDrive.setRightSideInverted(true);
        differentialDrive.setMaxOutput(0.75);

        previousTime = System.currentTimeMillis();
    }

    public void init(Profile fwdProfile, Profile rotProfile)
    {
        this.fwdProfile = fwdProfile;
        this.rotProfile = rotProfile;
        this.currentState = kStartDriveState;
    }

    public void exec()
    {
        if(acceptingInput)
        {
            var cont = RobotContainer.driverController;
            arcadeDrive(cont.getY(Hand.kLeft),
                        cont.getX(Hand.kRight));
        }

        SmartDashboard.putString("Drive Mode", currentState.name());
    }

    public void arcadeDrive(double fwd, double rot) 
    {
        //System.out.println("Rot In: " + rot);
        var currentTime = System.currentTimeMillis();

        if(currentState == DriveState.EASED)
        {
            fwd = fwdProfile.profile(fwd, previousFwd, currentTime - previousTime);
            rot = rotProfile.profile(rot, previousRot, currentTime - previousTime);
        }

        //TODO: UNCOMMENT
        arcadeDriveDirect(fwd, rot);
        previousFwd = fwd;
        previousRot = rot;
        previousTime = currentTime;
 
        //TODO: REMOVE
        //System.out.println(fwd);
        //System.out.println("Rot Out: " + rot);
    }

    public void arcadeDriveDirect(double fwd, double rot)
    {
        // Account for turn compensation
        rot = rot * Constants.DriveConstants.kTurnFactor;
        if(Math.abs(fwd) >= 0.1)
        {
            rot += Constants.DriveConstants.kTurnCompensate;
        }
        rot = Utils.clamp(rot, -1, 1);

        differentialDrive.arcadeDrive(fwd, rot);
    }

    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public double getAverageDistance() {
        return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
    }

    public double getAverageEncoderSpeed(){
        return (leftEncoder.getVelocity() + rightEncoder.getVelocity() / 2.0) / 3973.2;
    }

    public CANEncoder getLeftEncoder() {
        return leftEncoder;
    }

    public CANEncoder getRightEncoder() {
        return rightEncoder;
    }

    public double getHeading(){
        return navx.getYaw();
    }

    public void resetNavx(){
        navx.reset();
    }

    public void setMaxOutput(double maxOutput) 
    {
        differentialDrive.setMaxOutput(maxOutput);
    }

    public void setAcceptingInput(boolean value) 
    {
        acceptingInput = value;
    }

    public void setCurrentDriveState(DriveState state)
    {
        currentState = state;
    }
}
