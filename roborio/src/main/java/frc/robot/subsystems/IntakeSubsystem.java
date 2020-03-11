package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Hardware;
// TODO: User should have full control over the spinning of the intake wheels. The operator should be able to 

public class IntakeSubsystem extends SubsystemBase {

    public IntakeSubsystem() {
    }
    public void setIntakeSpeed(double speed) {
        Hardware.intake.set(speed);
    }

    public void stopIntake() {
        Hardware.intake.set(0);
    }
}