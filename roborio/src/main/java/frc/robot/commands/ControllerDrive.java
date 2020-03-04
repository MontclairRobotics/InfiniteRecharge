package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ControllerDrive extends CommandBase {
    private final DriveSubsystem drive;
    private final Joystick controller;
    
    public ControllerDrive(DriveSubsystem subsystem, Joystick controller){
        drive = subsystem;
        this.controller = controller;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        drive.setMaxSpeed(0.5); // TODO: Limit Deceleration to prevent robot from tipping over
                                // TODO: Limit should be declared in the constants class
    }

    @Override
    public void execute(){
        drive.arcadeDrive(controller.getRawAxis(4), controller.getRawAxis(5));
    }
}