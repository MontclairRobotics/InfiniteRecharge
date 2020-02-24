package frc.robot.subsystems;

import java.util.Objects;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {

    private boolean fullyRaised = false;
    private final double winchSpeed;
    private SpeedController winchMotor, armMotor;

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
    public ClimberSubsystem(double speed) {this(0,Hardware.,)}
    public ClimberSubsystem(double speed, SpeedController winchMotor, SpeedController armMotor) {winchSpeed = speed;}

}