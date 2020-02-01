//PACKAGE//
package frc.robot.core.components.Transport;


//IMPORTS//
import frc.robot.core.utils.StateMachine.*;

class IntakeStart extends StateBase<Transport> {
    public IntakeStart(Transport caller, String useId){super(caller, useId);}

    public StateBase run() {

        StateBase nextState = new IntakeRun(caller, useId);
        caller.intakeArm.armLower(0.1);
        caller.getBall.intakeBall();
        return nextState;

    }
}