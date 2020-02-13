package frc.robot.core.utils.StateMachine;

//STATE MACHINE BASE//

@Deprecated
public abstract class StateBase<T> implements StateMachineInterface {

    public StateBase(T caller, String useId) {

        this.caller = caller;
        this.useId = useId;

    }

    public T getCaller() {return caller;}
    public String getUseId() {return useId;}

    public String useId;
    public T caller;

    public StateBase run() {return null;}

}