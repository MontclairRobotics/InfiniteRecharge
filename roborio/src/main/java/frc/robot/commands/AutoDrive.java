package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDrive extends CommandBase {
    private final DriveSubsystem drive;
    private final double startTime;
    private double time;

    /**
     * 
     * @param subsystem - the drive subsystem that will be used by AutoDrive
     * @param time - time the robot will drive forwards for (in ms)
     */
    public AutoDrive(DriveSubsystem subsystem, double time) {
        drive = subsystem;
        time = time;
        startTime = System.currentTimeMillis();
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        //drive.arcadeDrive(speed, rotation);
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