
/****************************************************************************

Copyright (c) 2019 William Chu. All Rights Reserved.  

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

******************************************************************************/


package frc.robot.utils.fsm;

import edu.wpi.first.wpilibj.DriverStation;

import java.util.ArrayList;

/**
 * Finite State Machine
 * Defined by a name, array of states.
 *
 * @author Will Chu
 * @version 1.0
 *
 */
public class FiniteStateMachine {

    /**
     * Interface for all States in FSM
     */
    public interface State{

        /**
         * Method while state is running
         */
        public void inState();

        /**
         * Returns the next state in FSM
         * @return State
         */
        public State nextState();

        /**
         * If state is completed
         * @return boolean
         */
        public boolean isDone();

        /**
         * If this state is an accept state
         * @return boolean
         */
        public boolean isAcceptState();
    }

    private String name;
    private ArrayList<State> states = new ArrayList<>();
    private State currentState;

    /**
     * @param name String name of FSM
     * @param startState Initial start state of FSM
     * @param states States not including start state of FSM
     */
    public FiniteStateMachine(String name, State startState, State ... states){
        this.name = name;
        this.states.add(startState);
        this.currentState = startState;
        for (State state : states) {
            this.states.add(state);
        }
    }

    /**
     * @param name String name of FSM
     * @param startState Initial start state of FSM
     */
    public FiniteStateMachine(String name, State startState){
        this.name = name;
        this.states.add(startState);
        this.currentState = startState;
    }

    /**
     * Runs the FSM until accept state is reached
     * If accept state does not exist, runs till termination of program
     */
    public void run(){
        if (currentState.isDone()){
            if(currentState.isAcceptState()){
                System.out.print("State Machine Done");
            }
            currentState = currentState.nextState();
        }else{
            currentState.inState();

        }
    }

    /**
     * @return String name of FSM
     */
    public String getName() {
        return name;
    }

    /**
     * Returns current state in FSM
     * @return State
     */
    public State getCurrentState() {
        return currentState;
    }

}