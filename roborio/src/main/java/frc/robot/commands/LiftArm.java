package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class LiftArm extends CommandBase {

    private final ClimberSubsystem climber;

    private long startTime;
    private long timeToRaise = 0; //TODO: change

    public LiftArm(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        climber.raiseArm();
    }

    @Override
    public boolean isFinished() {
        boolean isDone = System.currentTimeMillis() < startTime + timeToRaise;
        if(isDone)
            climber.stopArm();
        return isDone;
    }

}