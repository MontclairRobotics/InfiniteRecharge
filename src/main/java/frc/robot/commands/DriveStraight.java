package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.RobotContainer.driveSubsystem;

public class DriveStraight extends CommandBase {

    private final PIDController distanceController;
    //distance is in feet
    public DriveStraight(double distance){
        distanceController = new PIDController(1,0,0);
        distanceController.setSetpoint(distance); 
        distanceController.setTolerance(0.1);
    }

    @Override
    public void initialize() {
        driveSubsystem.resetEncoders();
    }

    @Override
    public void execute() {
        driveSubsystem.arcadeDriveDirect(distanceController.calculate(driveSubsystem.getAverageDistance()),0);
        SmartDashboard.putNumber("Average distance", driveSubsystem.getAverageDistance());
    }

    @Override
    public boolean isFinished() {
        return distanceController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.arcadeDriveDirect(0,0);
    }
}
