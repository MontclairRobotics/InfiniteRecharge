package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class LiftArm extends CommandBase {

    private final ClimberSubsystem climber;

    public LiftArm(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.raiseArm();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        climber.stopArm();
    }

}