package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionProfile {
    Input<Double> targetSpeed;
    Input<Double> currentSpeed;
    private double accelLimit;
    private double lastTime;
    private double output = 0;

    public MotionProfile(Input<Double> targetSpeed, Input<Double> currentSpeed, double accelLimit) {
        this.targetSpeed = targetSpeed;
        this.accelLimit = accelLimit;
        this.currentSpeed = currentSpeed;
    }

    public double getOutput() {
        double dTime = System.currentTimeMillis() - lastTime;
        double target = targetSpeed.get();
        double current = currentSpeed.get();
        double dir = (target < 0 ? 1 : -1);

        SmartDashboard.putNumber("Current Speed", currentSpeed.get());
        SmartDashboard.putNumber("Target Speed", targetSpeed.get());

        if (Math.abs(targetSpeed.get()) < 0.1) {
            output = 0;
        }

        if (target > 0 && current > target || target < 0 && current < target) {
            return target;
        }

        if (dTime > accelLimit) {
            output += 0.05 * dir;
            output = Utils.constrain(output, -.25, .25);
            lastTime = System.currentTimeMillis();
        }
        SmartDashboard.putNumber("motionOutput", output);

        return output;
    }
}