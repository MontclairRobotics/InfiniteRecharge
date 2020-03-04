package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForward extends CommandBase{

    private final DriveSubsystem driveSubsystem;
    private final double startTime;

    public DriveForward(DriveSubsystem subsystem) {
        driveSubsystem = subsystem;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void initialize() {
        driveSubsystem.arcadeDrive(1, 0);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime >= 5000; // TODO: Pass in as a parameter
    }

}