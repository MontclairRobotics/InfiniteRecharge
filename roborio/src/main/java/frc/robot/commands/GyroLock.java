package frc.robot.commands;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.Constants.PIDConstants;

public class GyroLock extends CommandBase {
    private final DriveSubsystem drive;
    private final PIDController controller = new PIDController(PIDConstants.kP_Gyro, PIDConstants.kI_Gyro, PIDConstants.kD_Gyro);
    private double speed;
    private double rotation;

    public GyroLock(DriveSubsystem subsystem) {
        drive = subsystem;
        addRequirements(drive);
    }

    public void initialize() {
        controller.setTolerance(0.5);
        controller.setSetpoint(0);
        controller.enableContinuousInput(-180, 180);
        drive.resetNavX();
    }
    
    public void execute() {
        rotation = controller.calculate(drive.getYaw());
        drive.arcadeDrive(speed, rotation, false);
    }
    public void end(boolean inverted) {
        controller.reset();
    }
}