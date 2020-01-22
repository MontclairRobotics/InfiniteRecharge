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
    private StateMachineBase intakeState, outputState;

    //sesnor thingy

    //CONSTRUCTOR//
    Transport() {
        intakeMotors = null;
        outputMotors = null;

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new RestBase(this);
        outputState = new RestBase(this);
    }
    Transport( SpeedController intakeMotor, SpeedController outputMotor ) {
        intakeMotors = new SpeedController[]{intakeMotor};
        outputMotors = new SpeedController[]{outputMotor};

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new RestBase(this);
        outputState = new RestBase(this);
    }
    Transport( SpeedController[] intakeMotors, SpeedController[] outputMotors ) {
        this.intakeMotors = intakeMotors;
        this.outputMotors = outputMotors;

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new RestBase(this);
        outputState = new RestBase(this);
    }

    //GETTER-SETTERS//
    public SpeedController[] getIntakeMotors() { return intakeMotors; }
    public SpeedController[] getOutputMotors() { return outputMotors; }
    public boolean getHasIntaken() { return hasIntaken; }
    public boolean getHasOutputted() { return hasOutputted; }
    public void setHasIntaken(boolean value) { hasIntaken = value; }
    public void setHasOutputted(boolean value) { hasOutputted = value; }

    public void intake() {
     
        intakeState = new IntakeStart(this);

    }

    public void output() {

        outputState = new OutputStart(this);

    }

    public boolean full() { return false; } //USE SENSORS
    public boolean empty() { return false; } //USE SENSORS

}