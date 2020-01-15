//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;

class OutputStart implements TransportStateMachine {
    public TransportStateMachine run(Transport transport) {

        TransportStateMachine nextState = new OutputRun();

        for (SpeedController motor: transport.getOutputMotors()) {

            motor.set(1); //CHANGE CONSTANT//

        }

        return nextState;

    }
}