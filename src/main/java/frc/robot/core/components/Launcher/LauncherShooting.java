//PACKAGE//
package frc.robot.core.components.Launcher;

import frc.robot.core.utils.StateMachine.*;
import com.ctre.phoenix.motorcontrol.ControlMode;

class LauncherShooting extends StateBase<Launcher>{
    public LauncherShooting(Launcher launcher, String useId){super(launcher, useId);}

    @Override
    public StateBase run() {

        StateBase nextState = new LauncherShooting(caller, useId);

        if( 1==1 ) { //TODO CHANGE CONDITION

            nextState = new RestState(caller, useId);

            if(caller.getQueueLength() > 0) {
                caller.getMotor().set(ControlMode.PercentOutput, 0);
            }

        }

        return nextState;

    }
}