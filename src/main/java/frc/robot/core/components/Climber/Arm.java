package frc.robot.core.components.Climber;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.core.utils.StateMachine.*;

interface ArmBase {
    void extend();
    void retract();
}

class ArmStart extends StateBase<Arm>{
    public ArmStart(Arm arm, String useId) {super(arm, useId);}

    @Override
    public StateBase<Arm> run() {
        StateBase nextState = new ArmWait(caller, useId);

        caller.getMotor().set(ControlMode.PercentOutput, caller.getDesiredSpeed());
        caller.getEncoder().reset();

        return nextState;
    }
}
class ArmWait extends StateBase<Arm>{
    public ArmWait(Arm arm, String useId) {super(arm, useId);}

    @Override
    public StateBase<Arm> run() {
        StateBase nextState = new ArmWait(caller, useId);

        if(caller.getEncoder().getDistance() > caller.getMaximumRotation()) {nextState = new ArmEnd(caller, useId);}

        return nextState;
    }
}
class ArmEnd extends StateBase<Arm>{
    public ArmEnd(Arm arm, String useId) {super(arm, useId);}

    @Override
    public StateBase<Arm> run() {
        StateBase nextState = new RestState(caller, useId);

        caller.getMotor().set(ControlMode.PercentOutput, 0);

        return nextState;
    }
}

public class Arm implements ArmBase{

    public Arm() {

        motor = null;
        encoder = null;
        desiredSpeed = 0;
        maximumRotation = 0;
        StateMachineHandler.instantiateState(new RestState(this, null));

    }

    public Arm(TalonSRX motor, Encoder encoder, double maximumRotation) {

        this.motor = motor;
        this.encoder = encoder;
        desiredSpeed = 0;
        this.maximumRotation = maximumRotation;
        StateMachineHandler.instantiateState(new RestState(this, null));

    }

    private TalonSRX motor;
    private Encoder encoder;
    private double desiredSpeed;
    private double maximumRotation;

    public TalonSRX getMotor() {return motor;}
    public double getDesiredSpeed() {return desiredSpeed;}
    public double getMaximumRotation() {return maximumRotation;}
    public Encoder getEncoder() {return encoder;}

    public void extend() {
        desiredSpeed = 0.1;
        StateMachineHandler.setState(new ArmStart(this, null), this, null);
        // need to change
    }
    
    public void retract() {
        desiredSpeed = -0.1;// need to change
        StateMachineHandler.setState(new ArmStart(this, null), this, null);
    }
}