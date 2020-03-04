package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.Constants.DriveConstants;

public class TurnInPlace extends CommandBase {
    private final DriveSubsystem drive;
    private final double speed, degrees;

    public TurnInPlace(DriveSubsystem subsystem, double degrees, double speed) {
        drive = subsystem;
        this.speed = speed;
        this.degrees = drive.getAngle() + degrees;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        drive.arcadeDrive(0, speed*Math.signum(degrees));
    }

    @Override 
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(degrees - drive.getAngle()) >= DriveConstants.kThetaCorrectionThreshold;
    }
}