/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class LiftConstants {
        public static final double kLiftSpeed = 1; //TODO: Tune
        public static final double kClimbSpeed = 1; //TODO: Tune
    }
    public static final class Ports {
        //None of these ports are final
        public static final int kFrontLeft = 1;
        public static final int kBackLeft = 2;
        public static final int kFrontRight = 3;
        public static final int kDT_BackRight = 4;

        public static final int kIntakeLimit = 1;
        public static final int kLeftLiftLimit = 2;
        public static final int kRightLiftLimit = 3;
    }
    public static final class TransportConstants{
        public static final int kIntakeSpeed = 1;
    }
    public static final class LauncherConstants {
        public static final double kLauncherSpeed = 1; //TODO: Tune
        public static final double kLauncherError = 0; //TODO: Tune
    }
}