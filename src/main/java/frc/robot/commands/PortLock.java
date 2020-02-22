package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.Constants.VisionPIDConstants.*;
import static frc.robot.RobotContainer.*;
import static frc.robot.utils.Utils.rumbleAux;
import static frc.robot.utils.Utils.rumbleDriver;

public class PortLock extends CommandBase {

    private final PIDController dist;
    private final PIDController turn;

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision/Port");

    public PortLock() {
        this.dist = new PIDController(kVisionDistP, kVisionDistI, kVisionDistD);
        this.dist.setSetpoint(240);
        this.dist.enableContinuousInput(0, 480);
        this.dist.setTolerance(3);

        this.turn = new PIDController(kVisionTurnP, kVisionTurnI, kVisionTurnD);
        this.turn.setSetpoint(320);
        this.turn.enableContinuousInput(0, 640);
        this.turn.setTolerance(3);
    }

    @Override
    public void execute() {
        double fwd, rot;
        if(table.getEntry("Detected").getBoolean(false)){
            fwd = table.getEntry("Y").getDouble(0);
            rot = table.getEntry("X").getDouble(0);
        } else {
            fwd = driverController.getY(GenericHID.Hand.kLeft);
            rot = driverController.getX(GenericHID.Hand.kRight);
        }
        driveSubsystem.arcadeDrive(fwd, rot);
    }

    @Override
    public boolean isFinished() {
        boolean isFinished = dist.atSetpoint() && turn.atSetpoint();
        rumbleDriver(isFinished);
        rumbleAux(isFinished);
        return isFinished;
    }
}
