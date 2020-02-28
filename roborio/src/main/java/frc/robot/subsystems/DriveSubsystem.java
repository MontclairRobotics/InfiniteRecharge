package frc.robot.subsystems;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Hardware;

public class DriveSubsystem extends SubsystemBase{
    private SpeedControllerGroup leftMotors;
    private SpeedControllerGroup rightMotors;
    private final DifferentialDrive driveTrain;



    public DriveSubsystem(){
        leftMotors = new SpeedControllerGroup(Hardware.DT_FL, Hardware.DT_BL);
        rightMotors = new SpeedControllerGroup(Hardware.DT_FR, Hardware.DT_BR);
        driveTrain = new DifferentialDrive(leftMotors, rightMotors);
    }
    public void arDrive(double forward, double rotation, boolean in){
        if(in){
            driveTrain.arcadeDrive(-forward, -rotation);
        }
        else{
            driveTrain.arcadeDrive(forward, rotation);
        }
    }
}