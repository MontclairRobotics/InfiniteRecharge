package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class Controls {

    public static XboxController driver;
    public static XboxController auxiliary;

    public class Port{
        public static final int DRIVE_STICK = 0;
        public static final int AUX_STICK = 1;
    }

    public Controls(){
        driver = new XboxController(Port.DRIVE_STICK);
        auxiliary = new XboxController(Port.AUX_STICK);
    }

}