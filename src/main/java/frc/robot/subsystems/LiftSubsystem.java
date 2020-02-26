package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;


import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.LiftConstants.kLiftMainPort;
import static frc.robot.Constants.LiftConstants.kLiftWinchPort;

public class LiftSubsystem {

    CANSparkMax main = new CANSparkMax(kLiftMainPort, kBrushless);
    CANSparkMax winch = new CANSparkMax(kLiftWinchPort, kBrushless);

    public LiftSubsystem(){}

    public void setMainSpeed(double speed){
        main.set(speed);
    }

    public void setWinchSpeed(double speed){
        winch.set(speed);
    }
}
