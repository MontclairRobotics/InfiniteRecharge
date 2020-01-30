package frc.robot.core.components;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.core.Robot;
import frc.robot.core.utils.Component;
import frc.robot.core.utils.ControlSystem;
import frc.robot.core.utils.Hardware;
import frc.robot.core.utils.Utils;
import frc.robot.core.utils.ControlSystem.Controllers;
import frc.robot.core.utils.ControlSystem.DriverButtons;

public class Drivetrain implements Component  {

    DifferentialDrive differentialDrive;

    //Turn controller for gyro lock
    private PIDController gyroController;

    public Drivetrain(){}

    @Override
    public void robotInit() {

        SpeedControllerGroup right;
        SpeedControllerGroup left;
       
        left = new SpeedControllerGroup(Hardware.DT_FL, Hardware.DT_BL);
        right = new SpeedControllerGroup(Hardware.DT_FR, Hardware.DT_BR);

        differentialDrive = new DifferentialDrive(left, right);
    }
    public void teleopInit() {
        //TODO: Tune values with final robot, as they are likely to be different because of mass and friction
        //Values taken from Will's branch
        gyroController = new PIDController(0.5, 0, 0.07);
        gyroController.setTolerance(2); //Not tuned
        gyroController.enableContinuousInput(-180.0, 180.0);
    }

    @Override
    public void teleopPeriodic() {
        double rotation;
        if(ControlSystem.driver.getRawButton(DriverButtons.GYRO_LOCK.getPort()) || Utils.threshold(Math.abs(ControlSystem.driver.getY(GenericHID.Hand.kLeft)),0.3,0.7)) {
            gyroController.setSetpoint(0);
            rotation = gyroController.calculate(Hardware.navx.getYaw());
        } else if(ControlSystem.driver.getPOV()!= -1){
            switch(ControlSystem.driver.getPOV()){
                case 0:
                    gyroController.setSetpoint(0);
                    rotation = gyroController.calculate(Hardware.navx.getYaw());
                    break;

                case 90:
                    gyroController.setSetpoint(90);
                    rotation = gyroController.calculate(Hardware.navx.getYaw());
                    break;

                case 180:
                    gyroController.setSetpoint(180);
                    rotation = gyroController.calculate(Hardware.navx.getYaw());
                    break;

                case 270:
                    gyroController.setSetpoint(270);
                    rotation = gyroController.calculate(Hardware.navx.getYaw());
                    break;

                default:
                    rotation = ControlSystem.driver.getX(GenericHID.Hand.kRight);
            }
            differentialDrive.arcadeDrive(ControlSystem.driver.getY(GenericHID.Hand.kLeft),
                rotation);
        }

    }

    @Override
    public void autonomousPeriodic() {

    }

}