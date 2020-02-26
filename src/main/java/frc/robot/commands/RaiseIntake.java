package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

import static frc.robot.RobotContainer.intakeSubsystem;

public class RaiseIntake extends CommandBase {

    @Override
    public void execute() {
        intakeSubsystem.setDeploySpeed(-0.8);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setIntakeSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return intakeSubsystem.getLimitSwitch();
    }
}
