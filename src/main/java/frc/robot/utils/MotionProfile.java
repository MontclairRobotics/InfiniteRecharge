package frc.robot.utils;

import java.util.function.Function;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionProfile {
    Input<Double> targetSpeed;
    Input<Double> currentSpeed;
    private double accelLimit;
    private double lastTime;
    private double output = 0;

    public MotionProfile(Input<Double> targetSpeed, Input<Double> currentSpeed, double accelLimit){
        this.targetSpeed = targetSpeed;
        this.accelLimit = accelLimit;
        this.currentSpeed = currentSpeed;
    }

    public double getOutput(){
        double dTime = System.currentTimeMillis() - lastTime;
        SmartDashboard.putNumber("Target Speed", targetSpeed.get());
        if(targetSpeed.get() < 0.1){
            return 0;
            
        }
        if(Math.abs(targetSpeed.get() - currentSpeed.get()) < 0.1){
            return targetSpeed.get();
        }
        if(dTime > accelLimit){
            output += 0.05 * (targetSpeed.get() - currentSpeed.get() < 0 ? 1 : -1);
            lastTime = System.currentTimeMillis();
        }
        SmartDashboard.putNumber("motionOutput", output);
        return output;
    }
}