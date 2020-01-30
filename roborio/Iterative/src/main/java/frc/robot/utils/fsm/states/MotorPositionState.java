package frc.robot.utils.fsm.states;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.utils.fsm.FiniteStateMachine;

public class MotorPositionState implements FiniteStateMachine.State {

    private CANSparkMax canSparkMax;
    private PIDController pidController;
    private FiniteStateMachine.State nextState;


    public MotorPositionState(CANSparkMax canSparkMax, double target, double tolerance, PIDController pidController, FiniteStateMachine.State nextState){
        this.canSparkMax = canSparkMax;
        pidController.setSetpoint(target);
        pidController.setTolerance(tolerance);
        this.pidController = pidController;
        this.nextState = nextState;

    }

    @Override
    public void inState() {
        canSparkMax.set(pidController.calculate(canSparkMax.getEncoder().getPosition()));
    }

    @Override
    public FiniteStateMachine.State nextState() {
        return nextState;
    }

    @Override
    public boolean isDone() {
        if(pidController.atSetpoint()){
            canSparkMax.set(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAcceptState() {
        return nextState == null;
    }
}
