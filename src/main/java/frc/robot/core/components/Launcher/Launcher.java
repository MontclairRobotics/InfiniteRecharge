//PACKAGE//
package frc.robot.core.components.Launcher;

//IMPORTS//
import frc.robot.core.components.Transport.*;
import frc.robot.core.utils.StateMachine.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

class Launcher implements LauncherBase{

    Launcher() {

        motor = null;
        encoder = null;
        transport = null;
        StateMachineHandler.instantiateState( RestBase(this, null); );
        desiredSpeed = 0;
        shooterQueueLength = 0;

    }

    Launcher(SpeedController motor, Encoder encoder, Transport transport) {

        this.motor = motor;
        this.encoder = encoder;
        this.transport = transport;
        state = new RestBase(this, null);
        desiredSpeed = 0;
        shooterQueueLength = 0;

    }

    private SpeedController motor;
    private Encoder encoder;
    private Transport transport;
    private double desiredSpeed;
    private int shooterQueueLength;

    //GETTER-SETTERS//
    public SpeedController getMotor() {return motor;}
    public Encoder getEncoder() {return encoder;}
    public double getDesiredSpeed() {return desiredSpeed;}
    public Transport getTransport() {return transport;}
    public int getQueueLength() {return shooterQueueLength;}

    public boolean finishedShooting() {

        return (StateMachineHandler.findState(this, null).equals(new RestBase(this,null)));

    }

    public void shoot(double velocity) {

        shoot(velocity, 1);

    }

    public void shoot(double velocity, int burstSize) {

        desiredSpeed = velocity;
        shooterQueueLength += burstSize;

        StateMachineHandler.setState(new LauncherShootStart(this, null), this, null);

    }

}