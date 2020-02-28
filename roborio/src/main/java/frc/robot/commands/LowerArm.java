package frc.robot.commands;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class LowerArm extends CommandBase {

    private final ClimberSubsystem climber;

    public LowerArm(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.lowerArm();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        climber.stopArm();
    }

    @Override
    public boolean isFinished() {
        return climber.getFullyLimited();
    }

}