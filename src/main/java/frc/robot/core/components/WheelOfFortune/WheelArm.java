package frc.robot.core.components.WheelOfFortune;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.core.utils.*;
import frc.robot.core.utils.SensorManager.KeyColorSensorValues;
import edu.wpi.first.wpilibj.util.Color;


interface WheelArmInterface {
    void Raise();
    void Lower();
    void RotateControl();
    void RotateForColor(char colorForField);
    char GetColorFromSensor();
}
public class WheelArm implements WheelArmInterface {
    
    
    int spinCount = 0;
    public void Raise() {
        //Hardware.ArmMover.set(ControlMode.PercentOutput, 0.1);
    }
    
    public void Lower() {
        //Hardware.ArmMover.set(ControlMode.PercentOutput, -0.1);
    }
    public void RotateControl() {
        char startColor = GetColorFromSensor();
        boolean ifTurned = false;
        //Hardware.WheelMover.set(ControlMode.PercentOutput, 0.2);
        if (spinCount == 7) {
            //Hardware.WheelMover.set(ControlMode.PercentOutput, 0);
        } else if (GetColorFromSensor() == startColor && ifTurned == true) {
            spinCount ++;
            ifTurned = false;
        } else {
            ifTurned = true;
        }
     }

    public void RotateForColor(char colorForField) {
        char colorForSensor;
        if (colorForField == 'y') {
            colorForSensor = 'g';
        } else if (colorForField == 'r') {
            colorForSensor = 'b';
        } else if (colorForField == 'g') {
            colorForSensor = 'y';
        } else {
            colorForSensor = 'r';
        }
        //Hardware.WheelMover.set(ControlMode.PercentOutput, 0.1);

        if (GetColorFromSensor() == colorForSensor) {
            //Hardware.WheelMover.set(ControlMode.PercentOutput, 0);
        }
    }
    /**
     * Returns the color that the color sensor is reading
     * 
     * y - yellow (red > .3 , green > .5)
     * r - red    (red > .4)
     * g - green  (green > .5)
     * b - blue   (blue > .4)
     * 
     * All values were obtained through testing at one light condition
     * @return a charachter pertaining to the color that the light sensor is reading (y, r, g, b)
     */
    public char GetColorFromSensor() { 
        
        Color detectedColor = Hardware.m_colorSensor.getColor();
        double redVal = detectedColor.red;
        double blueVal = detectedColor.blue;
        double greenVal = detectedColor.green;

        char returnVal = 'n';

        if (redVal > KeyColorSensorValues.redValForY && greenVal > KeyColorSensorValues.greenValForY) {
            returnVal = 'y';
        } else if (redVal > KeyColorSensorValues.redValForR) {
            returnVal = 'r';
        } else if (greenVal > KeyColorSensorValues.greenValForG) {
            returnVal = 'g';
        } else if (blueVal > KeyColorSensorValues.blueValForB) {
            returnVal = 'b';
        }
        return(returnVal);
    }
}