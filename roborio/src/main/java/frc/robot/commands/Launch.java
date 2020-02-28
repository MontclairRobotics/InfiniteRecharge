package frc.robot.commands;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class Launch extends CommandBase {

    private final LauncherSubsystem launcher;

    public Launch(LauncherSubsystem launcher) {
        this.launcher = launcher;
        addRequirements(launcher);
    }

    @Override
    public void initialize() {
        launcher.startLauncher();
    }

    @Override
    public void execute() {
        if(launcher.getLauncherRevved()) {
            //TODO: TRANS
        }
    }

    @Override
    public void end(boolean interrupted) {
        launcher.stopLauncher();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}