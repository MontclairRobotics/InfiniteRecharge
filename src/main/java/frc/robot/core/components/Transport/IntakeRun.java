//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//

class IntakeRun implements TransportStateMachine {
    public TransportStateMachine run(Transport transport) {

        TransportStateMachine nextState = new IntakeRun();

        if(transport.getHasIntaken()) { 
            nextState = new IntakeEnd(); 
            transport.setHasIntaken(false);
        }

        return nextState;

    }
}