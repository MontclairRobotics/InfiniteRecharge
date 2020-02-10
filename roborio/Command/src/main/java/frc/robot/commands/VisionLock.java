package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Network;

import static frc.robot.Constants.DriveConstants.*;

public class VisionLock extends PIDCommand {
    public VisionLock(double fwd, double visionX, DriveSubsystem drive, Network network) {
        super(
                new PIDController(kTurnP, kTurnI, kTurnD),  
                network:: getPortX,
                visionX,
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
