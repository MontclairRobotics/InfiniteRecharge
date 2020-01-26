package frc.robot.component;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.utils.RobotComponent;

import java.sql.Driver;

public class Climber implements RobotComponent {

    CANSparkMax winchMotor;
    CANSparkMax armMotor;

    CANEncoder winchEncoder;
    CANEncoder armEncoder;

    public Climber(){
        //TODO
    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void robotPeriodic() {

    }
}
