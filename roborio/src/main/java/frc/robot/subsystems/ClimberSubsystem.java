package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Hardware;
import frc.robot.utils.Constants.LiftConstants;

public class ClimberSubsystem extends SubsystemBase {
    //Not needed for now, will be used in the future
    SpeedControllerGroup armMotors;
    SpeedControllerGroup winchMotors;

    public ClimberSubsystem() {        
    }

    public void raiseArm() {
        //This is not final, but individual motors should be tested before grouping them
        //Hardware.leftLiftArm.set(LiftConstants.kLiftSpeed);
    }

    public void lowerArm() {
        //See raiseArm comment
        //Hardware.leftLiftArm.set(-LiftConstants.kLiftSpeed);
    }

    public void stopArm() {
        //Hardware.leftLiftArm.set(0);
    }

    //public boolean getLimitSwitch() { return Hardware.liftLimit.get(); }
}