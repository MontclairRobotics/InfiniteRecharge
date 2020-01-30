package frc.robot.core.components.Drivetrain;

import frc.robot.core.utils.Vectors.*;

class NavPoint{

    private Vector2 vector;
    private double radius;

    public NavPoint(Vector2 vector, double radius) {this.vector = vector; this.radius = radius;}
    public NavPoint(double x, double y, double radius) {this(new Vector2(x, y), radius);}

    public boolean isInRadius(NavPoint point) {

        return Vector2.distance(point.vector, vector) <= radius;

    }

    public static double turnAngle(Vector2 leg1, Vector2 vertexVector, Vector2 leg2) {

        leg1.x -= vertexVector.x;
        leg1.y -= vertexVector.y;

        leg2.x -= vertexVector.x;
        leg2.y -= vertexVector.y;

        return leg1.signedAngleBetween(leg2);

    }

    //FLYOVER INSTANTIATION//

    public static double flyOverRadius;
    public static NavPoint newFlyOverPoint(Vector2 vector) {

        return new NavPoint(vector,flyOverRadius);

    }

}