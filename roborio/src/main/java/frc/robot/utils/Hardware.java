package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.utils.Constants.Ports;

public final class Hardware {

    //IDs might not be final, check with electronics before testing.
    //MAKE SURE MOTORS ARE SET TO THE CORRECT PREFERENCE (BRUSHED/BRUSHLESS ON THE REV ROBOTICS SOFTWARE)
    public static final CANSparkMax DT_FL = new CANSparkMax(Ports.kFrontLeft, MotorType.kBrushless);
    public static final CANSparkMax DT_FR = new CANSparkMax(Ports.kFrontRight, MotorType.kBrushless);
    public static final CANSparkMax DT_BL = new CANSparkMax(Ports.kBackLeft, MotorType.kBrushless);
    public static final CANSparkMax DT_BR = new CANSparkMax(Ports.kBackRight, MotorType.kBrushless);

    public static final CANSparkMax transportBottom = new CANSparkMax(3, MotorType.kBrushless);
    public static final CANSparkMax transportTop  = new CANSparkMax(5, MotorType.kBrushless);
    public static final CANSparkMax shooter = new CANSparkMax(6, MotorType.kBrushless);

    public static final CANSparkMax leftWinch = new CANSparkMax(10, MotorType.kBrushless);
    public static final CANSparkMax rightWinch = new CANSparkMax(11, MotorType.kBrushless);
    
    public static final CANSparkMax leftLiftArm = new CANSparkMax(12, MotorType.kBrushless);
    public static final CANSparkMax rightLiftArm = new CANSparkMax(13, MotorType.kBrushless);

    public static final DigitalInput liftLimit = new DigitalInput(Ports.kLiftLimit);
}