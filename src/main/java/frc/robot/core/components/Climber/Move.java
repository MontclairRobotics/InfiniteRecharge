package frc.robot.core.components.Climber;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.components.ControlSystem;
import frc.robot.core.components.ControlSystem.Axis;
import frc.robot.core.components.ControlSystem.Controllers;

interface MoverBase {
    void balance();
    void shift(double speed, double distance);
}
    
public class Move implements MoverBase {
    ControlSystem moverControls;
    SpeedController moverController;

    public void balance() {
        
    }
    
    public void shift(double speed, double distance) {
        moverController.set(moverControls.getJoystickAxis(Controllers.DRIVER, Axis.Y));
    }
}