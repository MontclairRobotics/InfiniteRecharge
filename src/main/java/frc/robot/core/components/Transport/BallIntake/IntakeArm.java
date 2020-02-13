package frc.robot.core.components.Transport.BallIntake;
import frc.robot.core.utils.*;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.Robot;

public class IntakeArm {

  public SpeedController motor = Hardware.IntakeArm;
  public double speedMultiplier = 1;
  public double inputThreshold = 0.1;
  
  public void run(double joystickYAxis) {

    if(Math.abs(joystickYAxis) > inputThreshold) {

      motor.set(joystickYAxis*speedMultiplier);

    } else {

      motor.stopMotor();

    }

  }

  public boolean isInThreshold(double num) {

    return Math.abs(num) < inputThreshold;

  }

  public IntakeArm(SpeedController motor, double speedMultiplier, double inputThreshold) {

    this.motor = motor;
    this.speedMultiplier = speedMultiplier;
    this.inputThreshold = inputThreshold;

  }

  public IntakeArm() {}

}
