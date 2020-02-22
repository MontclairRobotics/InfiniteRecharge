package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.IntakeConstants.kIntakeArmPort;
import static frc.robot.Constants.IntakeConstants.kIntakeWheelPort;


public class IntakeSubsystem extends SubsystemBase {

    private final CANSparkMax intakeArm = new CANSparkMax(kIntakeArmPort, kBrushless);
    private final CANSparkMax intakeWheel = new CANSparkMax(kIntakeWheelPort, kBrushless);

    // TODO
    private final CANDigitalInput lowerLimitSwitch = new CANDigitalInput(intakeArm,
            CANDigitalInput.LimitSwitch.kForward, CANDigitalInput.LimitSwitchPolarity.kNormallyClosed);

    // TODO
    private final CANDigitalInput upperLimitSwitch = new CANDigitalInput(intakeArm,
            CANDigitalInput.LimitSwitch.kForward, CANDigitalInput.LimitSwitchPolarity.kNormallyClosed);

    // Note: ONLY INTAKE DIRECTION (Will break chain if inverted)
    public void setIntakeArmSpeed(double speed){
        intakeArm.set(Math.abs(speed));
    }

    public void setIntakeWheelSpeed(double speed){
        intakeWheel.set(speed);
    }

    public boolean getLowerLimit(){
        return lowerLimitSwitch.get();
    }

    public boolean getUpperLimit(){
        return upperLimitSwitch.get();
    }
}
