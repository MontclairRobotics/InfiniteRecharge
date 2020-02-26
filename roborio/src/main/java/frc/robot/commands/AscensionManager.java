package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ClimberSubsystem;

public class AscensionManager extends CommandBase {

    private final ClimberSubsystem climber;
    private final LiftArm liftArm;
    private final Joystick controller = null; //TODO: change
    private final int buttonId = 0;

    private boolean wasPressed = false;
    private boolean hasBeenActivated = false;

    public AscensionManager(ClimberSubsystem climber, LiftArm liftArm) {
        this.climber = climber;
        this.liftArm = liftArm;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        hasBeenActivated = false;
    }

    @Override
    public void execute() {
        boolean isPressed = controller.getRawButton(buttonId);

        if(isPressed && !hasBeenActivated) 
            CommandScheduler.getInstance().schedule(liftArm);
        if(isPressed && !wasPressed) 
            hasBeenActivated = true;
        if(isPressed && hasBeenActivated)
            climber.climbUp();
        if(!isPressed) 
            climber.climbStop();

    }

}