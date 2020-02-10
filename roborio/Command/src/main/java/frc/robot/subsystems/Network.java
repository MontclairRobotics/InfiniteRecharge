package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Network {

    public boolean getPort(){
        return SmartDashboard.getBoolean("port visible",false);
    }

    public double getPortX() {
        return(SmartDashboard.getNumber("portX", 0));
    }
}
