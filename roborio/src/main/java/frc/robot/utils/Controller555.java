package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller555 {

    private Joystick joystick;

    public Joystick getUnderlying() {return joystick;}

    public final JoystickButton RB = new JoystickButton(joystick, 4);
    public final JoystickButton LB = new JoystickButton(joystick, 5);

    public final JoystickButton START = new JoystickButton(joystick, 6);
    public final JoystickButton BACK = new JoystickButton(joystick, 7);

    public final JoystickButton A = new JoystickButton(joystick, 0);
    public final JoystickButton B = new JoystickButton(joystick, 1);
    public final JoystickButton X = new JoystickButton(joystick, 3);
    public final JoystickButton Y = new JoystickButton(joystick, 2);

    public final JoystickButton RTR = new JoystickButton(joystick, 8);
    public final JoystickButton LTR = new JoystickButton(joystick, 9);

    public Controller555(int port) {
        joystick = new Joystick(port);
    }

    public Controller555() {
        this(0);
    }

}