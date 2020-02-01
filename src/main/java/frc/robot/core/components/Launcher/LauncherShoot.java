//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherShoot extends StateBase<Launcher>{
    public LauncherShoot(Launcher launcher,String useId){super(launcher,useId);}

    @Override
    public StateBase run() {

        StateBase nextState = new LauncherShooting(caller,useId);


        return nextState;

    }
}