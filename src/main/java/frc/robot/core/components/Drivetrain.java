package frc.robot.core.components;


//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.core.Robot;
import frc.robot.core.components.ControlSystem.Controllers;
import frc.robot.core.components.ControlSystem.DriverButtons;
import frc.robot.core.utils.Component;
import frc.robot.core.utils.Hardware;

public class Drivetrain implements Component /*PIDOutput*/ {

    DifferentialDrive differentialDrive;



   //private PIDController turnController;
    private double rotateToAngleRate;
    private boolean rotateToAngle;

    public Drivetrain(){}

    @Override
    public void robotInit() {

        SpeedControllerGroup right;
        SpeedControllerGroup left;
       
        //left = new SpeedControllerGroup(Hardware.DT_FL, Hardware.DT_BL);
        //right = new SpeedControllerGroup(Hardware.DT_FR, Hardware.DT_BR);

        //differentialDrive = new DifferentialDrive(left, right);

        

        /*turnController = new PIDController(0, 0, 0, 0, navx, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(5);
        turnController.setContinuous(true);*/
    }

    @Override
    public void teleopPeriodic() {

        if(Robot.controlSystem.getButton(DriverButtons.GYRO_LOCK)){
            lockAngle(Robot.controlSystem.getPOV(Controllers.DRIVER));
        }else{
            differentialDrive.tankDrive(Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, 0),
                Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, 0));
                calcCurrentRotationRate();
                //Hardware.navx.getAngle();
        }

    }

    public void lockAngle(double angle){
        //Hardware.navx.reset();
        //turnController.setSetpoint(angle);
        rotateToAngle = true;
    }

    public double calcCurrentRotationRate(){
        if ( rotateToAngle ) {
             // turnController.enable();
              return rotateToAngleRate;
        } else {
              //turnController.disable();
              return Robot.controlSystem.getJoystickAxis(Controllers.DRIVER, 0);
        }
    }

    @Override
    public void autonomousPeriodic() {

    }

    //@Override
   /* public void pidWrite(double output) {
        rotateToAngleRate = output;
    }*/

}