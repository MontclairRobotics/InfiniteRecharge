package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.RobotContainer.intakeSubsystem;
import static frc.robot.RobotContainer.transportSubsystem;

public class IntakeBalls extends CommandBase {


    @Override
    public void execute() {
        double topTranportSpeed = 0;
        double botTransportSpeed = 0;
        boolean hadSpace = true;
        intakeSubsystem.setIntakeSpeed(0.5);
        topTranportSpeed = 0.7;
        botTransportSpeed = -0.7;
        if (transportSubsystem.getIsBall() && hadSpace == true) {
            botTransportSpeed = 0.7;
            hadSpace = false;
        } else if (!transportSubsystem.getIsBall()) {
            hadSpace = true;
        }
        transportSubsystem.setTransportSpeed(topTranportSpeed, botTransportSpeed);
    }

    @Override
    public boolean isFinished() {
        boolean returnVal = false;
        if (transportSubsystem.getBallCount() == 5) {
            returnVal = true;
        }
        return returnVal;
    }
}