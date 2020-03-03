package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.VisionConstants;

public class VisionSubsystem extends SubsystemBase {

    private boolean isVisible = false;
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision/Port");

    public VisionSubsystem() {}

    public boolean getTargetVisible() {
        isVisible = table.getEntry("Tgt Detected").getBoolean(isVisible);
        return isVisible;
    }

    public Double[] getTargetPosition() /*RETURN [x,y]*/ {
        return new Double[]{
            getTargetVisible() ? table.getEntry("x").getDouble(0) : -1, 
            getTargetVisible() ? table.getEntry("y").getDouble(0) : -1
        };
    }
    
    public boolean getAligned() {
        return  
            getTargetVisible() ?
            Math.abs( getTargetPosition()[0] - VisionConstants.kVisionTarget[0] )
                <= Constants.VisionConstants.kAlignmentThreshold
            && Math.abs( getTargetPosition()[1] - VisionConstants.kVisionTarget[1] ) 
                <= Constants.VisionConstants.kAlignmentThreshold
                :
            false;
    }

}