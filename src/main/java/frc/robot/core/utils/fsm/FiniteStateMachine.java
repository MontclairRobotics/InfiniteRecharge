package frc.robot.core.utils.fsm;

public class FiniteStateMachine {
    private String name;
    private State currentState;

    public FiniteStateMachine(String name, State startState) {
        this.name = name;
        this.currentState = startState;
    }

    public void run() {
        //If the current state is done, go to the next one
        if (currentState.isDone()) {
            currentState = currentState.nextState();
        }
        //Otherwise, run the current state
        else {
            currentState.inState();
        }
    }

    public String getName() { return name;}

    public State getCurrentState() {
        return currentState;
    }
}