//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherRevUp extends StateMachineBase<Launcher>{
    public LauncherRevUp(Launcher launcher){super(launcher);}

    @Override
    public StateMachineBase run() {

        StateMachineBase nextState = new LauncherRevUp(caller);

        if( caller.getEncoder().get() >= 10) { //Change constant

            nextState = new LauncherShoot(caller);

        }

        return nextState;

    }
}