package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Drive extends CommandBase {
    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;

public Drive(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier rotation){
        m_drive = subsystem;
        m_forward = forward;
        m_rotation = rotation;
        addRequirements(subsystem);
}
@Override
public void execute(){
    m_drive.arDrive(m_forward.getAsDouble(), m_rotation.getAsDouble(), true);//may change false boolean later
}
}