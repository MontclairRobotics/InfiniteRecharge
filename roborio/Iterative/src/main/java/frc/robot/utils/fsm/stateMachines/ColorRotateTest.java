package frc.robot.utils.fsm.stateMachines;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.utils.fsm.FiniteStateMachine;
import frc.robot.utils.fsm.states.ColorRotateState;

public class ColorRotateTest extends FiniteStateMachine {

    public ColorRotateTest() {
        super("Color Rotate Test", new ColorRotateState(new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushed),new ColorSensorV3(I2C.Port.kMXP)));
    }
}
