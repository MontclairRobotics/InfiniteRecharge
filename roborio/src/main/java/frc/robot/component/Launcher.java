package frc.robot.component;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.utils.Controls;
import frc.robot.utils.RobotComponent;

//TODO: Complete
public class Launcher implements RobotComponent {

    CANSparkMax launchMotor;
    CANSparkMax flapMotor;
    CANSparkMax reloadMotor;
    CANSparkMax intakeMotor;

    public Launcher() {
        this.launchMotor = new CANSparkMax(5, MotorType.kBrushless);
        this.flapMotor = new CANSparkMax(6, MotorType.kBrushless);
        this.reloadMotor = new CANSparkMax(7, MotorType.kBrushless);
    }

    @Override
    public void teleopPeriodic() {
        if(Controls.auxiliary.getTriggerAxis(GenericHID.Hand.kLeft)>0.125){ // Arbitrary threshold to fire
            launchMotor.set(1);
        }

    }

    @Override
    public void robotPeriodic() {
        intakeMotor.set(1);
        reloadMotor.set(1);
    }

}