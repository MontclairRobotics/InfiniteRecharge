//PACKAGE//
package frc.robot.core.components.Transport;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import frc.robot.core.components.Transport.BallIntake.BallSuck;
import frc.robot.core.components.Transport.BallIntake.IntakeArm;
//IMPORTS//
import frc.robot.core.utils.Hardware;
import frc.robot.core.utils.StateMachine.*;

public class Transport implements TransportIntake, TransportMovement{

    //DECLARATIONS//
    private CANSparkMax intakeMotor = Hardware.TransportIntakeWheel;
    private CANSparkMax MovementMotor = Hardware.AllTransport;
    public BallSuck getBall;
    private boolean hasIntaken, hasOutputted, hasMoved;
    public IntakeArm intakeArm;
    //sesnor thingy

    //CONSTRUCTOR//
    Transport() {
        intakeMotor = null;
        MovementMotor = null;

        hasIntaken = false;
        hasOutputted = false;

        StateMachineHandler.instantiateState( new RestState(this, "i") );
        StateMachineHandler.instantiateState( new RestState(this, "u") );
    }
    public Transport( CANSparkMax intakeMotor, CANSparkMax MovementMotor, TalonSRX intakeArm) {

        hasIntaken = false;
        hasOutputted = false;
        hasMoved = false;

        StateMachineHandler.instantiateState( new RestState(this, "i") );
        StateMachineHandler.instantiateState( new RestState(this, "u") );
    }


    //GETTER-SETTERS//
    public CANSparkMax getIntakeMotor() { return intakeMotor; }
    public CANSparkMax getMovementMotor() { return MovementMotor; }
    public boolean getHasIntaken() { return hasIntaken; }
    public boolean getHasOutputted() { return hasOutputted; }
    public boolean getHasMoved() {return hasMoved;}
    public void setHasIntaken(boolean value) { hasIntaken = value; }
    public void setHasOutputted(boolean value) { hasOutputted = value; }
    public void setHasMoved(boolean value) {hasMoved = value;}

    public void intake() {
     
        StateMachineHandler.setState(new IntakeStart(this, "i"), this, "i");

    }

    public void move() {

        StateMachineHandler.setState(new MovementStart(this, "m"), this, "m");

    }

    public boolean full() { return false; } //USE SENSORS
    public boolean empty() { return false; } //USE SENSORS

}