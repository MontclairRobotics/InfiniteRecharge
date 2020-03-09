package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelManipulatorSubsystem;

public class PositionalControlPanel extends CommandBase {

    ControlPanelManipulatorSubsystem controlPanelManipulatorSubsystem;

    ControlPanelManipulatorSubsystem.Colors tgtColor;

    public PositionalControlPanel(ControlPanelManipulatorSubsystem controlPanelManipulatorSubsystem){
        this.controlPanelManipulatorSubsystem = controlPanelManipulatorSubsystem;
    }

    @Override
    public void execute() {
        controlPanelManipulatorSubsystem.enableWheelMotor();
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
            switch (gameData.charAt(0)) {
                case 'B' :
                    tgtColor = ControlPanelManipulatorSubsystem.Colors.BLUE;
                    break;
                case 'G' :
                    tgtColor = ControlPanelManipulatorSubsystem.Colors.GREEN;
                    break;
                case 'R' :
                    tgtColor = ControlPanelManipulatorSubsystem.Colors.RED;
                    break;
                case 'Y' :
                    tgtColor = ControlPanelManipulatorSubsystem.Colors.YELLOW;
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
        return controlPanelManipulatorSubsystem.getColor().equals(tgtColor);
    }

    @Override
    public void end(boolean interrupted) {
        controlPanelManipulatorSubsystem.disableWheelMotor();
    }
}
