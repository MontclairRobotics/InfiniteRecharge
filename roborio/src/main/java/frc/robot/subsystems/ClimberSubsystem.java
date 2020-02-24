package frc.robot.subsystems;

import java.util.Objects;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Hardware;

public class ClimberSubsystem extends SubsystemBase {

    private boolean fullyRaised = false;
    private final double winchSpeed;
    private SpeedControllerGroup winchMotors, armMotors;

    public boolean raiseArm() {

    }

    public boolean lowerArm() {

    }

    public boolean climbUp() {
        if(!fullyRaised)
            return false;
        climb(winchSpeed);
        return true;
    }

    public boolean climbDown() {
        if(!fullyRaised)
            return false;
        climb(-winchSpeed);
        return true;
    }

    private void climb(double velocity) {
        
    }

    public ClimberSubsystem() {this(0,null,null);}
    public ClimberSubsystem(double speed) {this(0,Hardware.winches,Hardware.liftArms);}
    public ClimberSubsystem(double speed, SpeedControllerGroup winchMotors, SpeedControllerGroup armMotors) {winchSpeed = speed; this.winchMotors=winchMotors; this.armMotors=armMotors;}

}