package frc.robot.core.components;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.core.components.ControlSystem;
import frc.robot.core.components.Drivetrain;

interface transportIntake {
    void intake();// requires sensor
    boolean full();
}

interface transportOutput {
    void output();// requires sensor
    boolean empty();
}

interface transportStateMachine {
    transportStateMachine run(Transport transport);
}

class intakeStart implements transportStateMachine {
    public transportStateMachine run(Transport transport) {

        transportStateMachine nextState = new intakeRun();

        for (SpeedController motor: transport.getIntakeMotors()) {

            motor.set(1); //CHANGE CONSTANT//

        }

        return nextState;

    }
}
class outputStart implements transportStateMachine {
    public transportStateMachine run(Transport transport) {

        transportStateMachine nextState = new outputRun();

        for (SpeedController motor: transport.getOutputMotors()) {

            motor.set(1); //CHANGE CONSTANT//

        }

        return nextState;

    }
}
class intakeRun implements transportStateMachine {
    public transportStateMachine run(Transport transport) {

        transportStateMachine nextState = new intakeRun();

        if(transport.getHasIntaken()) { 
            nextState = new intakeEnd(); 
            transport.setHasIntaken(false);
        }

        return nextState;

    }
}
class outputRun implements transportStateMachine {
    public transportStateMachine run(Transport transport) {

        transportStateMachine nextState = new outputRun();

        if(transport.getHasOutputted()) { 
            nextState = new outputEnd(); 
            transport.setHasOutputted(false);
        }

        return nextState;

    }
}
class intakeEnd implements transportStateMachine {
    public transportStateMachine run(Transport transport) {

        transportStateMachine nextState = new rest();

        for (SpeedController motor: transport.getIntakeMotors()) {

            motor.stopMotor(); //CHANGE CONSTANT//

        }

        return nextState;

    }
}
class outputEnd implements transportStateMachine {
    public transportStateMachine run(Transport transport) {

        transportStateMachine nextState = new rest();

        for (SpeedController motor: transport.getOutputMotors()) {

            motor.stopMotor(); //CHANGE CONSTANT//

        }

        return nextState;

    }
}
class rest implements transportStateMachine {
    public transportStateMachine run(Transport transport) {
        return new rest();
    }
}

class Transport implements transportIntake, transportOutput{

    //DECLARATIONS//
    private SpeedController[] intakeMotors;
    private SpeedController[] outputMotors;
    private boolean hasIntaken, hasOutputted;
    private transportStateMachine intakeState, outputState;

    //sesnor thingy

    //CONSTRUCTOR//
    Transport() {
        intakeMotors = null;
        outputMotors = null;

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new rest();
        outputState = new rest();
    }
    Transport( SpeedController intakeMotor, SpeedController outputMotor ) {
        intakeMotors = new SpeedController[]{intakeMotor};
        outputMotors = new SpeedController[]{outputMotor};

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new rest();
        outputState = new rest();
    }
    Transport( SpeedController[] intakeMotors, SpeedController[] outputMotors ) {
        this.intakeMotors = intakeMotors;
        this.outputMotors = outputMotors;

        hasIntaken = false;
        hasOutputted = false;

        intakeState = new rest();
        outputState = new rest();
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
     
        intakeState = new intakeStart();

    }

    public void output() {

        outputState = new outputStart();

    }

    public boolean full() { return false; } //USE SENSORS
    public boolean empty() { return false; } //USE SENSORS

}