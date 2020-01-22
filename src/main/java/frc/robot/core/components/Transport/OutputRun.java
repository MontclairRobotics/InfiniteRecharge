//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import frc.robot.core.utils.StateMachine.*;

class IntakeRun extends StateMachineBase<Transport>{
    public IntakeRun(Transport caller){super(caller);}

    public StateMachineBase run() {

        StateMachineBase nextState = new IntakeRun(caller);

        if(caller.getHasIntaken()) { 
            nextState = new IntakeEnd(caller); 
            caller.setHasIntaken(false);
        }

        return nextState;

    }
}