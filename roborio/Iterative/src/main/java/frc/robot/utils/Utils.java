package frc.robot.utils;

public class Utils {

    public static boolean threshold(double input, double min, double max){
        return (input>=min && input<=max);
    }

    public enum ControlPanelColors{
        RED, YELLOW, CYAN, GREEN, UNKNOWN
    }

}
