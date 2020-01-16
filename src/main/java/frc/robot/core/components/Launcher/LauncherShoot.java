//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherShoot extends StateMachineBase<Launcher>{
    public LauncherShoot(Launcher launcher){super(launcher);}

    @Override
    public StateMachineBase<Launcher> run() {

        StateMachineBase<Launcher> nextState = new LauncherShooting(caller);

        caller.getTransport().output();

        return nextState;

    }
}