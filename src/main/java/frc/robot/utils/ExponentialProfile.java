package frc.robot.utils;

public class ExponentialProfile 
{
    public double base;
    public double dmax;
    
    public static final double EPSILON = 0.001;

    public ExponentialProfile(double base, double dmax)
    {
        this.base = base;
        this.dmax = dmax;
    }

    public double profile(double target, double prev)
    {
        var diff = prev - target;
        
        //for sufficiently small changes...
        if(-EPSILON <= diff && diff <= EPSILON)
        {
            //simply perform the whole change.
            return prev;
        }

        //otherwise...
        diff = diff / base; //perform the scaling
        diff = Utils.clamp(diff, -dmax, dmax); //clamp the magnitude of the difference

        //return the new thing!
        return prev + diff;
    }
}
