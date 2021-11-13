/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

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
        public static final int kFrontLeftMotorPort = 3;
        public static final int kFrontRightMotorPort = 9;
        public static final int kBackLeftMotorPort = 11;
        public static final int kBackRightMotorPort = 7;

        public static final double kEncoderCPR = 42;
        public static final double kWheelDiameterInches = 6;
        public static final double kGearboxRatioMotorToOut = 8.0 / 1.0;
        public static final double kEncoderDistancePerPulse =
            // Assumes the encoders are on a 8:1 gearbox
            ((kWheelDiameterInches/*in*/ * Math.PI /*r o*/) / /*r m*/ kGearboxRatioMotorToOut); // / kEncoderCPR; //take off encoder cpr

        public static final double kTurnP = 1;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;

        public static final double kTurnToleranceDeg = 5;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

        private static final double kTimeForEase = 0.25;
        public static final double kDriveSlope = 1 / (kTimeForEase * 1000);
        
        private static final double kTimeForTurnEase = 0.1;
        public static final double kDriveTurnSlope = 1 / (kTimeForTurnEase * 1000);
        
        public static final double kDriveSnapFactor = 0.3;

        public static final double kTurnFactor = 0.9;
        public static final double kTurnCompensate = -0.05;
    }

    public static final class ShooterConstants {
        public static final int kShooterPort = 6;
    }

    public static final class TransportConstants {
        public static final int kTransportTopPort = 5;
        public static final int kTransportBotPort = 15;
    }

    public static final class IntakeConstants{
        public static final int kIntakeDeployPort = 14;
        public static final int kIntakeWheelPort = 18;
        public static final int kIntakeLimitSwitchPort = 1;
    }

    public static final class LiftConstants{
        public static final int kLiftPort = 4;
        public static final int kWinchPort = 2; 
    }

    public static final class ControlPanelManipulatorConstants {
        public static final I2C.Port kColorSensorPort = I2C.Port.kMXP;
        public static final int kWheelMotorPort = 4;
    }

    public static final class VisionPIDConstants {
        public static final double kVisionTurnP = 1;
        public static final double kVisionTurnI = 0;
        public static final double kVisionTurnD = 0;
    }

    public static final class IOConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kAuxiliaryControllerPort = 1;
    }
}