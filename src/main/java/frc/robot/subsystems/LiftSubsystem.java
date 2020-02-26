package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

public class LiftSubsystem {

    CANSparkMax mainLeft;
    CANSparkMax mainRight;

    CANSparkMax winchLeft;
    CANSparkMax winchRight;

    public LiftSubsystem(){}

    public void setMainSpeed(double speed){
        mainLeft.set(speed);
        mainRight.set(speed);
    }

    public void setWinchSpeed(double speed){
        winchLeft.set(speed);
        winchRight.set(speed);
    }
}
