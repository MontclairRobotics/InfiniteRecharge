package frc.robot.core.components.Transport.BallIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.core.utils.Hardware;

public class IntakeArm {
    public static void armLower(double speed) {
        Hardware.IntakeArm.set(ControlMode.PercentOutput, speed);
    }
    public void armRaise(double speed) {
        Hardware.IntakeArm.set(ControlMode.PercentOutput, -speed);
    }
}