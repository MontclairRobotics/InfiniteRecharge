package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.utils.Constants;
import frc.robot.utils.Controllers;
import frc.robot.utils.Constants.IntakeConstants;


// TODO: Shouldn't this be split up into two different commands? One to control the movement of the intake arms
// TODO: And another one to control the spinning of the intake
public class Intake extends CommandBase {
    private final IntakeSubsystem intake;


    public Intake(IntakeSubsystem subsystem) {
        intake = subsystem;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setIntakeSpeed(IntakeConstants.kIntakeSpeed);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        intake.stopIntake();
    }
}