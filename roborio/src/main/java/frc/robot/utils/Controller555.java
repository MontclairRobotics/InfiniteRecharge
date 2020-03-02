package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller555 {

    public final Joystick This;

    public final JoystickButton D_UP = new JoystickButton(This, 0);
    public final JoystickButton D_DOWN = new JoystickButton(This, 2);
    public final JoystickButton D_LEFT = new JoystickButton(This, 1);
    public final JoystickButton D_RIGHT = new JoystickButton(This, 3);

    public final JoystickButton RT = new JoystickButton(This, 0);
    public final JoystickButton LT = new JoystickButton(This, 0);
    public final JoystickButton RZ = new JoystickButton(This, 0);
    public final JoystickButton LZ = new JoystickButton(This, 0);

    public final JoystickButton SQUARE = new JoystickButton(This, 0);
    public final JoystickButton CIRCLE = new JoystickButton(This, 0);
    public final JoystickButton TRIANGLE = new JoystickButton(This, 0);
    public final JoystickButton X = new JoystickButton(This, 0);

    public Controller555(int port) {
        This = new Joystick(port);
    }

}