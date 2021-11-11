package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveStraight;

import static frc.robot.RobotContainer.driveSubsystem;

import javax.naming.InitialContext;

public class AutoDrive extends SequentialCommandGroup {

    // Test Mode that will drive a distance of 1 foot
    public AutoDrive(double timeSeconds) {
        addCommands(
            new CommandBase() 
            {
                private long startMillis;

                @Override
                public void initialize() 
                {
                    startMillis = System.currentTimeMillis();
                }
                
                @Override
                public void execute() 
                {
                    driveSubsystem.arcadeDriveDirect(0.5, 0);
                }

                @Override
                public boolean isFinished() 
                {
                    return (System.currentTimeMillis() - startMillis) > (long)(timeSeconds * 1000);
                }
            },
            new CommandBase() 
            {
                private long startMillis;

                @Override
                public void initialize() 
                {
                    startMillis = System.currentTimeMillis();
                }

                @Override
                public void execute() 
                {
                    driveSubsystem.arcadeDriveDirect(0, 0);
                }

                @Override
                public boolean isFinished() 
                {
                    return (System.currentTimeMillis() - startMillis) > 500;
                }
            }
        );
    }
}
