package frc.robot.core.components.Climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

interface climberControls {
    double getJoystickAxis(Joystick joystick);
    boolean getJoystickButton(JoystickButton button);  
}

public class ClimberControlSystem implements climberControls{

    @Override
    public double getJoystickAxis(Joystick joystick) {
        return(joystick.getRawAxis(0));
    }
    public boolean getJoystickButton(JoystickButton button) {
        return(button.get());
    } 
}