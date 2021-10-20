package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveStraight;

public class AutoDrive extends SequentialCommandGroup {

    // Test Mode that will drive a distance of 5 "units"
    public AutoDrive() {
        addCommands(
                //new LowerIntake(),
                new DriveStraight(1)
        );
    }
}
