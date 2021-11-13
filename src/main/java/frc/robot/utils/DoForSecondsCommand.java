package frc.robot.utils;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DoForSecondsCommand extends WhileCommand
{
    private long startTimeMillis;
    private long targetTimeLength;
    
    @Override
    public void initialize() 
    {
        startTimeMillis = System.currentTimeMillis();
    }

    public DoForSecondsCommand(Runnable runnable, double time, SubsystemBase... requirements)
    {
        super(
            runnable,
            (WhileCommand inst) -> 
            {
                var instR = (DoForSecondsCommand)inst;
                return System.currentTimeMillis() - instR.startTimeMillis < instR.targetTimeLength;
            },
            requirements
        );

        this.targetTimeLength = (long)(time * 1000);
    }
}
