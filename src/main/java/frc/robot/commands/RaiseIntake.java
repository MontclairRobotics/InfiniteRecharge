package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.RobotContainer.intakeSubsystem;

public class RaiseIntake extends CommandBase {

    @Override
    public void execute() {
        intakeSubsystem.setIntakeArmSpeed(-0.8);
        intakeSubsystem.setIntakeWheelSpeed(1);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setIntakeArmSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return intakeSubsystem.getUpperLimit();
    }
}
