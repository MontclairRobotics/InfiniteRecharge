package frc.robot.core.utils;


public class Utils {
    public static double constrain(double val,double min,double max) {
        if(val<min)return min;
        if(val>max)return max;
        return val;
    }

    public static double wrap(double val,double wrapAt) {
        return wrap(val,-wrapAt/2,wrapAt/2);
    }

    public static double wrap(double val,double min,double max) {
        double diff=max-min;
        return ((val-min)%diff+diff)%diff+min;
    }
    public static boolean threshold(double input, double min, double max){
        return (input>=min && input<=max);
    }

}