package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimbDown extends CommandBase {

    private final ClimberSubsystem climber;

    public ClimbDown(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.climbDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}