//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.utils.StateMachine.*;


class OutputEnd extends StateMachineBase<Transport>{
    OutputEnd(Transport caller){super(caller);}

    public StateMachineBase run() {

        StateMachineBase nextState = new RestBase(this);
        
        for (SpeedController motor: caller.getOutputMotors()) {

            motor.stopMotor(); //CHANGE CONSTANT//

        }

        return nextState;

    }
}