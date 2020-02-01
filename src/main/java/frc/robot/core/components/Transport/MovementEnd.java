package frc.robot.core.components.Transport;

import frc.robot.core.utils.Hardware;
//IMPORTS//
import frc.robot.core.utils.StateMachine.*;

class MovementEnd extends StateBase<Transport>{
    MovementEnd(Transport caller, String useId){super(caller, useId);}

    public StateBase run() {

        StateBase nextState = new RestState(this, useId);
        Hardware.AllTransport.set(0);

        return nextState;

    }
}