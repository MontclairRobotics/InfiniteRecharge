//PACKAGE//
package frc.robot.core.components.Launcher;


import frc.robot.core.utils.StateMachine.*;

//CLASSES//
class LauncherShootStart extends StateBase<Launcher>{
    public LauncherShootStart(Launcher launcher, String useId){super(launcher, useId);}

    @Override
    public StateBase run() {

        caller.getMotor().set(caller.getDesiredSpeed());
        return new LauncherRevUp(caller, useId);

    }
}