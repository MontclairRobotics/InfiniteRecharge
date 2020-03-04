package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.Constants.DriveConstants;

public class ThetaCorrection extends CommandBase {
    private final DriveSubsystem drive;
    private final double degrees;
    private double turnDirection;

    public ThetaCorrection(DriveSubsystem subsystem, double degrees) {
        drive = subsystem;
        this.degrees = degrees;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        turnDirection = Math.signum(degrees);
        if(turnDirection == 0) {
            end(true);
        }
    }

    @Override
    public void execute(){
        drive.arcadeDrive(-0.1, DriveConstants.kThetaCorrectionSpeed*turnDirection );
    }

    @Override 
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(drive.getAngle() - degrees) + DriveConstants.kThetaCorrectionOffset <= DriveConstants.kThetaCorrectionThreshold;
    }
}