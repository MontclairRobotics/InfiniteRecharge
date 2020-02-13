package frc.robot.core.components.Transport.BallIntake;
import frc.robot.core.utils.*;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.Robot;

public class BallSuck {

/*void ballIntake() {
if (Robot.controlSystem.getButton(AuxillaryButtons.TEMP)) {
Hardware.TransportIntake.set(ControlMode.PercentOutput, 0.1);
  }         
 }*/

  public SpeedController motor = Hardware.IntakeArm;
  public double speed = 1;
  
  public void run(boolean pressed) {

    if(pressed) {

      motor.set(speed);

    } else {

      motor.stopMotor();

    }

  }

  public BallSuck(SpeedController motor, double speed) {

    this.motor = motor;
    this.speed = speed;

  }

  public BallSuck() {}

}