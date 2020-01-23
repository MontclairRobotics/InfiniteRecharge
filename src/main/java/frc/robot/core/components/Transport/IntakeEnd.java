//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.utils.StateMachine.*;

class IntakeEnd extends StateMachineBase<Transport>{
    IntakeEnd(Transport caller, String useId){super(caller, useId);}

    public StateMachineBase run() {

        StateMachineBase nextState = new RestBase(this, useId);
        
        for (SpeedController motor: caller.getIntakeMotors()) {

            motor.stopMotor(); //CHANGE CONSTANT//

        }

        return nextState;

    }
}