package frc.robot.core.components.Transport;

import frc.robot.core.utils.Hardware;
import frc.robot.core.utils.StateMachine.*;
class MovementStart  extends StateBase <Transport> {

    public MovementStart(Transport caller, String useId) {super(caller, useId);}
     
    public StateBase run() {
        StateBase nextState = new MovementRun(caller, useId);
        Hardware.AllTransport.set(0.3);
        return nextState;
    }

}