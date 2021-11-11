package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.DriveStraight;


import static frc.robot.RobotContainer.shooterSubsystem;
import static frc.robot.RobotContainer.transportSubsystem;

public class ShootSequence extends SequentialCommandGroup {

    public ShootSequence(double timeSeconds){
        addCommands(
                new AutoDrive(timeSeconds), // Drive into the wall
                
                new InstantCommand(()-> shooterSubsystem.setSpeed(1)),
                new WaitUntilCommand(()-> (shooterSubsystem.getRPM()>1000)),
                new InstantCommand(() -> transportSubsystem.setTransportSpeed(1)),
                new edu.wpi.first.wpilibj2.command.WaitCommand(1)
        );
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        transportSubsystem.setTransportSpeed(0);
        shooterSubsystem.setSpeed(0);
    }

}
