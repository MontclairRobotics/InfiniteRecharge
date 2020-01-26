//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.utils.StateMachine.*;

public class Transport implements TransportIntake, TransportOutput{

    //DECLARATIONS//
    private SpeedController[] intakeMotors;
    private SpeedController[] outputMotors;
    private boolean hasIntaken, hasOutputted;
    public int ballCount = 0;

    //sesnor thingy

    //CONSTRUCTOR//
    Transport() {
        intakeMotors = null;
        outputMotors = null;

        hasIntaken = false;
        hasOutputted = false;

        StateMachineHandler.instantiateState( new RestState(this, "i") );
        StateMachineHandler.instantiateState( new RestState(this, "u") );
    }
    public Transport( SpeedController intakeMotor, SpeedController outputMotor ) {
        intakeMotors = new SpeedController[]{intakeMotor};
        outputMotors = new SpeedController[]{outputMotor};

        hasIntaken = false;
        hasOutputted = false;

        StateMachineHandler.instantiateState( new RestState(this, "i") );
        StateMachineHandler.instantiateState( new RestState(this, "u") );
    }
    Transport( SpeedController[] intakeMotors, SpeedController[] outputMotors ) {
        this.intakeMotors = intakeMotors;
        this.outputMotors = outputMotors;

        hasIntaken = false;
        hasOutputted = false;

        StateMachineHandler.instantiateState( new RestState(this, "i") );
        StateMachineHandler.instantiateState( new RestState(this, "u") );
    }

    //GETTER-SETTERS//
    public SpeedController[] getIntakeMotors() { return intakeMotors; }
    public SpeedController[] getOutputMotors() { return outputMotors; }
    public boolean getHasIntaken() { return hasIntaken; }
    public boolean getHasOutputted() { return hasOutputted; }
    public void setHasIntaken(boolean value) { hasIntaken = value; }
    public void setHasOutputted(boolean value) { hasOutputted = value; }

    public void intake() {
     
        StateMachineHandler.setState(new IntakeStart(this, "i"), this, "i");

    }

    public void output() {

        StateMachineHandler.setState(new OutputStart(this, "o"), this, "o");

    }

    public boolean full() { return false; } //USE SENSORS
    public boolean empty() { return false; } //USE SENSORS

}