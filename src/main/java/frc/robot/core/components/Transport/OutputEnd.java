//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.utils.StateMachine.*;


class OutputEnd extends StateBase<Transport>{
    OutputEnd(Transport caller, String useId){super(caller, useId);}

    public StateBase run() {

        StateBase nextState = new RestState(this, useId);
        
        for (SpeedController motor: caller.getOutputMotors()) {

            motor.stopMotor(); //CHANGE CONSTANT//

        }

        return nextState;

    }
}