package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Hardware;

public class IntakeSubsystem extends SubsystemBase {

    public IntakeSubsystem() {
    }

    public void startIntake() {
        Hardware.intake.set(Constants.IntakeConstants.kIntakeSpeed);
    }

    public void setArmSpeed(double speed) {
        Hardware.intakeLeft.set(speed);
        Hardware.intakeRight.set(speed);
    }

    public void stopIntake() {
        Hardware.intake.set(0);
    }

    public void stopArm() {
        setArmSpeed(0);
    }

}