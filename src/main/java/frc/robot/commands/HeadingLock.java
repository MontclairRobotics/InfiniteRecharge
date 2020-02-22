package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.RobotContainer.driveSubsystem;
import static frc.robot.RobotContainer.driverController;

public class HeadingLock extends CommandBase {

    private final PIDController turn;

    public HeadingLock(double tgt) {
        turn = new PIDController(kTurnP, kTurnI, kTurnD);
        turn.setSetpoint(tgt);
        turn.enableContinuousInput(-180, 180);
        turn.setTolerance(kTurnToleranceDeg, kTurnRateToleranceDegPerS);
    }

    @Override
    public void execute() {
        driveSubsystem.arcadeDrive(driverController.getY(GenericHID.Hand.kLeft),
                turn.calculate(driveSubsystem.getHeading()));
    }

    @Override
    public boolean isFinished() {
        return turn.atSetpoint();
    }
}
