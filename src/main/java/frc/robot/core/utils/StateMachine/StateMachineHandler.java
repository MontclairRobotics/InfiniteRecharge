package frc.robot.core.utils.StateMachine;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class StateMachineHandler {

    private static Set<StateBase> states = new HashSet<StateBase>();

    public static void instantiateState(StateBase state) {states.add(state);} 

    public static StateBase findState(Object caller, String useId) {

        for(StateBase state: states) {

            if(state.getCaller() == caller && state.getUseId() == useId ) {

                return state;

            }

        }

        return null;

    }

    public static boolean removeState(Object caller, String useId) {

        for(StateBase state: states) {

            if(state.getCaller() == caller && state.getUseId() == useId ) {

                states.remove(state);
                return true;

            }

        }

        return false;

    }

    public static boolean setState(StateBase newState, Object caller, String useId) {

        for(StateBase state: states) {

            if(state.getCaller() == caller && state.getUseId().equals(useId) ) {

                state = newState;
                return true;

            }

        }

        return false;

    }
    
    public static boolean findAndRun(Object caller, String useId) {

        for(StateBase state: states) {

            if(state.getCaller() == caller && state.getUseId() == useId ) {

                state = state.run();
                return true;

            }

        }

        return false;

    }

    public static void runAll() {

        for( StateBase state : states ) {
            state = state.run();
        }

    }

}