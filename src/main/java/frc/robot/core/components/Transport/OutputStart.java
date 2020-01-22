//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.utils.StateMachine.*;

class OutputStart extends StateMachineBase<Transport> {
    public OutputStart(Transport caller){super(caller);}

    public StateMachineBase run() {

        StateMachineBase nextState = new IntakeRun(caller);

        for (SpeedController motor: caller.getIntakeMotors()) {

            motor.set(1); //CHANGE CONSTANT//

        }

        return nextState;

    }
}