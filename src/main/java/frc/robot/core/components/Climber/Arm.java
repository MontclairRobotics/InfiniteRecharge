package frc.robot.core.components.Climber;

import edu.wpi.first.wpilibj.SpeedController;

interface armBase {
    void extend();
    void retract();
}

public class Arm implements armBase{
 SpeedController arm;

    public void extend() {
        arm.set(0.1);// need to change
    }
    
    public void retract() {
        arm.set(-0.1);// need to change
    }
}