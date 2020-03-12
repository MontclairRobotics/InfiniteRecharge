package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.utils.Constants.Ports;

public final class Hardware {

    //IDs might not be final, check with electronics before testing.
    //MAKE SURE MOTORS ARE SET TO THE CORRECT PREFERENCE (BRUSHED/BRUSHLESS ON THE REV ROBOTICS SOFTWARE)
    public static final CANSparkMax DT_FL = new CANSparkMax(7, MotorType.kBrushless);
    public static final CANSparkMax DT_FR = new CANSparkMax(1, MotorType.kBrushless);
    public static final CANSparkMax DT_BL = new CANSparkMax(9, MotorType.kBrushless);
    public static final CANSparkMax DT_BR = new CANSparkMax(3, MotorType.kBrushless);

    public static final CANSparkMax transportBottom = new CANSparkMax(10, MotorType.kBrushless); //Not final ID;
    public static final CANSparkMax transportTop  = new CANSparkMax(5, MotorType.kBrushless);
    public static final CANSparkMax shooter = new CANSparkMax(6, MotorType.kBrushless);

    //public static final CANSparkMax leftWinch = new CANSparkMax(10, MotorType.kBrushless); //WRONG ID!!
    //public static final CANSparkMax rightWinch = new CANSparkMax(11, MotorType.kBrushless); //WRONG ID!!
    
    //public static final CANSparkMax leftLiftArm = new CANSparkMax(12, MotorType.kBrushless); //Not final ID
    //public static final CANSparkMax rightLiftArm = new CANSparkMax(2, MotorType.kBrushless);

    public static final CANSparkMax intake = new CANSparkMax(18, MotorType.kBrushless); //Not final ID
    //public static final CANSparkMax intakeLeft = new CANSparkMax(13, MotorType.kBrushed);
    //public static final CANSparkMax intakeRight = new CANSparkMax(4, MotorType.kBrushed);


    //public static final DigitalInput liftLimit = new DigitalInput(Ports.kLiftLimit);
}