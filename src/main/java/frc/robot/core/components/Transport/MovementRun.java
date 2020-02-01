package frc.robot.core.components.Transport;

import frc.robot.core.utils.Hardware;
import frc.robot.core.utils.StateMachine.*;
class MovementRun  extends StateBase <Transport> {

    public MovementRun(Transport caller, String useId) {super(caller, useId);}
     
    public StateBase run() {
        StateBase nextState = new MovementRun(caller, useId);
        if (caller.getHasMoved()) {
            nextState = new MovementEnd(caller, useId);
            caller.setHasMoved(false);
        }
        return nextState;
    }

}