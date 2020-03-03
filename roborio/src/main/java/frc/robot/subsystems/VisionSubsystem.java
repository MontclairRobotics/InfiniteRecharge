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
        try {
            isVisible = table.getEntry("Tgt Detected").getBoolean(isVisible);
        } finally {}
        return isVisible;
    }

    public Double[] getTargetPosition(boolean isVisible) /*RETURN [x,y]*/ {
        return new Double[]{
            isVisible ? table.getEntry("x").getDouble(0) : -1, 
            isVisible ? table.getEntry("y").getDouble(0) : -1
        };
    }

    public Double[] getTargetPosition() /*RETURN [x,y]*/ {
        return getTargetPosition(getTargetVisible());
    }
    
    public boolean getAligned() {
        boolean isCurVis = getTargetVisible();
        return  
            isCurVis ?
            Math.abs( getTargetPosition(true)[0] - VisionConstants.kTarget[0] )
                <= Constants.VisionConstants.kAlignmentThreshold
            && Math.abs( getTargetPosition(true)[1] - VisionConstants.kTarget[1] ) 
                <= Constants.VisionConstants.kAlignmentThreshold
            :false;
    }

}