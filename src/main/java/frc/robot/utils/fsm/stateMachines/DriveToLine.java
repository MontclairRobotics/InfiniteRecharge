package frc.robot.utils.fsm.stateMachines;

import frc.robot.Robot;
import frc.robot.utils.fsm.FiniteStateMachine;
import frc.robot.utils.fsm.states.AutoDriveState;

public class DriveToLine extends FiniteStateMachine {

    public DriveToLine(Robot robot) {
        super("Drive to Line",
                new AutoDriveState(robot,24,0, true));
    }
}
