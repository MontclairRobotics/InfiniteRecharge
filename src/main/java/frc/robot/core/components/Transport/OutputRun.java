//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import frc.robot.core.utils.StateMachine.*;

class OutputRun extends StateBase<Transport>{
    public OutputRun(Transport caller, String useId){super(caller, useId);}

    public StateBase run() {

        StateBase nextState = new IntakeRun(caller, useId);

        if(caller.getHasIntaken()) { 
            nextState = new IntakeEnd(caller, useId); 
            caller.setHasIntaken(false);
        }

        return nextState;

    }
}