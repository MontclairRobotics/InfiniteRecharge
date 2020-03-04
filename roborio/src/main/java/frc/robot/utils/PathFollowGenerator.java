/*
package frc.robot.utils;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoDrive;
import frc.robot.subsystems.DriveSubsystem;

public class PathFollowGenerator {
    public static SequentialCommandGroup generate(DriveSubsystem driveSub, double linearSpeed, double angularSpeed, PathPoint2D... points) {
        ArrayList<SequentialCommandGroup> commands = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (i <= points.length)
                break;
            commands.add(
                new AutoDrive(driveSub);
            );
        }
        return new SequentialCommandGroup( (Command[])commands.toArray() );
    }
}
*/