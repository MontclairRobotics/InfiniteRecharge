package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.SPI;

public class Drive extends CommandBase {
    private final DriveSubsystem drive;
    private final double forward;
    private final double rotation;
    private boolean inverted;
    
    public Drive(DriveSubsystem subsystem, double forward, double rotation, boolean inverted){
        drive = subsystem;
        this.forward = forward;
        this.rotation = rotation;
        addRequirements(subsystem);
    }
    public Drive(DriveSubsystem subsystem, double forward, double rotation){
        this(subsystem, forward, rotation, false);
    }

    @Override
    public void execute(){
        if (inverted) {
            drive.arcadeDrive(forward, rotation, true);
            
        }
        else {
            drive.arcadeDrive(forward, rotation, false);
        }
        
    }
}