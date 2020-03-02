package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.utils.Constants;
import frc.robot.utils.Hardware;

public class IntakeSubsystem extends SubsystemBase {
    private SpeedControllerGroup intakeM;
    private SpeedControllerGroup intakeL;
    private SpeedControllerGroup intakeR;

    public IntakeSubsystem() {
        intakeM = new SpeedControllerGroup(Hardware.intake);
        intakeL = new SpeedControllerGroup(Hardware.intakeLeft);
        intakeR = new SpeedControllerGroup(Hardware.intakeRight);
    }

    public void startIntake(boolean in, boolean it) {
        if (in) {
            intakeL.set(1);            
            intakeR.set(1);
        }
        if (it) {
            intakeM.set(1);
        } else {
            intakeL.set(0);
            intakeR.set(0);
            intakeM.set(0);
        }

    }


    @Override
    public void periodic() {

    }
}