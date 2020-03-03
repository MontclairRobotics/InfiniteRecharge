package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.VisionSubsystem;

public class ReportVision extends CommandBase {
    
    private final VisionSubsystem vision;

    public ReportVision(VisionSubsystem vision) {
        this.vision = vision;
        addRequirements(this.vision);
    }

    @Override
    public void execute() {
        SmartDashboard.putBoolean("Target is in position", vision.getAligned());
    }

}