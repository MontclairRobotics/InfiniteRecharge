package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Hardware;
import frc.robot.utils.Constants.LiftConstants;

public class VisionSubsystem extends SubsystemBase {

    public VisionSubsystem() {        
    }

    //TODO: implement funcs
    public boolean getTargetVisible() {
        return null; //TODO: implement
    }

    public boolean getAligned() {
        return Math.abs( 
                    getCenteredTargetPosition()[0] - Constants.VisionConstants.kImageCenter[0] 
               ) <= Constants.VisionConstants.kAlignmentThreshold
               && Math.abs(
                    getCenteredTargetPosition()[1] - Constants.VisionConstants.kImageCenter[1]
               ) <= Constants.VisionConstants.kAlignmentThreshold;
    }

    public double[] getTargetPosition() /*RETURN [x,y]*/ {
        return null; //TODO: implement
    }

    public double[] getCenteredTargetPosition() /*RETURN [x,y]*/ {
        return getTargetPosition(); //TODO: implement
    }

}