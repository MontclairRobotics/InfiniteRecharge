package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;

public class LauncherSubsystem extends SubsystemBase {

    private final CANSparkMax launcher = new CANSparkMax(Constants.LauncherConstants.kLauncherPort, kBrushless);

    public void setSpeed(double speed){
        launcher.set(speed);
    }
}
