package frc.robot.core.components.WheelOfFortune;
import edu.wpi.first.wpilibj.DriverStation;

interface ColorGetterFromFieldrinterface {
    char getColorToFind();
}

class ColorGetterFromField implements ColorGetterFromFieldrinterface {
    public char getColorToFind() {
        char returnVal = 'n';
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
            returnVal = gameData.charAt(0);
        }
        return(returnVal);
    }
}