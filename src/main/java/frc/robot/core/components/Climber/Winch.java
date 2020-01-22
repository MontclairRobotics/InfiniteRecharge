//PACKAGE//
package frc.robot.core.components.Climber;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.core.utils.StateMachine.*;

interface WinchBase {
    void wind(double speed, double distance);
    void reset(double speed);
}

class WinchBegin extends StateMachineBase<Winch>{
    public WinchBegin(Winch caller){super(caller);}

    public StateMachineBase run() {

        StateMachineBase nextState = new WinchWaiting(caller);

        caller.getMotor().set(caller.getDesiredSpeed());
        caller.getEncoder().reset();

        return nextState;

    }

}
class WinchWaiting extends StateMachineBase<Winch> {
    public WinchWaiting(Winch caller){super(caller);}

    public StateMachineBase run() {

        StateMachineBase nextState = new WinchWaiting(caller);

        if(caller.getEncoder().getDistance() > caller.getDesiredDistance()) {nextState = new WinchEnd(caller);}

        return nextState;

    }

}
class WinchEnd extends StateMachineBase<Winch> {
    public WinchEnd(Winch caller){super(caller);}

    public StateMachineBase run(Winch winch) {

        StateMachineBase nextState = new RestBase(caller);

        caller.getMotor().stopMotor();
        caller.setCurrentDistance(caller.getEncoder().getDistance());

        return nextState;

    }

}

public class Winch implements WinchBase{
        
    private SpeedController motor;
    private Encoder encoder;
    private StateMachineBase state;
    private double desiredSpeed;
    private double desiredDistance;

    private double currentDistance;
    private double maximumDistance;

    Winch() {

        motor = null;
        encoder = null;
        state = new RestBase(this);
        desiredSpeed = 0;
        desiredDistance = 0;
        currentDistance = 0;
        maximumDistance = 0;

    }

    Winch(SpeedController motor, Encoder encoder, double maximumDistance) {

        this.motor = motor;
        this.encoder = encoder;
        state = new RestBase(this);
        desiredDistance = 0;
        desiredSpeed = 0;
        this.maximumDistance = maximumDistance;
        currentDistance = 0;

    }

    public SpeedController getMotor() {return motor;}
    public Encoder getEncoder() {return encoder;}
    public double getDesiredSpeed() {return desiredSpeed;}
    public double getDesiredDistance() {return desiredDistance;}
    public double getCurrentDistance() {return currentDistance;}
    public double getMaximumDistance() {return maximumDistance;}

    public void setCurrentDistance(double currentDistance) {this.currentDistance = currentDistance;}

    public void wind(double speed, double distance) {

        desiredSpeed = speed;
        desiredDistance = distance;
        state = new WinchBegin(this);

    }

    public void reset(double speed) {

        wind(speed, -currentDistance);

    }

}