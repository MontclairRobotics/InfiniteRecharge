package frc.robot.component;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Launcher{

    CANSparkMax launchMotor;
    CANSparkMax flapMotor;
    CANSparkMax reloadMotor;

    public Launcher() {
        this.launchMotor = new CANSparkMax(5, MotorType.kBrushless);
        this.flapMotor = new CANSparkMax(6, MotorType.kBrushless);
        this.reloadMotor = new CANSparkMax(7, MotorType.kBrushless);
    }

    public void enable(){
        launchMotor.set(1);
    }

    public void enable(boolean inversion){
        launchMotor.setInverted(inversion);
        launchMotor.set(1);
    }

    public void enable(double power){
        launchMotor.set(power);

    }

    public void reload(){
        // TODO
    }

    public void setLaunchAngle(){
        // TODO
    }

    public void disable(){
        launchMotor.set(0);
    }
}