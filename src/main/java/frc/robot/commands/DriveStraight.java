package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

import static frc.robot.RobotContainer.driveSubsystem;

public class DriveStraight extends CommandBase {

    private final PIDController distanceController;

    public DriveStraight(double distance){
        distanceController = new PIDController(1,0,0);
        distanceController.setSetpoint(distance*1/0.3); // TODO: Tune rot/meter
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
