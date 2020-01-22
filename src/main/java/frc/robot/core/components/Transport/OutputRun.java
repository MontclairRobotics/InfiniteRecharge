//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import frc.robot.core.utils.StateMachine.*;

class OutputRun extends StateMachineBase<Transport>{
    public OutputRun(Transport caller){super(caller);}

    public StateMachineBase run() {

        StateMachineBase nextState = new IntakeRun(caller);

        if(caller.getHasIntaken()) { 
            nextState = new IntakeEnd(caller); 
            caller.setHasIntaken(false);
        }

        return nextState;

    }
}