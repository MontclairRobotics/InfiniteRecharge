package frc.robot.utils;

public class LinearProfile implements Profile
{
    public DoubleInput slope;
    public DoubleInput snapFactor;
    
    public static final double EPSILON = 0.0005;

    public LinearProfile(DoubleInput slope, DoubleInput snapFactor)
    {
        this.slope = slope;
        this.snapFactor = snapFactor;
    }

    public double profile(double target, double prev, long millisDelta)
    {
        var slopeVal = slope.get();
        //read slope value

        var delta = target - prev; 
        //total distance
        var diff = millisDelta * slopeVal * Utils.signZero(delta);
        //         delta time  * delta/ms * direction

        //handle snap
        if(Utils.signsDiffer(prev, target))
            diff *= snapFactor.get();

        var newVal = prev + diff;
        //apply change
        
        return Utils.clampUnordered(newVal, prev, target);
        //clamp the value to range
    }
}
