package frc.robot.core.components;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor{

    private ColorSensorV3 colorSensor;
    private double kR = 1, kB = 1, kG = 1, oR = 0, oB = 0, oG = 0;
    /* "k" here stands for coefficient and "o" for offset */

    public ColorSensor(ColorSensorV3 colorSensor) {

        this.colorSensor = colorSensor;

    }

    public double getRed() { return colorSensor.getRed() * kR + oR; }
    public double getBlue() { return colorSensor.getBlue() * kB + oB; }
    public double getGreen() { return colorSensor.getGreen() * kG + oG; }
    public double getIR() { return colorSensor.getIR(); }
    public Color getColor() {
        return new Color(getRed(),getGreen(),getBlue());
    }

    public ColorSensorV3 getColorSensorV3() {return colorSensor;}

    public void calibrateFunctions(Color white, Color black) {

        kR = (white.red - black.red)/255;
        kB = (white.blue - black.blue)/255;
        kG = (white.green - black.green)/255;

        oR = black.red;
        oB = black.blue;
        oG = black.green;

    }

    public void setFunctions(double kR, double kB, double kG, double oR, double oB, double oG) {this.kR = kR; this.kB = kB; this.kG = kG; this.oR = oR; this.oB = oB; this.oG = oG;}

}