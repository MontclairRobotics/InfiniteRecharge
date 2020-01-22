package frc.robot.core.components.Climber;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.core.utils.StateMachine.*;

interface ArmBase {
    void extend();
    void retract();
}

class ArmStart extends StateMachineBase<Arm>{
    public ArmStart(Arm arm) {super(arm);}

    @Override
    public StateMachineBase<Arm> run() {
        StateMachineBase nextState = new ArmWait(caller);

        caller.getMotor().set(caller.getDesiredSpeed());
        caller.getEncoder().reset();

        return nextState;
    }
}
class ArmWait extends StateMachineBase<Arm>{
    public ArmWait(Arm arm) {super(arm);}

    @Override
    public StateMachineBase<Arm> run() {
        StateMachineBase nextState = new ArmWait(caller);

        if(caller.getEncoder().getDistance() > caller.getMaximumRotation()) {nextState = new ArmEnd(caller);}

        return nextState;
    }
}
class ArmEnd extends StateMachineBase<Arm>{
    public ArmEnd(Arm arm) {super(arm);}

    @Override
    public StateMachineBase<Arm> run() {
        StateMachineBase nextState = new RestBase(caller);

        caller.getMotor().stopMotor();

        return nextState;
    }
}

public class Arm implements ArmBase{

    public Arm() {

        motor = null;
        encoder = null;
        desiredSpeed = 0;
        maximumRotation = 0;
        state = new RestBase(this);

    }

    public Arm(SpeedController motor, Encoder encoder, double maximumRotation) {

        this.motor = motor;
        this.encoder = encoder;
        desiredSpeed = 0;
        this.maximumRotation = maximumRotation;
        state = new RestBase(this);

    }

    private SpeedController motor;
    private Encoder encoder;
    private double desiredSpeed;
    private double maximumRotation;
    private StateMachineBase state = new StateMachineBase<Arm>(this);

    public SpeedController getMotor() {return motor;}
    public double getDesiredSpeed() {return desiredSpeed;}
    public double getMaximumRotation() {return maximumRotation;}
    public Encoder getEncoder() {return encoder;}

    public void extend() {
        desiredSpeed = 0.1;
        state = new ArmStart(this);
        // need to change
    }
    
    public void retract() {
        desiredSpeed = -0.1;// need to change
        state = new ArmStart(this);
    }
}