//PACKAGE//
package frc.robot.core.components.Launcher;

//CLASSES//
class LauncherShootStart implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        launcher.getMotor().set(launcher.getDesiredSpeed());
        return new LauncherRevUp();

    }
}