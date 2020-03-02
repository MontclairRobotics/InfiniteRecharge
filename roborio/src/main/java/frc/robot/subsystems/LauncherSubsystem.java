package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.utils.Constants.LauncherConstants.kLauncherError;
import static frc.robot.utils.Constants.LauncherConstants.kLauncherSpeed;
import frc.robot.utils.Hardware;

public class LauncherSubsystem extends SubsystemBase{

    public LauncherSubsystem() {
    }

    public void startLauncher() {
        Hardware.shooter.set(kLauncherSpeed);
    }

    public void stopLauncher() {
        Hardware.shooter.set(0);
    }

    public boolean getLauncherRevved() {
        return Hardware.shooter.getEncoder().getVelocity() >= kLauncherSpeed - kLauncherError;
    }
    
}