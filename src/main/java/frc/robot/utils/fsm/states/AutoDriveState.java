package frc.robot.utils.fsm.states;

import frc.robot.utils.fsm.FiniteStateMachine.State;
import frc.robot.Robot;
import frc.robot.component.Drivetrain;

public class AutoDriveState implements State {

    Drivetrain drivetrain;
    
    double distance;
    double angle;
    boolean isAcceptState;
    State nextState;

    public AutoDriveState(Robot robot, double distance, double angle, State nextState){
        this.drivetrain = robot.getDrivetrain();
        this.distance = distance;
        this.angle = angle;
        this.nextState = nextState;
        isAcceptState = false;
    }

    public AutoDriveState(Robot robot, double distance, double angle, boolean isAcceptState){
        this.drivetrain = robot.getDrivetrain();
        this.distance = distance;
        this.angle = angle;
        this.isAcceptState = isAcceptState;
    }

    @Override
    public void inState() {
        drivetrain.autoDrive(distance, angle);
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public boolean isDone() {
        return drivetrain.autoDrive(distance, angle);
    }

    @Override
    public boolean isAcceptState() {
        return isAcceptState;
    }

}