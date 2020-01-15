package frc.robot.core.components.Climber;

import frc.robot.core.components.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

interface MoverBase {
    void balance();
    void shift(double speed, double distance);
}
    
public class Move implements MoverBase {
    SpeedController moverController;
    ClimberControlSystem moverControls;

    public void balance() {
        
    }
    
    public void shift(double speed, double distance) {
        moverController.set(moverControls.getJoystickAxis(null));
    }
}