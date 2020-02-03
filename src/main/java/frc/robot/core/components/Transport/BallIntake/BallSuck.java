package frc.robot.core.components.Transport.BallIntake;

import frc.robot.core.utils.Hardware;

public class BallSuck {
    public static void intakeBall() {
        Hardware.TransportIntakeWheel.set(0.5);
    }
    public static void stopIntaking() {
        Hardware.TransportIntakeWheel.set(0);
    }
}