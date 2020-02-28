package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Hardware;
import frc.robot.utils.Constants;

public class TransportSubsystem extends SubsystemBase{
    
    private SpeedControllerGroup bottomTransport, topTransport;

    public TransportSubsystem(){
        bottomTransport = new SpeedControllerGroup(Hardware.transportBottom);
        topTransport = new SpeedControllerGroup(Hardware.transportTop);
        
    }
    public void actTransport(){
            bottomTransport.set(Constants.TransportConstants.kIntakeSpeed);
            topTransport.set(-(Constants.TransportConstants.kIntakeSpeed));
        }
    public void deactTransport(){
        bottomTransport.set(0);
        topTransport.set(0);
    }
    
}