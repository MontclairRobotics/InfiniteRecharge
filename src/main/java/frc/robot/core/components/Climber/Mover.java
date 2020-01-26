package frc.robot.core.components.Climber;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.core.components.ControlSystem.Axis;
import frc.robot.core.components.ControlSystem.Controllers;
import frc.robot.core.Robot;


interface MoverBase {
    void shift();
}
    
public class Mover implements MoverBase {
    SpeedController moverController;
    
    public void shift() {
        moverController.set(Robot.controlSystem.getJoystickAxis(Controllers.AUXILLARY, Axis.X) / 2);
    }
}