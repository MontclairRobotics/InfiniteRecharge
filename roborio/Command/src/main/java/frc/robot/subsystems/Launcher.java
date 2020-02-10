package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;

public class Launcher extends SubsystemBase{
    CANSparkMax launcher = new CANSparkMax(LauncherConstants.kLauncherPort, kBrushless);
    CANSparkMax intakeArm = new CANSparkMax(LauncherConstants.kIntakeArmPort, kBrushless);
    CANSparkMax intakeWheel = new CANSparkMax(LauncherConstants.kIntakeWheelPort, kBrushless);
    CANSparkMax transport = new CANSparkMax(LauncherConstants.kTransport, kBrushless);

    public void shoot(double speed) {
        launcher.set(speed);
        transport();

    }
    public void stopAll() {
        launcher.set(0);
        intakeWheel.set(0);
        intakeArm.set(0);
        transport.set(0);
    }
    public void intakeArmLower() {
        intakeArm.set(0.5);
    }
    public void intakeArmRaise() {
        intakeArm.set(-0.5);
    }
    public void intakeBalls() {
        intakeWheel.set(0.7);
    }
    public void transport() {
        transport.set(0.5);

    }
    public void intake() {
        transport();
        intakeBalls();
    }
}
