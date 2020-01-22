//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.utils.StateMachine.*;


class IntakeEnd extends StateMachineBase<Transport>{
    IntakeEnd(Transport caller){super(caller);}

    public StateMachineBase run() {

        StateMachineBase nextState = new RestBase(this);
        
        for (SpeedController motor: caller.getIntakeMotors()) {

            motor.stopMotor(); //CHANGE CONSTANT//

        }

        return nextState;

    }
}