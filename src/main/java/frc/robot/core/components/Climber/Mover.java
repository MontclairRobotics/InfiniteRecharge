package frc.robot.core.components.Climber;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.components.ControlSystem;
import frc.robot.core.components.ControlSystem.Axis;
import frc.robot.core.components.ControlSystem.Controllers;

interface MoverBase {
    void shift(double speed, double distance);
}
    
public class Mover implements MoverBase {
    ControlSystem moverControls;
    SpeedController moverController;
    
    public void shift(double speed, double distance) {
        moverController.set(moverControls.getJoystickAxis(Controllers.DRIVER, Axis.Y));
    }
}