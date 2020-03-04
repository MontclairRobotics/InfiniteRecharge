package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.Constants.VisionPIDConstants.*;
import static frc.robot.RobotContainer.*;
import static frc.robot.utils.Utils.*;

public class PortLock extends CommandBase {

    private final PIDController turn;

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision/Port");

    public PortLock() {

        this.turn = new PIDController(kVisionTurnP, kVisionTurnI, kVisionTurnD);
        this.turn.setSetpoint(320);
        this.turn.enableContinuousInput(0, 640);
        this.turn.setTolerance(3);
    }

    @Override
    public void execute() {
        double rot;
        if(table.getEntry("Detected").getBoolean(false)){
            rot = turn.calculate(table.getEntry("X").getDouble(320));
        } else {
            rot = driverController.getX(GenericHID.Hand.kRight);
        }
        driveSubsystem.arcadeDrive(driverController.getY(GenericHID.Hand.kLeft), rot);
    }

    @Override
    public boolean isFinished() {
        boolean isFinished = turn.atSetpoint();
        rumble(isFinished);
        return isFinished;
    }
}
