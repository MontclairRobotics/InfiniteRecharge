package frc.robot.core.components.Transport.BallIntake;
import frc.robot.core.utils.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.core.Robot;
import frc.robot.core.components.ControlSystem.AuxillaryButtons;

class BallSuck {
void ballIntake() {
if (Robot.controlSystem.getButton(AuxillaryButtons.TEMP)) {
Hardware.TransportIntake.set(ControlMode.PercentOutput, 0.1);
  }         
 }
}