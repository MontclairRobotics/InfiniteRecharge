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
    public static final class DriveConstants {
        public static final double kThetaCorrectionSpeed = 1; //TODO: TUNE
        public static final double kThetaCorrectionThreshold = 1; //TODO: TUNE
        public static final double kThetaCorrectionOffset = 1; //TODO: TUNE
    }
    public static final class LiftConstants {
        public static final double kLiftSpeed = 1; //TODO: Tune
        public static final double kClimbSpeed = 1; //TODO: Tune
    }
    public static final class Ports {
        //None of these ports are final
        public static final int kFrontLeft = 1;
        public static final int kBackLeft = 2;
        public static final int kFrontRight = 3;
        public static final int kBackRight = 4;

        public static final int kIntakeLimit = 1;
        public static final int kLiftLimit = 2;
    }
    public static final class ControlConstants {
        public static final int kDriver = 0;
        public static final int kOperator = 1;

        public static final int kLeftX = 0;
        public static final int kLeftY = 1;

        public static final int kRightX = 4;
        public static final int kRightY = 5;




        //TODO: Confirm these, these numbers are placeholders
        public static final int kA = 1;
        public static final int kB = 2;
        public static final int kX = 3;
        public static final int kY = 4;

        public static final int kLB = 5;
        public static final int kRB = 6;

        public static final int kSelect = 7;
        public static final int kStart = 8;

        public static final int kLStick = 9;
        public static final int kRStick = 10;
    }
    public static final class PIDConstants {
        //TODO: Tune
        public static final int kP_Gyro = 1;
        public static final int kI_Gyro = 0;
        public static final int kD_Gyro = 0;
    }
    public static final class TransportConstants{
        public static final int kTransportSpeed = 1;
    }
    public static final class IntakeConstants{
        public static final double kIntakeSpeed = 1;
		public static final double kIntakeChange = 1;
    }
    public static final class LauncherConstants {
        public static final double kLauncherSpeed = 1; //TODO: Tune
        public static final double kLauncherError = 0; //TODO: Tune
    }
    public static final class VisionConstants {
        public static final double kAlignmentThreshold = 1;
        public static final double[] kTarget = new double[]{-1,2}; //must be two long
    }
}