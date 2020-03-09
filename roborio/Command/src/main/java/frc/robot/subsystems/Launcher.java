package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;

public class Launcher extends SubsystemBase {
    CANSparkMax launcher = new CANSparkMax(LauncherConstants.kLauncherPort, kBrushless);
    //CANSparkMax intakeArm = new CANSparkMax(LauncherConstants.kIntakeArmPort, kBrushless);
   // CANSparkMax intakeWheel = new CANSparkMax(LauncherConstants.kIntakeWheelPort, kBrushless);
    CANSparkMax UpperTransport= new CANSparkMax(LauncherConstants.kUpperTransport, kBrushless);
    CANSparkMax LowerTransport = new CANSparkMax(LauncherConstants.kLowerTransport, kBrushless);
    CANEncoder launchEncoder = new CANEncoder(launcher);
    //CANEncoder intakeArmEncoder = new CANEncoder(intakeArm);
    private AnalogInput UltraSonicSensor = new AnalogInput(0);

    /**
     * Revs up launcher and when fully reved turns on transport
     * @param speed
     */

    public void shoot(double speed) {
        launcher.set(speed);
        double currVelocity = launchEncoder.getVelocity();
        launchEncoder.setVelocityConversionFactor(0.00017618);
        SmartDashboard.putNumber("Launcher Velocity Desired", speed);
        SmartDashboard.putNumber("Launcher Velocity Actual", currVelocity);
        SmartDashboard.putNumber("Launcher Velocity Error", currVelocity - speed);
        if (Math.abs(currVelocity - speed) < 0.5) {
            transport(0.3);
        }
    }

    public void stopAll() {
        launcher.stopMotor();
        //intakeWheel.stopMotor();
        //intakeArm.stopMotor();
        UpperTransport.stopMotor();
        LowerTransport.stopMotor();
    }

    public void intakeArmLower() {
        /*intakeArm.set(0.1);
        if (intakeArmEncoder.getPosition() > 0.1) {
            intakeArm.stopMotor();
        }*/

    }

    public void intakeArmRaise() {
        /*intakeArm.set(-0.1);
        if (intakeArmEncoder.getPosition() <0.01) {
            intakeArm.stopMotor();
        }*/

    }

    public void intakeBalls() {
      //  intakeWheel.set(0.1);
    }

    public void transport(double speed) {
        //LowerTransport.setInverted(true);
        UpperTransport.set(speed);
        LowerTransport.set(speed);
    }
    /**
     * Turns on both the UpperTransportand the intake wheel
     */
    public void intake() {
        //intakeBalls();
        /*if (UltraGetDistance() < 8) {
            transport(0.3);
        } else {
            transport(0);
        }*/
        transport(0.3);

    }
    public double UltraGetDistance() {
        /*double volts = sensor.getVoltage(); //gets volts
        double mm = volts * 5 / 4.88 * 1000; //converts volts into millimeters
        double inches = mm * 0.0394; //converts millimeters to inches
        return inches;*/
        return 0;
    }
}
