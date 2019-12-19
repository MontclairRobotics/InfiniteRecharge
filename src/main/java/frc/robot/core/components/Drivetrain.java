package frc.robot.core.components;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.core.Robot;
import frc.robot.core.components.ControlSystem.Axis;
import frc.robot.core.components.ControlSystem.Controllers;
import frc.robot.core.utils.Component;

public class Drivetrain implements Component {

    WPI_TalonSRX frontLeft;
    WPI_TalonSRX frontRight;
    WPI_TalonSRX backLeft;
    WPI_TalonSRX backRight;

    private double leftPower;
    private double rightPower;

    public Drivetrain(){}

    @Override
    public void robotInit() {
        this.frontLeft = new WPI_TalonSRX(0);
        this.frontRight = new WPI_TalonSRX(0);
        this.backLeft = new WPI_TalonSRX(0);
        this.backRight = new WPI_TalonSRX(0);
    }

    @Override
    public void teleopPeriodic() {

        leftPower = Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, Axis.X)
            + Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, Axis.Y);

        rightPower = Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, Axis.Y)
            - Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, Axis.X);

        frontLeft.set(leftPower);
        backLeft.set(leftPower);
        frontRight.set(rightPower);
        backRight.set(rightPower);
    }

    @Override
    public void autonomousPeriodic() {


    }

}