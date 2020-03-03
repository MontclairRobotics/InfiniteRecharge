package frc.robot.commands;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TransportSubsystem;

public class Launch extends CommandBase {

    private final LauncherSubsystem launcher;
    private final TransportSubsystem transport;

    public Launch(LauncherSubsystem launcher, TransportSubsystem transport) {
        this.launcher = launcher;
        this.transport = transport;
        addRequirements(launcher, transport);
    }

    @Override
    public void initialize() {
        launcher.startLauncher();
    }

    @Override
    public void execute() {
        if(launcher.getLauncherRevved()) {
            transport.actTransport();
        }
    }

    @Override
    public void end(boolean interrupted) {
        launcher.stopLauncher();
        transport.deactTransport();
    }

}