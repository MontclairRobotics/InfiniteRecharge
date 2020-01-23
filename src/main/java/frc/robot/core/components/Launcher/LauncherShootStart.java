//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

//CLASSES//
class LauncherShootStart extends StateMachineBase<Launcher>{
    public LauncherShootStart(Launcher launcher, String useId){super(launcher, useId);}

    @Override
    public StateMachineBase run() {

        caller.getMotor().set(caller.getDesiredSpeed());
        return new LauncherRevUp(caller, useId);

    }
}