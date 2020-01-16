//PACKAGE//
package frc.robot.core.components.Launcher;

class LauncherShooting implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        LauncherStateMachine nextState = new LauncherShooting();

        if( 1==1 ) { //CHANGE CONDITION

            nextState = new LauncherRest();

            if(launcher.getQueueLength() > 0) {
                launcher.getMotor().stopMotor();
            }

        }

        return nextState;

    }
}