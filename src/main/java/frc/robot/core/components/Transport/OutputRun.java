//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;

class OutputRun implements TransportStateMachine {
    public TransportStateMachine run(Transport transport) {

        TransportStateMachine nextState = new OutputRun();

        if(transport.getHasOutputted()) { 
            nextState = new OutputEnd(); 
            transport.setHasOutputted(false);
        }

        return nextState;

    }
}