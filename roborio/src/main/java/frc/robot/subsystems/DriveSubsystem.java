package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Hardware;
import edu.wpi.first.wpilibj.SPI;


public class DriveSubsystem extends SubsystemBase{
    private SpeedControllerGroup leftMotors;
    private SpeedControllerGroup rightMotors;
    private final DifferentialDrive driveTrain;

    private final AHRS navx = new AHRS(SPI.Port.kMXP);

    private boolean inverted;

    public DriveSubsystem(){
        leftMotors = new SpeedControllerGroup(Hardware.DT_FL, Hardware.DT_BL);
        rightMotors = new SpeedControllerGroup(Hardware.DT_FR, Hardware.DT_BR);
        driveTrain = new DifferentialDrive(leftMotors, rightMotors);

        inverted = false;
    }
    public void arcadeDrive(double forward, double rotation){
        if(inverted){
            driveTrain.arcadeDrive(-forward, -rotation);
        }
        else{
            driveTrain.arcadeDrive(forward, rotation);
        }
    }
    public void setMaxSpeed(double speed) {
        driveTrain.setMaxOutput(speed);
    }
    public void resetNavX() {
        navx.reset();
    }
    
    public double getYaw() {
        return navx.getYaw();
    }
    public void invert() {
        inverted = !inverted;
    }
    
}