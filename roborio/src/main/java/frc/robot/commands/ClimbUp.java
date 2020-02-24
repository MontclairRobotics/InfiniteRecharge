package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimbUp extends CommandBase {

    private final ClimberSubsystem climber;

    public ClimbUp(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.climbUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}