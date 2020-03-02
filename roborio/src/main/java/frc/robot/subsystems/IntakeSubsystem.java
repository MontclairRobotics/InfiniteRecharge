package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Hardware;

public class IntakeSubsystem extends SubsystemBase {
    
    public IntakeSubsystem() {

    }
    
    public void startIntake() {
        Hardware.intake.set(100);
    }
    
    public void stopIntake() {
        Hardware.intake.set(0);
    }


    @Override
    public void periodic() {

    }
}