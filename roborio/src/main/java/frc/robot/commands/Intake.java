package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends CommandBase {
    private final IntakeSubsystem intake;


    public Intake(IntakeSubsystem subsystem) {
        intake = subsystem;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.startIntake();
    }

    @Override
    public void execute() {
        intake.stopIntake();
    }
}