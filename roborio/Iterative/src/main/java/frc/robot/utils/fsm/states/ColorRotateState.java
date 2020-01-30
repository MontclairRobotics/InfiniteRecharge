package frc.robot.utils.fsm.states;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.Utils;
import frc.robot.utils.fsm.FiniteStateMachine;

// This is a test class
public class ColorRotateState implements FiniteStateMachine.State {

    CANSparkMax canSparkMax;
    ColorSensorV3 colorSensorV3;

    public ColorRotateState(CANSparkMax canSparkMax, ColorSensorV3 colorSensorV3){
        this.canSparkMax = canSparkMax;
        this.colorSensorV3 = colorSensorV3;
    }

    @Override
    public void inState() {
        canSparkMax.set(1);
        SmartDashboard.putNumber("Prox", colorSensorV3.getProximity());
        SmartDashboard.putBoolean("Blue", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),0.2,0.3));
        SmartDashboard.putBoolean("Yellow", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),2,3));
        SmartDashboard.putBoolean("Red", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),3.8,4.4));
        SmartDashboard.putBoolean("Green", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),0.4,0.8));
    }

    @Override
    public FiniteStateMachine.State nextState() {
        return null;
    }

    @Override
    public boolean isDone() {
        if(Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),2,3)){
            canSparkMax.set(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAcceptState() {
        return true;
    }
}
