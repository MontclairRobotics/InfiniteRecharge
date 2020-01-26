//PACKAGE//
package frc.robot.core.components.Launcher;

//IMPORTS//
import frc.robot.core.components.Transport.*;
import frc.robot.core.utils.StateMachine.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;

public class Launcher implements LauncherBase{

    Launcher() {

        motor = null;
        encoder = null;
        transport = null;
        StateMachineHandler.instantiateState( new RestState(this, null) );
        desiredSpeed = 0;
        shooterQueueLength = 0;

    }

    public Launcher(TalonSRX launcherMotor, Encoder encoder, Transport transport) {

        this.motor = launcherMotor;
        this.encoder = encoder;
        this.transport = transport;
        StateMachineHandler.instantiateState(new RestState(this, null));
        desiredSpeed = 0;
        shooterQueueLength = 0;

    }

    private TalonSRX motor;
    private Encoder encoder;
    private Transport transport;
    private double desiredSpeed;
    private int shooterQueueLength;

    //GETTER-SETTERS//
    public TalonSRX getMotor() {return motor;}
    public Encoder getEncoder() {return encoder;}
    public double getDesiredSpeed() {return desiredSpeed;}
    public Transport getTransport() {return transport;}
    public int getQueueLength() {return shooterQueueLength;}

    public boolean finishedShooting() {

        return (StateMachineHandler.findState(this, null).equals(new RestState(this,null)));

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