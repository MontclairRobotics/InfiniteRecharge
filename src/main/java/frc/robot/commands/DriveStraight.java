package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.RobotContainer.driveSubsystem;

public class DriveStraight extends CommandBase {

    private final PIDController distanceController;
    //distance is in feet
    public DriveStraight(double distance){
        distanceController = new PIDController(1,0,0);
        distanceController.setSetpoint(distance*8*Math.PI * 1/6); 
        distanceController.setTolerance(0.1);
    }

    @Override
    public void initialize() {
        driveSubsystem.resetEncoders();
    }

    @Override
    public void execute() {
        driveSubsystem.arcadeDrive(distanceController.calculate(driveSubsystem.getAverageEncoderDistance()),0);
    }

    @Override
    public boolean isFinished() {
        return distanceController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.arcadeDrive(0,0);
    }
}
