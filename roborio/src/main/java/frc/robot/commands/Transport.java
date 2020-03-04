package frc.robot.commands;

import frc.robot.subsystems.TransportSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

// TODO: The transport system has to have some way to tell when it is okay to start moving. 
// TODO: Since the entire robot is a feed through system, any movement to pick up a ball will 
// TODO: Move the rest of the balls in the transport. 
public class Transport extends CommandBase{
    private TransportSubsystem m_transport;

    public Transport(TransportSubsystem subsystem){
        m_transport = subsystem;
        addRequirements(subsystem);
    }
    @Override
    public void execute(){
        m_transport.actTransport();
    }
    @Override
    public void end(boolean interupted){
        m_transport.deactTransport();
    }
}