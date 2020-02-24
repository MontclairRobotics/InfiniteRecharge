package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Hardware {
    public static final CANSparkMax DT_FL = new CANSparkMax(2, MotorType.kBrushless);
    public static final CANSparkMax DT_FR = new CANSparkMax(1, MotorType.kBrushless);
    public static final CANSparkMax DT_BL = new CANSparkMax(9, MotorType.kBrushless);
    public static final CANSparkMax DT_BR = new CANSparkMax(7, MotorType.kBrushless);

    public static final CANSparkMax transportBottom = new CANSparkMax(3, MotorType.kBrushless); //Not final ID;
    public static final CANSparkMax transportTop  = new CANSparkMax(5, MotorType.kBrushless);
    public static final CANSparkMax shooter = new CANSparkMax(6, MotorType.kBrushless);

    public static final CANSparkMax lift = new CANSparkMax(4, MotorType.kBrushless);
    public Hardware() {}

    public static void robotInit() {
    }
}