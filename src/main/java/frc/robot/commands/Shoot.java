package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;


import static frc.robot.RobotContainer.*;
import static frc.robot.utils.Utils.rumble;

public class Shoot extends CommandBase {

    @Override
    public void execute() {
        double speed = 1;// TODO need to add in ratio of distance we are away:speed of motor
        shooterSubsystem.setSpeed(speed);
        if (Math.abs(shooterSubsystem.getEncoder() - speed) < 0.1) {
            transportSubsystem.setTransportSpeed(1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.setSpeed(-0.01);
        transportSubsystem.setTransportSpeed(0);
        rumble(false);
    }

}
