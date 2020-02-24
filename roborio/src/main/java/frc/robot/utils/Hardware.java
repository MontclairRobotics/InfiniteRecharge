package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Hardware {
    public static final CANSparkMax DT_FL;
    public static final CANSparkMax DT_FR;
    public static final CANSparkMax DT_BL;
    public static final CANSparkMax DT_BR;
    public static final CANSparkMax transportBottom;
    public static final CANSparkMax transportTop;
    public static final CANSparkMax shooter;
    public static final CANSparkMax lift;
    public Hardware() {}

    public static void robotInit() {

        //Motor Types are all set to brushless until we know otherwise, some ID's are temporary
        DT_FL = new CANSparkMax(2, MotorType.kBrushless);
        DT_BL = new CANSparkMax(1, MotorType.kBrushless);
        DT_FR = new CANSparkMax(9, MotorType.kBrushless);
        DT_BR = new CANSparkMax(7, MotorType.kBrushless);

        transportBottom = new CANSparkMax(3, MotorType.kBrushless); //Not final ID
        transportTop = new CANSparkMax(5, MotorType.kBrushless);
        
        shooter = new CANSparkMax(6, MotorType.kBrushless);

        lift = new CANSparkMax(4, MotorType.kBrushless); //Not final ID
    }
}