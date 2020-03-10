package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.IntakeConstants.*;


public class IntakeSubsystem extends SubsystemBase {


    private final CANSparkMax intake = new CANSparkMax(kIntakeWheelPort, MotorType.kBrushless);

    private final DigitalInput limitSwitch = new DigitalInput(kIntakeLimitSwitchPort);

    public void setIntakeSpeed(double speed){
        intake.set(speed);
    }

    public boolean atLimit(){
        return limitSwitch.get();
    }

}
