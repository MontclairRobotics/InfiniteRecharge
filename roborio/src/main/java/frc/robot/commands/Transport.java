package frc.robot.commands;

import frc.robot.subsystems.TransportSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

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