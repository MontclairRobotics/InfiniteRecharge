package frc.robot.core.components;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.core.Robot;
import frc.robot.core.components.ControlSystem.Controllers;
import frc.robot.core.components.ControlSystem.DriverButtons;
import frc.robot.core.utils.Component;

public class Drivetrain implements Component, PIDOutput {

    DifferentialDrive differentialDrive;

    WPI_TalonSRX frontLeft;
    WPI_TalonSRX frontRight;
    WPI_TalonSRX backLeft;
    WPI_TalonSRX backRight;

    private AHRS navx;
    private PIDController turnController;
    private double rotateToAngleRate;
    private boolean rotateToAngle;

    public Drivetrain(){}

    @Override
    public void robotInit() {

        SpeedControllerGroup right;
        SpeedControllerGroup left;
        frontLeft = new WPI_TalonSRX(0);
        frontRight = new WPI_TalonSRX(0);
        backLeft = new WPI_TalonSRX(0);
        backRight = new WPI_TalonSRX(0);
        left = new SpeedControllerGroup(frontLeft, backLeft);
        right = new SpeedControllerGroup(frontRight, backRight);

        differentialDrive = new DifferentialDrive(left, right);

        try {
            navx = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
        }

        turnController = new PIDController(0, 0, 0, 0, navx, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(5);
        turnController.setContinuous(true);
    }

    @Override
    public void teleopPeriodic() {

        if(Robot.controlSystem.getButton(DriverButtons.GYRO_LOCK)){
            lockAngle(Robot.controlSystem.getPOV(Controllers.DRIVER));
        }else{
            differentialDrive.tankDrive(Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, 0),
                Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, 0));
                calcCurrentRotationRate();
                navx.getAngle();
        }

    }

    public void lockAngle(double angle){
        navx.reset();
        turnController.setSetpoint(angle);
        rotateToAngle = true;
    }

    public double calcCurrentRotationRate(){
        if ( rotateToAngle ) {
              turnController.enable();
              return rotateToAngleRate;
        } else {
              turnController.disable();
              return Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, 0);
        }
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void pidWrite(double output) {
        rotateToAngleRate = output;
    }

}