package frc.robot.core.utils.StateMachine;

//STATE MACHINE BASE//

public class StateMachineBase<T> implements StateMachineInterface {

    public StateMachineBase(T caller) {

        this.caller = caller;

    }

    public T caller;
    public StateMachineBase run() {return null;}

}