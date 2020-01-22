//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherShoot extends StateMachineBase<Launcher>{
    public LauncherShoot(Launcher launcher){super(launcher);}

    @Override
    public StateMachineBase run() {

        StateMachineBase nextState = new LauncherShooting(caller);

        caller.getTransport().output();

        return nextState;

    }
}