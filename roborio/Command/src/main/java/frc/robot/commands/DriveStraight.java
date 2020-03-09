package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveSubsystem;



public class DriveStraight extends CommandBase {
    private final PIDController distanceController;
    DriveSubsystem driveSubsystem;
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

        driveSubsystem.arcadeDrive(distanceController.calculate(driveSubsystem.getAverageEncoderDistance()),0);

    }
    @Override
    public boolean isFinished() {
        return distanceController.atSetpoint();
    }

}