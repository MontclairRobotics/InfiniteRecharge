package frc.robot.utils;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.utils.Constants.ControlConstants;

// TODO: Make sure IDs are correct
public class Controllers {
    // public static final Controller555 driver = new Controller555(0);
    // public static final Controller555 auxillary = new Controller555(1);

    public static final Joystick driver = new Joystick(ControlConstants.kDriver);
    public static final Joystick operator = new Joystick(ControlConstants.kOperator);

    public static class Buttons {
        //Driver Buttons (Control Scheme will likely/definitely be changed)
        public static final JoystickButton gyroLock = new JoystickButton(driver, ControlConstants.kA);
        public static final JoystickButton fullSpeed = new JoystickButton(driver, ControlConstants.kLStick);
        public static final JoystickButton quarterSpeed = new JoystickButton(driver, ControlConstants.kLB);
        public static final JoystickButton shoot = new JoystickButton(driver, ControlConstants.kB);
        public static final JoystickButton invert = new JoystickButton(driver, ControlConstants.kRB);

        
        //Operator Buttons (Control Scheme will likely/definitely be changed)
        public static final JoystickButton intake = new JoystickButton(operator, ControlConstants.kA);
        public static final JoystickButton raiseLift = new JoystickButton(operator, ControlConstants.kX);
        public static final JoystickButton lowerLift = new JoystickButton(operator, ControlConstants.kY);

    }
}