//PACKAGE//
package frc.robot.core.components.Launcher;

class LauncherRevUp implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        LauncherStateMachine nextState = new LauncherRevUp();

        if( launcher.getEncoder().get() >= 10) { //Change constant

            nextState = new LauncherShoot();

        }

        return nextState;

    }
}