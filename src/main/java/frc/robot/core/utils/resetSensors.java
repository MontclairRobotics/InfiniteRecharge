package frc.robot.core.utils;

import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.core.components.ControlSystem.DriverButtons;
import frc.robot.core.Robot;
import edu.wpi.first.wpilibj.util.Color;


public class ResetSensors {
    public static double redValForY = 0.3;
    public static double greenValForY = 0.5;
    public static double redValForR = 0.4;
    public static double greenValForG = 0.5;
    public static double blueValForB = 0.4;

    public void resetSensors() {
        Hardware.navx.reset();
        SmartDashboard.putString("SettingColorSensor", "Put the color sensor 3-4 inches above the yellow color");
        SmartDashboard.putString("SettingColorSensor", "Now press and hold the red button");

        calibrateColor();
    }

    public void calibrateColor() {
        char currentColorTesting = 'y';
        boolean activeCalibration = false;
        Color detectedColor = Hardware.m_colorSensor.getColor();

        if (Robot.controlSystem.getButton(DriverButtons.Calibrating_Sensors) || activeCalibration == false && currentColorTesting == 'y') {
            redValForY = detectedColor.red;
            greenValForY = detectedColor.green;
            activeCalibration = true;
            if (detectedColor.red < redValForY) {
                redValForY = detectedColor.red;
            }
            if (detectedColor.green < redValForY) {
                redValForY = detectedColor.red;
            }
        } else {
            currentColorTesting = 'r';
            activeCalibration = false;
        }

        SmartDashboard.putString("SettingColorSensor", "Move the sensor over the red color");
        SmartDashboard.putString("SettingColorSensor", "Now press and hold the red button");
        if (Robot.controlSystem.getButton(DriverButtons.Calibrating_Sensors) || activeCalibration == false && currentColorTesting == 'r') {
            redValForR = detectedColor.red;
            activeCalibration = true;
            if (detectedColor.red < redValForR) {
                redValForR = detectedColor.red;
            }
        } else {
            currentColorTesting = 'g';
            activeCalibration = false;
        }

        SmartDashboard.putString("SettingColorSensor", "Move the sensor over the green color");
        SmartDashboard.putString("SettingColorSensor", "Now press and hold the red button");
        if (Robot.controlSystem.getButton(DriverButtons.Calibrating_Sensors) || activeCalibration == false && currentColorTesting == 'g') {
            greenValForG = detectedColor.green;
            activeCalibration = true;
            if (detectedColor.green < greenValForG) {
                greenValForG = detectedColor.green;
            }
        } else {
            currentColorTesting = 'b';
            activeCalibration = false;
        }

        SmartDashboard.putString("SettingColorSensor", "Move the sensor over the blue color");
        SmartDashboard.putString("SettingColorSensor", "Now press and hold the red button");
        if (Robot.controlSystem.getButton(DriverButtons.Calibrating_Sensors) || activeCalibration == false && currentColorTesting == 'b') {
            blueValForB = detectedColor.blue;
            activeCalibration = true;
            if (detectedColor.blue < blueValForB) {
                blueValForB = detectedColor.blue;
            }
        } else {
            currentColorTesting = 'd';
            activeCalibration = false;
        }
        
        SmartDashboard.putString("SettingColorSensor", "All done!");
    }
}