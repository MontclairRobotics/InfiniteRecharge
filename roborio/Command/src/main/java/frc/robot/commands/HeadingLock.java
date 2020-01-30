package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

import static frc.robot.Constants.DriveConstants.*;

public class HeadingLock extends PIDCommand {

    public HeadingLock(double fwd, double tgtAngleDeg, DriveSubsystem drive) {
        super(
                new PIDController(kTurnP, kTurnI, kTurnD),
                drive::getHeading,
                tgtAngleDeg,
                output -> drive.arcadeDrive(fwd, output),
                drive);

        getController().enableContinuousInput(-180, 180);
        getController().setTolerance(kTurnToleranceDeg, kTurnRateToleranceDegPerS);
    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
