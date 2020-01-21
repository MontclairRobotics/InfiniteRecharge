package frc.robot.core.utils;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;

public class Hardware {
    //TODO All device ids need to be changed
    //DriveTrain
    public final static CANSparkMax DT_FR = new CANSparkMax(0, MotorType.kBrushless);
    public final static CANSparkMax DT_BR = new CANSparkMax(1, MotorType.kBrushless);
    public final static CANSparkMax DT_FL = new CANSparkMax(2, MotorType.kBrushless);
    public final static CANSparkMax DT_BL = new CANSparkMax(3, MotorType.kBrushless);
    //Climber
    public final static TalonSRX ClimbArm_L = new TalonSRX(0);
    public final static TalonSRX ClimbArm_R = new TalonSRX(1);
    public final static TalonSRX ClimbWinch_L = new TalonSRX(2);
    public final static TalonSRX ClimbWinch_R = new TalonSRX(3);
    public final static TalonSRX ClimbMover = new TalonSRX(4);
    //Launcher
    public final static TalonSRX LauncherMotor = new TalonSRX(5);
    //Transport
    public final static TalonSRX TransportIntake = new TalonSRX(6);
    public final TalonSRX TransportOutput = new TalonSRX(7);
    //WheelOfFortune
    public final static TalonSRX ArmMover = new TalonSRX(8);
    public final static TalonSRX WheelMover = new TalonSRX(9);
    public final static I2C.Port i2cPort = I2C.Port.kOnboard;
    public final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

}