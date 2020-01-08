package frc.robot.core.components;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.core.components.ControlSystem;
import frc.robot.core.components.Drivetrain;

interface transportIntake {
    void intake();// requires sensor
    boolean full();
}

interface transportOutput {
    void output();// requires sensor
    boolean empty();
}