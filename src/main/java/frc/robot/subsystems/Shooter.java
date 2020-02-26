package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.ShooterConstants.kShooterPort;

public class Shooter extends SubsystemBase {

    private final CANSparkMax launcher = new CANSparkMax(kShooterPort, kBrushless);

    public void setSpeed(double speed){
        launcher.set(speed);
    }
}
