//PACKAGE//
package frc.robot.core.components.Launcher;

class LauncherShoot implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        LauncherStateMachine nextState = new LauncherShooting();

        launcher.getTransport().output();

        return nextState;

    }
}