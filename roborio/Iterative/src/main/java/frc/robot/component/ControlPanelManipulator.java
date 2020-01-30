package frc.robot.component;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.utils.RobotComponent;
import frc.robot.utils.Utils;
import frc.robot.utils.fsm.FiniteStateMachine;


//TODO: Complete
public class ControlPanelManipulator implements RobotComponent {

    CANSparkMax armMotor;
    CANSparkMax wheelMotor;

    FiniteStateMachine fsm;

    private enum Colors{
        RED, YELLOW, CYAN, GREEN, UNKNOWN
    }

    public ControlPanelManipulator(){
        armMotor = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless); //TODO
        wheelMotor = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless); //TODO

    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void robotPeriodic() {

    }

    /**
     * Uses color sensor to identify state of control panel
     * @return enum color
     */
   private Colors getColor(){
        ColorSensorV3 colorSensorV3 = new ColorSensorV3(I2C.Port.kOnboard);
        if (Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
                Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
                Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255)){
            return Colors.CYAN;
       }else if (Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
                Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
                Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255)){
            return Colors.RED;
        }else if (Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
                Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
                Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255)){
            return Colors.GREEN;
        }if (Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
               Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255) &&
               Utils.threshold(colorSensorV3.getRawColor().blue, 0, 255)){
           return Colors.YELLOW;
       }
        return Colors.UNKNOWN;
    }





}
