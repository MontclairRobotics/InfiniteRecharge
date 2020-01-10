package frc.robot.core.components;

import edu.wpi.first.wpilibj.SpeedController;

public class LauncherShoot implements LauncherState{
    private final SpeedController motor;

    public LauncherShoot(SpeedController motor, double velocity, double burst){
        this.motor = motor;
        Transport transport = new Transport();
        transport.output(); 
        motor.set(velocity);
        try {
            motor.wait();
        } catch(Exception e) {

         }
        //wait until total # of balls(burst) are fired 
        motor.notify();
        motor.set(0);
    }

    public LauncherState run(){
        return this;
    }
}