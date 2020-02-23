package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;

import com.revrobotics.CANEncoder;

public class LauncherSubsystem extends SubsystemBase {

    private final CANSparkMax launcher = new CANSparkMax(Constants.LauncherConstants.kLauncherPort, kBrushless);
    private final CANEncoder launchEncoder = new CANEncoder(launcher);
    public void setSpeed(double speed){
        launcher.set(speed);
    }
    public double getEncoder() {
        launchEncoder.setVelocityConversionFactor(0.00017618);
        double currVelocity = launchEncoder.getVelocity();
        SmartDashboard.putNumber("Launcher Velocity Actual", currVelocity);
        return currVelocity;
    }
}
