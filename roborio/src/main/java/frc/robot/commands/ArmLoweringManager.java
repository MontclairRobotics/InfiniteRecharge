package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ClimberSubsystem;

public class ArmLoweringManager extends CommandBase {

    private final ClimberSubsystem climber;
    private final Joystick controller = null; //TODO: change
    private final int buttonId = 0;
    private final LowerArm lowerArm;

    private boolean wasPressed = false;

    public ArmLoweringManager(ClimberSubsystem climber, LowerArm lowerArm) {
        this.climber = climber;
        this.lowerArm = lowerArm;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        
        boolean isPressed = controller.getRawButton(buttonId);

        if(isPressed && !wasPressed)
            CommandScheduler.getInstance().schedule(lowerArm);
        
        wasPressed = isPressed;

    }

}