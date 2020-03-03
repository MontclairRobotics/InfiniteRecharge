package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Drive extends CommandBase {
    private final DriveSubsystem drive;
    private final Joystick controller;
    
    public Drive(DriveSubsystem subsystem, Joystick controller){
        drive = subsystem;
        this.controller = controller;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        drive.setMaxSpeed(0.5);
    }

    @Override
    public void execute(){
        drive.arcadeDrive(controller.getRawAxis(4), controller.getRawAxis(5));
    }
}