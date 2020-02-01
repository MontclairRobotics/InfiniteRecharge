//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherRevUp extends StateBase<Launcher>{
    public LauncherRevUp(Launcher launcher,String useId){super(launcher,useId);}

    @Override
    public StateBase run() {

        StateBase nextState = new LauncherRevUp(caller,useId);

        if( caller.getEncoder().getVelocity() >= 2500) { // TODO Change constant

            nextState = new LauncherShoot(caller, useId);

        }

        return nextState;

    }
}