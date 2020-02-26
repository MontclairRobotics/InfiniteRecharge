package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed;
import static frc.robot.Constants.IntakeConstants.*;


public class IntakeSubsystem extends SubsystemBase {

    private final CANSparkMax deployLeft = new CANSparkMax(kIntakeDeployLeftPort, kBrushed);
    private final CANSparkMax deployRight = new CANSparkMax(kIntakeDeployLeftPort, kBrushed);

    private final CANSparkMax intake = new CANSparkMax(kIntakeWheelPort, kBrushed);

    private final DigitalInput deployLimitSwitch = new DigitalInput(kIntakeDeployLimitSwitchPort);

    // Note: ONLY INTAKE DIRECTION (Will break chain if inverted)
    public void setIntakeSpeed(double speed){
        intake.set(Math.abs(speed));
    }

    public boolean getLimitSwitch(){
        return deployLimitSwitch.get();
    }

    public void setDeploySpeed(double speed){
        deployLeft.set(speed);
        deployRight.set(speed);
    }
}
