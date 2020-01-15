//PACKAGE//
package frc.robot.core.components.Transport;

//IMPORTS//
import edu.wpi.first.wpilibj.SpeedController;

public class Transport implements TransportIntake, TransportOutput{

    //DECLARATIONS//
    private SpeedController[] intakeMotors;
    private SpeedController[] outputMotors;
    private boolean hasIntaken, hasOutputted;
    private TransportStateMachine intakeState, outputState;

    //sesnor thingy

    //CONSTRUCTOR//
    Transport() {
        intakeMotors = null;
        outputMotors = null;

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new TransportRest();
        outputState = new TransportRest();
    }
    Transport( SpeedController intakeMotor, SpeedController outputMotor ) {
        intakeMotors = new SpeedController[]{intakeMotor};
        outputMotors = new SpeedController[]{outputMotor};

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new TransportRest();
        outputState = new TransportRest();
    }
    Transport( SpeedController[] intakeMotors, SpeedController[] outputMotors ) {
        this.intakeMotors = intakeMotors;
        this.outputMotors = outputMotors;

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new TransportRest();
        outputState = new TransportRest();
    }

    //GETTER-SETTERS//
    public SpeedController[] getIntakeMotors() { return intakeMotors; }
    public SpeedController[] getOutputMotors() { return outputMotors; }
    public boolean getHasIntaken() { return hasIntaken; }
    public boolean getHasOutputted() { return hasOutputted; }
    public void setHasIntaken(boolean value) { hasIntaken = value; }
    public void setHasOutputted(boolean value) { hasOutputted = value; }

    //METHODS//
    public void peroidic() {

        intakeState = intakeState.run(this);
        outputState = outputState.run(this);

        //UPDATE HASINPUTTED/HASOUPUTTED//

    }

    public void intake() {
     
        intakeState = new IntakeStart();

    }

    public void output() {

        outputState = new OutputStart();

    }

    public boolean full() { return false; } //USE SENSORS
    public boolean empty() { return false; } //USE SENSORS

}