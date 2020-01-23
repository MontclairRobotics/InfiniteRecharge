package frc.robot.core.utils.StateMachine;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class StateMachineHandler {

    private static Set<StateMachineBase> states = new HashSet<StateMachineBase>();

    public static void instantiateState(StateMachineBase state) {states.add(state);} 

    public static StateMachineBase findState(Object caller, String useId) {

        for(StateMachineBase state: states) {

            if(state.getCaller() == caller && state.getUseId() == useId ) {

                return state;

            }

        }

        return null;

    }

    public static boolean setState(StateMachineBase newState, Object caller, String useId) {

        for(StateMachineBase state: states) {

            if(state.getCaller() == caller && state.getUseId().equals(useId) ) {

                state = newState;
                return true;

            }

        }

        return false;

    }
    
    public static void run() {

        for( StateMachineBase state : states ) {
            state = state.run();
        }

    }

}