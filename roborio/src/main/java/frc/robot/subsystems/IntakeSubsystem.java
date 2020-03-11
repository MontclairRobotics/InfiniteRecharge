package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Hardware;
// TODO: User should have full control over the spinning of the intake wheels. The operator should be able to 
// TODO: slow the intaking speed down and run the intake in reverse. Meanwhile the rotation of the intake should 
// TODO: only have two positions, up an down, which are controlled by the limit switches. 
public class IntakeSubsystem extends SubsystemBase {

    public IntakeSubsystem() {
    }
    public void setIntakeSpeed(double speed) {
        Hardware.intake.set(speed);
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