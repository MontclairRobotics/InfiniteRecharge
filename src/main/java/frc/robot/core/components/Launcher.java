package frc.robot.core.components;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.core.components.ControlSystem;
import frc.robot.core.components.Drivetrain;

interface launcher {
    void stopMotors();
    void startMotors(); 
    void shoot(double velocity, int burst); 
    void nextState(); 
    

class UpShoot implements launcher {
    
    @Override
    public void stopMotors() {
        Upshoot.set(0);
        
    }

    @Override
    public void startMotors() {
       Upshoot.set(1);
    }

    @Override
    public void shoot(double velocity, int burst) {
        

        
    }

    @Override
    public void nextState() {
        
        
    }

}
