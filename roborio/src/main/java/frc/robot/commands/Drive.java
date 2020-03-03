package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Drive extends CommandBase {
    private final DriveSubsystem drive;
    private final double forward;
    private final double rotation;
    
    public Drive(DriveSubsystem subsystem, double forward, double rotation){
        drive = subsystem;
        this.forward = forward;
        this.rotation = rotation;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        drive.setMaxSpeed(0.5);
    }


    @Override
    public void execute(){
        drive.arcadeDrive(forward, rotation);
    }
}