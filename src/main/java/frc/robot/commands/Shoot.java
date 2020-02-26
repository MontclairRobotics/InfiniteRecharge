package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.RobotContainer.*;
import static frc.robot.utils.Utils.rumbleAux;
import static frc.robot.utils.Utils.rumbleDriver;

public class Shoot extends CommandBase {

    @Override
    public void execute() {
        shooterSubsystem.setSpeed(1);
        transportSubsystem.setTransportSpeed(1);
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.setSpeed(-0.01);
        transportSubsystem.setTransportSpeed(0);
        rumbleDriver(false);
        rumbleAux(false);
    }

}
