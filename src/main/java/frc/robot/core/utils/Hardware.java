package frc.robot.core.utils;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;



public class Hardware {
    //TODO All device ids need to be changed
    //DriveTrain
    public static CANSparkMax DT_FR = new CANSparkMax(0, MotorType.kBrushless);
    public static CANSparkMax DT_BR = new CANSparkMax(1, MotorType.kBrushless);
    public static CANSparkMax DT_FL = new CANSparkMax(2, MotorType.kBrushless);
    public static CANSparkMax DT_BL = new CANSparkMax(3, MotorType.kBrushless);
    public static AHRS navx; {
    try {
        navx = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
        DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    }
    //Climber
    public static TalonSRX ClimbArm_L = new TalonSRX(0);
    public static TalonSRX ClimbArm_R = new TalonSRX(1);
    public static TalonSRX ClimbWinch_L = new TalonSRX(2);
    public static TalonSRX ClimbWinch_R = new TalonSRX(3);
    public static TalonSRX ClimbMover = new TalonSRX(4);
    //Launcher
    public static TalonSRX LauncherMotor = new TalonSRX(5);
    //Transport
    public static TalonSRX TransportIntake = new TalonSRX(6);
    public static TalonSRX TransportOutput = new TalonSRX(7);
    //WheelOfFortune
    public static TalonSRX ArmMover = new TalonSRX(8);
    public static TalonSRX WheelMover = new TalonSRX(9);
    public static I2C.Port i2cPort = I2C.Port.kOnboard;
    public static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

}