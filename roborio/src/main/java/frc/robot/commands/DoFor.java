package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class DoFor extends CommandBase {

    private final Command command;
    private final double timeToWait;
    private final double startTime;

    public DoFor(Command command, double timeToWaitMillis) {
        this.command = command;
        this.timeToWait = timeToWaitMillis;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void initialize() {
        CommandScheduler.getInstance().schedule(command);
    }

    @Override
    public boolean isFinished() {
        return timeToWait >= startTime - System.currentTimeMillis();
    }

    @Override
    public void end(boolean interrupt) {
        CommandScheduler.getInstance().cancel(command);
    }

}