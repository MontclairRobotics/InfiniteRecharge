//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherShooting extends StateMachineBase<Launcher>{
    public LauncherShooting(Launcher launcher){super(launcher);}

    @Override
    public StateMachineBase run() {

        StateMachineBase nextState = new LauncherShooting(caller);

        if( 1==1 ) { //CHANGE CONDITION

            nextState = new RestBase(caller);

            if(caller.getQueueLength() > 0) {
                caller.getMotor().stopMotor();
            }

        }

        return nextState;

    }
}