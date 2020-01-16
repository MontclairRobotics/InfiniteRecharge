package frc.robot.core.utils.StateMachine;

import java.util.ArrayList;

public class StateMachineHandler {

    ArrayList<StateMachineBase> states = new ArrayList<StateMachineBase>();

    void run() {

        for( StateMachineBase state : states ) {
            state = state.run();
        }

    }

}