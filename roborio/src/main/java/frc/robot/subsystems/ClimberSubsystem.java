package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
    
    private double climbTarget = 0, armTarget = 0, shimmyTarget = 0;
    private ArmPosition position = ArmPosition.DOWN;
    private boolean canAscend = true;
    private boolean canDescend = false;

    public enum ArmPosition{
        UP(true), DOWN(false);
        boolean value;
        private ArmPosition(boolean value) {this.value = value;}
    }

    public boolean setArmPosition(boolean position) {

    }

    public ArmPosition getArmPosition() {
        return position; 
    }

    public boolean climbUp() {
        if(!canAscend)
            return false;
        climb(false);
        return true;
    }

    public boolean climbDown() {
        if(!canDescend)
            return false;
        climb(true);
        return true;
    }

    private void climb(boolean isDown) {

    }

}