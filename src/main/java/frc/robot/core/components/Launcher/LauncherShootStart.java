//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;

//CLASSES//
class LauncherShootStart extends StateMachineBase<Launcher>{
    public LauncherShootStart(Launcher launcher){super(launcher);}

    @Override
    public StateMachineBase<Launcher> run() {

        caller.getMotor().set(caller.getDesiredSpeed());
        return new LauncherRevUp(caller);

    }
}