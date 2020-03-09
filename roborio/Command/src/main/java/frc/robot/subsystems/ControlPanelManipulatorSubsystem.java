package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Utils;

import static frc.robot.Constants.ControlPanelManipulatorConstants.kColorSensorPort;
import static frc.robot.Constants.ControlPanelManipulatorConstants.kWheelMotorPort;

public class ControlPanelManipulatorSubsystem extends SubsystemBase {

    private final ColorSensorV3 colorSensor = new ColorSensorV3(kColorSensorPort);

    private final CANSparkMax wheelMotor = new CANSparkMax(kWheelMotorPort, CANSparkMaxLowLevel.MotorType.kBrushed);

    public enum Colors{
        BLUE(0.2,0.3),
        GREEN(0.4,0.8),
        RED(3.8,4.4),
        YELLOW(2,3);

        private final double min;
        private final double max;
        private Colors(double min, double max){
            this.min = min;
            this.max = max;
        }
    }

    public Colors getColor(){
        if(Utils.threshold((double) colorSensor.getRed()/colorSensor.getBlue(),Colors.BLUE.min,Colors.BLUE.max)){
            return Colors.BLUE;
        } else if(Utils.threshold((double) colorSensor.getRed()/colorSensor.getBlue(),Colors.YELLOW.min,Colors.YELLOW.max)){
            return Colors.YELLOW;
        }else if(Utils.threshold((double) colorSensor.getRed()/colorSensor.getBlue(),Colors.RED.min,Colors.RED.max)){
            return Colors.RED;
        }else if(Utils.threshold((double) colorSensor.getRed()/colorSensor.getBlue(),Colors.GREEN.min,Colors.GREEN.max)){
            return Colors.GREEN;
        }
        return null;
    }

    public void enableWheelMotor(){
        wheelMotor.set(0.8);
    }

    public void disableWheelMotor(){
        wheelMotor.set(0);
    }



}
