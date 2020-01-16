//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherShooting extends StateMachineBase<Launcher>{
    public LauncherShooting(Launcher launcher){super(launcher);}

    @Override
    public StateMachineBase<Launcher> run() {

        StateMachineBase<Launcher> nextState = new LauncherShooting(caller);

        if( 1==1 ) { //CHANGE CONDITION

            nextState = new LauncherRest(caller);

            if(caller.getQueueLength() > 0) {
                caller.getMotor().stopMotor();
            }

        }

        return nextState;

    }
}