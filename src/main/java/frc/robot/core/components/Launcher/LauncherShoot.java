//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherShoot extends StateMachineBase<Launcher>{
    public LauncherShoot(Launcher launcher,String useId){super(launcher,useId);}

    @Override
    public StateMachineBase run() {

        StateMachineBase nextState = new LauncherShooting(caller,useId);

        caller.getTransport().output();

        return nextState;

    }
}