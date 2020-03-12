package frc.robot.commands;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TransportSubsystem;

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
    }

    @Override
    public void end(boolean interrupted) {
        launcher.stopLauncher();
    }

}