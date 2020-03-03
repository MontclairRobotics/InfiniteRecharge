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
        boolean isVisible = vision.getTargetVisible();
        SmartDashboard.putBoolean("Target is in position", vision.getAligned());
        SmartDashboard.putString("Target at X", isVisible ? Double.toString(vision.getTargetPosition(isVisible)[0]) : "NOT-VISIBLE");
        SmartDashboard.putString("Target at Y", isVisible ? Double.toString(vision.getTargetPosition(isVisible)[1]) : "NOT-VISIBLE");
    }

}