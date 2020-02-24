package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Hardware {
    public static final CANSparkMax DT_FL = new CANSparkMax(2, MotorType.kBrushless);
    public static final CANSparkMax DT_FR = new CANSparkMax(1, MotorType.kBrushless);
    public static final CANSparkMax DT_BL = new CANSparkMax(9, MotorType.kBrushless);
    public static final CANSparkMax DT_BR = new CANSparkMax(7, MotorType.kBrushless);

    public static final CANSparkMax transportBottom = new CANSparkMax(3, MotorType.kBrushless); //Not final ID;
    public static final CANSparkMax transportTop  = new CANSparkMax(5, MotorType.kBrushless);
    public static final CANSparkMax shooter = new CANSparkMax(6, MotorType.kBrushless);

    public static final CANSparkMax leftWinch = new CANSparkMax(10, MotorType.kBrushless);
    public static final CANSparkMax rightWinch = new CANSparkMax(11, MotorType.kBrushless);
    public static final SpeedControllerGroup winches = new SpeedControllerGroup(leftWinch, rightWinch);
    
    public static final CANSparkMax leftLiftArm = new CANSparkMax(12, MotorType.kBrushless);
    public static final CANSparkMax rightLiftArm = new CANSparkMax(13, MotorType.kBrushless);
    public static final SpeedControllerGroup liftArms = new SpeedControllerGroup(leftLiftArm, rightLiftArm);

    public Hardware() {}

}