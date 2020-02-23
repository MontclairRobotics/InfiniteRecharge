package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.RobotContainer.*;
import static frc.robot.utils.Utils.rumbleAux;
import static frc.robot.utils.Utils.rumbleDriver;

public class Shoot extends CommandBase {

    @Override
    public void execute() {
        double speed = 1;// TODO need to add in ratio of distance we are away:speed of motor
        launcherSubsystem.setSpeed(speed);
        if (Math.abs(launcherSubsystem.getEncoder() - speed) < 0.1) {
            transportSubsystem.setTransportSpeed(1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        launcherSubsystem.setSpeed(-0.1);
        rumbleDriver(false);
        rumbleAux(false);
        transportSubsystem.ballCount = 0;
    }

}
