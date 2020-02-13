package frc.robot.core.utils;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.core.components.*;
import frc.robot.core.components.Transport.BallIntake.BallSuck;
import frc.robot.core.components.Transport.BallIntake.IntakeArm;


public class Hardware {
    //TODO: All device ids need to be changed
    //DriveTrain
    public static CANSparkMax DT_FR = new CANSparkMax(1, MotorType.kBrushless);
    public static CANSparkMax DT_BR = new CANSparkMax(2, MotorType.kBrushless);
    public static CANSparkMax DT_FL = new CANSparkMax(3, MotorType.kBrushless);
    public static CANSparkMax DT_BL = new CANSparkMax(4, MotorType.kBrushless);
    public static AHRS navx; {
    try {
        navx = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
        DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    }
    //Climber

    //These IDs haven't been set
    public final static CANSparkMax ClimbArm_L = new CANSparkMax(5, MotorType.kBrushed);
    public final static CANSparkMax ClimbArm_R = new CANSparkMax(6, MotorType.kBrushed);
    public final static CANSparkMax ClimbWinch_L = new CANSparkMax(7, MotorType.kBrushed);
    public final static CANSparkMax ClimbWinch_R = new CANSparkMax(8, MotorType.kBrushed);
    public final static CANSparkMax ClimbMover = new CANSparkMax(9, MotorType.kBrushed);
    //Launcher
    public final static CANSparkMax LauncherMotor = new CANSparkMax(10, MotorType.kBrushless);
    public final static CANEncoder LauncherEncoder = new CANEncoder(LauncherMotor);
    //Transport
    public final static CANSparkMax TransportIntakeWheel = new CANSparkMax(11, MotorType.kBrushless);
    public final static CANSparkMax IntakeArm = new CANSparkMax(12, MotorType.kBrushless);
    public final static CANSparkMax TransportBelt = new CANSparkMax(13, MotorType.kBrushless);
    public final static BallSuck BallIntakeHandler = new BallSuck();
    public final static IntakeArm IntakeArmHandler = new IntakeArm();
    //WheelOfFortune
    //public final static CANSparkMax ArmMover = new CANSparkMax(14, MotorType.kBrushed);
    //public final static CANSparkMax WheelMover = new CANSparkMax(15, MotorType.kBrushed);
    public final static I2C.Port i2cPort = I2C.Port.kOnboard;
    public final static ColorSensor m_colorSensor = new ColorSensor( new ColorSensorV3(i2cPort) );

}