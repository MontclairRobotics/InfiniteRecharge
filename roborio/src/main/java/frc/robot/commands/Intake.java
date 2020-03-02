package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends CommandBase {
    private final IntakeSubsystem m_intakeL;
    private final IntakeSubsystem m_intakeR;
    private final IntakeSubsystem m_intakeM;


    public Intake(IntakeSubsystem subsystem, IntakeSubsystem intakeL, IntakeSubsystem intakeR) {
        m_intakeM = subsystem;
        m_intakeL = intakeL;
        m_intakeR = intakeR;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        m_intakeM.startIntake(true, true);
    }
}