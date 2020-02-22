package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorArm;

import static frc.robot.RobotContainer.colorArm;

public class PositionalControlPanel extends CommandBase {

    ColorArm.Colors tgtColor;

    @Override
    public void execute() {
        colorArm.enableWheelMotor();
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
            switch (gameData.charAt(0)) {
                case 'B' :
                    tgtColor = ColorArm.Colors.BLUE;
                    break;
                case 'G' :
                    tgtColor = ColorArm.Colors.GREEN;
                    break;
                case 'R' :
                    tgtColor = ColorArm.Colors.RED;
                    break;
                case 'Y' :
                    tgtColor = ColorArm.Colors.YELLOW;
                    break;
                default :
                    tgtColor = null;
                    break;
            }
        } else {
            tgtColor = null;
        }
    }

    @Override
    public boolean isFinished() {
        return colorArm.getColor().equals(tgtColor);
    }

    @Override
    public void end(boolean interrupted) {
        colorArm.disableWheelMotor();
    }
}
