package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.LiftArm;
import frc.robot.commands.LowerArm;
import frc.robot.utils.Hardware;
import frc.robot.utils.Constants.LiftConstants;

public class ClimberSubsystem extends SubsystemBase {
    //SpeedControllerGroup armMotors;
    //SpeedControllerGroup winchMotors;

    private LiftArm liftCommand;
    private LowerArm lowerCommand;

    public ClimberSubsystem() {        
    }

    public void raiseArm() {
        //This is not final, but individual motors should be tested before grouping them
        Hardware.leftLiftArm.set(LiftConstants.kLiftSpeed);
        Hardware.rightLiftArm.set(LiftConstants.kLiftSpeed);
    }

    public void lowerArm() {
        //See raiseArm comment
        Hardware.leftLiftArm.set(-LiftConstants.kLiftSpeed);
        Hardware.rightLiftArm.set(-LiftConstants.kLiftSpeed);
        
    }

    public void stopArm() {
        Hardware.leftLiftArm.set(0);
        Hardware.rightLiftArm.set(0);
    }

    public boolean getFullyLimited() {
        return Hardware.leftLimitSwitch.get() && Hardware.rightLimitSwitch.get();
    }

    public boolean getLimited() {
        return Hardware.leftLimitSwitch.get() || Hardware.rightLimitSwitch.get();
    }

}