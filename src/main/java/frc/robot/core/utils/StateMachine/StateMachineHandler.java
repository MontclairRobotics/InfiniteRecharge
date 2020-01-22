package frc.robot.core.utils.StateMachine;

import java.util.ArrayList;

public class StateMachineHandler {

    ArrayList<StateMachineBase> states = new ArrayList<StateMachineBase>();

    public
    
    public void run() {

        for( StateMachineBase state : states ) {
            state = state.run();
        }

    }

}