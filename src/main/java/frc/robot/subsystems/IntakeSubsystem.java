package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.IntakeConstants.*;


public class IntakeSubsystem extends SubsystemBase {

    private final CANSparkMax deployLeft = new CANSparkMax(kIntakeDeployLeftPort, kBrushless);
    private final CANSparkMax deployRight = new CANSparkMax(kIntakeDeployLeftPort, kBrushless);

    private final CANSparkMax intakeWheel = new CANSparkMax(kIntakeWheelPort, kBrushless);

    // TODO
    private final DigitalInput deployLimitSwitch = new DigitalInput(kIntakeDeployLimitSwitchPort);

    // Note: ONLY INTAKE DIRECTION (Will break chain if inverted)
    public void setIntakeArmSpeed(double speed){
        intakeWheel.set(Math.abs(speed));
    }

    public void setIntakeWheelSpeed(double speed){
        intakeWheel.set(speed);
    }

    public boolean getLimitSwitch(){
        return deployLimitSwitch.get();
    }
}
