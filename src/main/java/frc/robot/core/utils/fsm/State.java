package frc.robot.core.utils.fsm;

public interface State {

    /**
     * The action that is performed during the state
     **/
    public void inState();

    /**
     * @return the state that will be performed after the current one is done
     **/
    public State nextState();

     /**
      * @return whether or not the current state is done
      */
    public boolean isDone();

    /**
     * 
     * @return whether or not the current state is accepting a next state
     */
    public boolean isAcceptState();
}