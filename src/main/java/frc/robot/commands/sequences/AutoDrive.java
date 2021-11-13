package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveStraight;
import frc.robot.utils.DoForSecondsCommand;

import static frc.robot.RobotContainer.driveSubsystem;

public class AutoDrive extends SequentialCommandGroup {

    // Test Mode that will drive a distance of 1 foot
    public AutoDrive(double timeSeconds) 
    {
        addCommands(
            new DoForSecondsCommand(() -> driveSubsystem.arcadeDriveDirect(0.5, 0), timeSeconds, driveSubsystem),
            new DoForSecondsCommand(() -> driveSubsystem.arcadeDriveDirect(0,0),    0.1,         driveSubsystem)
        );

        addRequirements(driveSubsystem);
    }
}
