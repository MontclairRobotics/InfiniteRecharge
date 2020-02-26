package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Hardware;

public class ClimberSubsystem extends SubsystemBase {

    private boolean fullyRaised = false;
    private final double armSpeed;
    private final double winchSpeed;
    private SpeedControllerGroup winchMotors, armMotors;

    private enum Direction {
        UP, DOWN, STOPPED
    }

    private Direction currentWinchDirection = Direction.STOPPED;
    private Direction currentArmDirection = Direction.STOPPED;

    public void raiseArm() {
        currentArmDirection = Direction.UP;
    }

    public void lowerArm() {
        currentArmDirection = Direction.DOWN;
    }

    public void stopArm() {
        currentArmDirection = Direction.STOPPED;
    }

    public void climbUp() {
        currentWinchDirection = Direction.UP;
    }

    public void climbDown() {
        currentWinchDirection = Direction.DOWN;
    }

    public void climbStop() {
        currentWinchDirection = Direction.STOPPED;
    }

    @Override
    public void periodic() {
        switch(currentArmDirection) {
            case UP:
                armMotors.set(armSpeed);
            case DOWN:
                armMotors.set(-armSpeed);
            default:
                armMotors.set(0);
        }
        switch(currentWinchDirection) {
            case UP:
                winchMotors.set(winchSpeed);
            case DOWN:
                winchMotors.set(-winchSpeed);
            default:
                winchMotors.set(0);
        }
    }

    public ClimberSubsystem() {this(0,0,null,null);}
    public ClimberSubsystem(double speed) {this(speed, speed,Hardware.winches,Hardware.liftArms);}
    public ClimberSubsystem(double armSpeed, double winchSpeed) {this(armSpeed, winchSpeed,Hardware.winches,Hardware.liftArms);}
    public ClimberSubsystem(double armSpeed, double winchSpeed, SpeedControllerGroup winchMotors, SpeedControllerGroup armMotors) {this.winchSpeed = winchSpeed; this.armSpeed = armSpeed; this.winchMotors=winchMotors; this.armMotors=armMotors;}

}