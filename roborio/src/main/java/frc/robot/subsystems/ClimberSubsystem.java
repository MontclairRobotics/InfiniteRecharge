package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.LiftArm;
import frc.robot.utils.Hardware;
import frc.robot.utils.Constants.LiftConstants;

public class ClimberSubsystem extends SubsystemBase {
    //SpeedControllerGroup armMotors;
    //SpeedControllerGroup winchMotors;

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

    public boolean getLimitSwitch() {
        return Hardware.leftLimitSwitch.get();
    }
    // public void climbUp() {
    //     currentWinchDirection = Direction.UP;
    // }

    // public void climbDown() {
    //     currentWinchDirection = Direction.DOWN;
    // }

    // public void climbStop() {
    //     currentWinchDirection = Direction.STOPPED;
    // }
}