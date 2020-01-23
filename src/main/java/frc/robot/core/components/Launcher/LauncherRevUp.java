//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

class LauncherRevUp extends StateMachineBase<Launcher>{
    public LauncherRevUp(Launcher launcher,String useId){super(launcher,useId);}

    @Override
    public StateMachineBase run() {

        StateMachineBase nextState = new LauncherRevUp(caller,useId);

        if( caller.getEncoder().get() >= 10) { //Change constant

            nextState = new LauncherShoot(caller, useId);

        }

        return nextState;

    }
}