package frc.robot.core.components.Climber;


interface climberBase {
    void armsUp();
    void armsDown();
    void reset();
    void climb(double height, double speed);
}

public class Climber implements climberBase {
    Arm rightArm;
    Arm leftArm;
    Winch rightWinch;
    Winch leftWinch;
    Mover mover;

    public void armsUp() {
        rightArm.extend();
        leftArm.extend();

    }

    public void armsDown() {
        rightArm.retract();
        leftArm.retract();

    }

    public void reset() {
        rightArm.retract();
        leftArm.retract();
        rightWinch.reset(0.1);
        leftWinch.reset(0.1);
    }


    public void climb(double height, double speed) {
        armsUp();
        armsDown();
        rightWinch.wind(speed, height);
        leftWinch.wind(speed, height);
    }
    public void move() {
        mover.shift();
    }
    


}