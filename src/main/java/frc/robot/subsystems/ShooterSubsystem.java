package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.ShooterConstants.kShooterPort;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax launcher = new CANSparkMax(kShooterPort, kBrushless);
    private final CANEncoder launcherEncoder = new CANEncoder(launcher);

    public void setSpeed(double speed){
        launcher.set(speed);
    }

    public double getEncoder() {
        launcherEncoder.setVelocityConversionFactor(0.0001761804);
        return launcherEncoder.getVelocity();
    }
}
