package frc.robot.core.utils.Vectors;

public class Vector2{

    public double x,y;

    public Vector2(double x, double y) { this.x = x; this.y = y; }

    //IDENTITY VECTOR//
    public static Vector2 identity = new Vector2(0, 0);

    //STATIC METHODS//
    public static Vector2 fromPoints(double x1, double y1, double x2, double y2) {

        return new Vector2(x2-x1,y2-y1);

    }

    public static double distance(Vector2 v1, Vector2 v2) {return Math.sqrt(Math.pow(v1.x-v2.x, 2) + Math.pow(v1.y-v2.y,2));}

    //FIND DATA//
    public double magnitude() { return Math.sqrt(Math.pow(x,2) + Math.pow(y,2)); }

    public double angle() { return Math.toDegrees(Math.atan2(y, x)); }

    public double angleBetween(Vector2 that) {

        return Math.abs(this.angle() - that.angle()) % 360;

    }

    public double signedAngleBetween(Vector2 that) {

        return (this.angle() - that.angle()) % 360;

    }
    
}