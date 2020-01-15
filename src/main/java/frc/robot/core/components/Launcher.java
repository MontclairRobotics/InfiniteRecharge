//PACKAGE//
package frc.robot.core.components;

//IMPORTS//
import frc.robot.core.components.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

//INTERFACES//
interface LauncherBase{

    void shoot(double velocity);
    void shoot(double velocity, int burstSize);

}

interface LauncherStateMachine{

    LauncherStateMachine run(Launcher launcher);

}

//CLASSES//
class LauncherShootStart implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        launcher.getMotor().set(launcher.getDesiredSpeed());
        return new LauncherRevUp();

    }
}
class LauncherRevUp implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        LauncherStateMachine nextState = new LauncherRevUp();

        if( launcher.getEncoder().get() >= 10) { //Change constant

            nextState = new LauncherShoot();

        }

        return nextState;

    }
}
class LauncherShoot implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        LauncherStateMachine nextState = new LauncherShooting();

        launcher.getTransport().output();

        return nextState;

    }
}
class LauncherShooting implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        LauncherStateMachine nextState = new LauncherShooting();

        if( 1==1 ) { //CHANGE CONDITION

            nextState = new Rest();

            if(launcher.getQueueLength() > 0) {
                launcher.getMotor().stopMotor();
            }

        }

        return nextState;

    }
}
class Rest implements LauncherStateMachine{
    public LauncherStateMachine run( Launcher launcher ) {

        return this;

    }
}

class Launcher implements LauncherBase{

    Launcher() {

        motor = null;
        encoder = null;
        transport = null;
        state = new Rest();
        desiredSpeed = 0;
        shooterQueueLength = 0;

    }

    Launcher(SpeedController motor, Encoder encoder, Transport transport) {

        this.motor = motor;
        this.encoder = encoder;
        this.transport = transport;
        state = new Rest();
        desiredSpeed = 0;
        shooterQueueLength = 0;

    }

    private SpeedController motor;
    private Encoder encoder;
    private Transport transport;
    private LauncherStateMachine state;
    private double desiredSpeed;
    private int shooterQueueLength;

    //GETTER-SETTERS//
    public SpeedController getMotor() {return motor;}
    public Encoder getEncoder() {return encoder;}
    public double getDesiredSpeed() {return desiredSpeed;}
    public Transport getTransport() {return transport;}
    public int getQueueLength() {return shooterQueueLength;}

    public boolean finishedShooting() {

        return (state.equals(new Rest()));

    }
    
    void periodic() {

        if(state.equals(new Rest()) && shooterQueueLength > 0) {

            state = new LauncherShootStart();
            shooterQueueLength--;

        }

        state = state.run(this);

    }

    public void shoot(double velocity) {

        shoot(velocity, 1);

    }

    public void shoot(double velocity, int burstSize) {

        desiredSpeed = velocity;
        shooterQueueLength += burstSize;

    }

}