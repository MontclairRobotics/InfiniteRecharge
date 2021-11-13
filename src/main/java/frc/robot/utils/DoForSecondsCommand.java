package frc.robot.utils;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DoForSecondsCommand extends WhileCommand
{
    private long startTimeMillis;
    private long targetTimeLength;

    public DoForSecondsCommand(Runnable runnable, double time, SubsystemBase... requirements)
    {
        super(
            runnable,
            (WhileCommand inst) -> 
            {
                var instR = (DoForSecondsCommand)inst;
                return System.currentTimeMillis() - instR.startTimeMillis > instR.targetTimeLength;
            },
            requirements
        );

        this.startTimeMillis = System.currentTimeMillis();
        this.targetTimeLength = (long)(time * 1000);
    }
}
