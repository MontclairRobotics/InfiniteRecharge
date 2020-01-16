package frc.robot.core.components.Climber;

import frc.robot.core.components.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

interface climberControls {
    double getJoystickAxis(ControlSystem.Controllers joystick, ControlSystem.Axis axis);
    boolean getButton(ControlSystem.AuxillaryButtons button);
}

public class ClimberControlSystem implements climberControls{
    ControlSystem climberControls;
    
    public double getJoystickAxis(ControlSystem.Controllers joystick, ControlSystem.Axis axis) {
        return(climberControls.getJoystickAxis(joystick, axis));
    }
    public boolean getButton(ControlSystem.AuxillaryButtons button) {
        return(climberControls.getButton(button));
    }
}