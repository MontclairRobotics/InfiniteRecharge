//PACKAGE//
package frc.robot.core.components.Climber;

//IMPORTS//
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.core.utils.StateMachine.*;

interface WinchBase {
    void wind(double speed, double distance);
    void reset(double speed);
}

class WinchBegin extends StateBase<Winch>{
    public WinchBegin(Winch caller, String useId){super(caller, useId);}

    public StateBase run() {

        StateBase nextState = new WinchWaiting(caller, useId);

        caller.getMotor().set(ControlMode.PercentOutput, caller.getDesiredSpeed());
        caller.getEncoder().reset();

        return nextState;

    }

}
class WinchWaiting extends StateBase<Winch> {
    public WinchWaiting(Winch caller, String useId){super(caller, useId);}

    public StateBase run() {

        StateBase nextState = new WinchWaiting(caller, useId);

        if(caller.getEncoder().getDistance() > caller.getDesiredDistance()) {nextState = new WinchEnd(caller, useId);}

        return nextState;

    }

}
class WinchEnd extends StateBase<Winch> {
    public WinchEnd(Winch caller, String useId){super(caller, useId);}

    public StateBase run(Winch winch) {

        StateBase nextState = new RestState(caller, useId);

        caller.getMotor().set(ControlMode.PercentOutput, 0);
        caller.setCurrentDistance(caller.getEncoder().getDistance());

        return nextState;

    }

}

public class Winch implements WinchBase{
        
    private TalonSRX motor;
    private Encoder encoder;
    private StateBase state;
    private double desiredSpeed;
    private double desiredDistance;

    private double currentDistance;
    private double maximumDistance;

    Winch() {

        motor = null;
        encoder = null;
        StateMachineHandler.instantiateState(new RestState(this,null));
        desiredSpeed = 0;
        desiredDistance = 0;
        currentDistance = 0;
        maximumDistance = 0;

    }

    Winch(TalonSRX motor, Encoder encoder, double maximumDistance) {

        this.motor = motor;
        this.encoder = encoder;
        StateMachineHandler.instantiateState(new RestState(this,null));
        desiredDistance = 0;
        desiredSpeed = 0;
        this.maximumDistance = maximumDistance;
        currentDistance = 0;

    }

    public TalonSRX getMotor() {return motor;}
    public Encoder getEncoder() {return encoder;}
    public double getDesiredSpeed() {return desiredSpeed;}
    public double getDesiredDistance() {return desiredDistance;}
    public double getCurrentDistance() {return currentDistance;}
    public double getMaximumDistance() {return maximumDistance;}

    public void setCurrentDistance(double currentDistance) {this.currentDistance = currentDistance;}

    public void wind(double speed, double distance) {

        desiredSpeed = speed;
        desiredDistance = distance;
        StateMachineHandler.setState(new RestState(this,null),this,null);

    }

    public void reset(double speed) {

        wind(speed, -currentDistance);

    }

}