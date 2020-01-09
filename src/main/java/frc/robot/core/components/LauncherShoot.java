package frc.robot.core.components;

import edu.wpi.first.wpilibj.SpeedController;

public class LauncherShoot implements LauncherState{
    private final SpeedController motor;

    public LauncherShoot(SpeedController motor, double velocity, double burst){
        this.motor = motor;
        motor.set(velocity);
        try {
            motor.wait();
        } catch(Exception e) {

         }
        //wait until total # of balls are fired 
    }

    public LauncherState run(){
        return this;
    }
}