//PACKAGE//
package frc.robot.core.components.Climber;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Encoder;

interface WinchBase {
    void wind(double speed, double distance);
    void reset(double speed);
    void periodic();
}

interface WinchStateMachine {
    WinchStateMachine run(Winch winch);
}

class WinchBegin implements WinchStateMachine {

    public WinchStateMachine run(Winch winch) {

        WinchStateMachine nextState = new WinchWaiting();

        winch.getMotor().set(winch.getDesiredSpeed());
        winch.getEncoder().reset();

        return nextState;

    }

}
class WinchWaiting implements WinchStateMachine {

    public WinchStateMachine run(Winch winch) {

        WinchStateMachine nextState = new WinchWaiting();

        if(winch.getEncoder().getDistance() > winch.getDesiredDistance()) {nextState = new WinchEnd();}

        return nextState;

    }

}
class WinchEnd implements WinchStateMachine {

    public WinchStateMachine run(Winch winch) {

        WinchStateMachine nextState = new WinchRest();

        winch.getMotor().stopMotor();
        winch.setCurrentDistance(winch.getEncoder().getDistance());

        return nextState;

    }

}
class WinchRest implements WinchStateMachine {

    public WinchStateMachine run(Winch winch) {

        return this;

    }

}

public class Winch implements WinchBase{
        
    private SpeedController motor;
    private Encoder encoder;
    private WinchStateMachine state;
    private double desiredSpeed;
    private double desiredDistance;

    private double currentDistance;
    private double maximumDistance;

    Winch() {

        motor = null;
        encoder = null;
        state = new WinchRest();
        desiredSpeed = 0;
        desiredDistance = 0;
        currentDistance = 0;
        maximumDistance = 0;

    }

    Winch(SpeedController motor, Encoder encoder, double maximumDistance) {

        this.motor = motor;
        this.encoder = encoder;
        state = new WinchRest();
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

    public void periodic() {

        state = state.run(this);

    }

    public void wind(double speed, double distance) {

        desiredSpeed = speed;
        desiredDistance = distance;
        state = new WinchBegin();

    }

    public void reset(double speed) {

        wind(speed, -currentDistance);

    }

}