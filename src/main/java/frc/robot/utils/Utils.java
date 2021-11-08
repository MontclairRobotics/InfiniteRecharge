package frc.robot.utils;

import edu.wpi.first.wpilibj.GenericHID;

import static frc.robot.RobotContainer.auxiliaryController;
import static frc.robot.RobotContainer.driverController;

public class Utils {

    public static boolean isAtMostMag(double input, double max) {
        return -max <= input && input <= max;
    }


    public static boolean isBetween(double input, double min, double max) {
        return (input >= min && input <= max);
    }

    public static double clamp(double input, double min, double max) {
        if (input < min) {
            return min;
        }
        if (input > max) {
            return max;
        }
        return input;
    }

    public static double clampUnordered(double input, double a, double b)
    {
        return a < b ? clamp(input, a, b) : clamp(input, b, a);
    }

    public static double signZero(double v)
    {
        if(v < 0) return -1;
        else if(v == 0) return 0;
        else return 1;
    }

    public static boolean signsDiffer(double a, double b)
    {
        return (a <= 0) ^ (b <= 0);
    }

    public static double roundToNearest(double input, double step)
    {
        return Math.round(input / step) * step;
    }

    public static void rumbleDriver(boolean rumble) {
        if (rumble) {
            driverController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
            driverController.setRumble(GenericHID.RumbleType.kRightRumble, 1);
        } else {
            driverController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
            driverController.setRumble(GenericHID.RumbleType.kRightRumble, 0);
        }
    }

    public static void rumbleAux(boolean rumble) {
        if (rumble) {
            auxiliaryController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
            auxiliaryController.setRumble(GenericHID.RumbleType.kRightRumble, 1);
        } else {
            auxiliaryController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
            auxiliaryController.setRumble(GenericHID.RumbleType.kRightRumble, 0);
        }
    }

    public static void rumble(boolean rumble) {
        rumbleDriver(rumble);
        rumbleAux(rumble);
    }

}
