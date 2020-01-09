package frc.robot.core.components;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.core.components.ControlSystem;
import frc.robot.core.components.Drivetrain;

interface launcher {
    boolean stopMotors();
    boolean startMotors(); 
    double shoot(double velocity, int burst); 
    boolean nextState; 

}

class UpShoot implements launcher{

}
