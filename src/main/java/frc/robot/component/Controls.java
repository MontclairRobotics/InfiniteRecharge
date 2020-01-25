package frc.robot.component;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {

    public static Joystick driver;
    public static Joystick auxillary;

    public class Port{
        public static final int DRIVE_STICK = 0;
        public static final int AUX_STICK = 1;
    }

    public Controls(){
        driver = new Joystick(Port.DRIVE_STICK);
        auxillary = new Joystick(Port.AUX_STICK);
    }

}