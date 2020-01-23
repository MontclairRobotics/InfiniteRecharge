package frc.robot.core.utils.StateMachine;

//STATE MACHINE BASE//

public class StateMachineBase<T> implements StateMachineInterface {

    public StateMachineBase(T caller, String useId) {

        this.caller = caller;
        this.useId = useId;

    }

    public T getCaller() {return caller;}
    public String getUseId() {return useId;}

    public String useId;
    public T caller;

    public StateMachineBase run() {return null;}

}