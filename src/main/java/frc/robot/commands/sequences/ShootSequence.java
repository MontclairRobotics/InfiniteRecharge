package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj2.command.*;

import static frc.robot.RobotContainer.shooterSubsystem;
import static frc.robot.RobotContainer.transportSubsystem;

public class ShootSequence extends SequentialCommandGroup 
{
    public ShootSequence(double timeSeconds)
    {
        addCommands(
            new AutoDrive(timeSeconds), // Drive into the wall
            
            new InstantCommand(() -> shooterSubsystem.setSpeed(0.9)),
            new WaitUntilCommand(() -> (shooterSubsystem.getRPM()>3000)),
            new InstantCommand(() -> transportSubsystem.setTransportSpeed(1)),
            new WaitCommand(2),
            new InstantCommand(() -> 
            {
                transportSubsystem.setTransportSpeed(0);
                shooterSubsystem.setSpeed(0);
            })
        );

        addRequirements(shooterSubsystem, transportSubsystem);
    }
}
