package frc.robot.utils;

import java.util.function.Function;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WhileCommand extends CommandBase
{
    private Runnable runnable;
    private Function<WhileCommand, Boolean> whileThis;

    public WhileCommand(Runnable runnable, Function<WhileCommand, Boolean> whileThis, SubsystemBase... requirements)
    {
        this.runnable = runnable;
        this.whileThis = whileThis;

        addRequirements(requirements);
    }

    @Override
    public boolean isFinished() 
    {
        return !whileThis.apply(this);
    }

    @Override
    public void execute() 
    {
        runnable.run();
    }
}
