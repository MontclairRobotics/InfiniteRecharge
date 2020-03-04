package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDrive extends CommandBase {
    private final DriveSubsystem drive;
    private final double speed, rotation, startTime;

    public AutoDrive(DriveSubsystem subsystem, double speed, double rotation, double time) {
        drive = subsystem;
        this.speed = speed;
        this.rotation = rotation;
        startTime = System.currentTimeMillis();
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        drive.arcadeDrive(speed, rotation);
    }

    @Override 
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    @Override 
    public boolean isFinished() {
        return System.currentTimeMillis() >= startTime + time;
    }
}