package frc.robot.core.components.Drivetrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.core.utils.Hardware;

public class NavigationalDrive {
    private AHRS navx;
    private PIDController 
        gyro_lock, 
        driving_distance, 
        turning;
    private final double TICKS_PER_INCH = (13/5)*12/36;
    private SpeedControllerGroup rightSide = new SpeedControllerGroup(Hardware.DT_FR, Hardware.DT_BR);
    private SpeedControllerGroup leftSide = new SpeedControllerGroup(Hardware.DT_FL, Hardware.DT_BL);

    public NavigationalDrive(){
        gyro_lock = new PIDController(0, 0, 0);
        driving_distance = new PIDController(0, 0, 0);
        turning = new PIDController(0, 0, 0);
        gyro_lock.enableContinuousInput(-180, 180);
        driving_distance.enableContinuousInput(0, 1000);
        turning.enableContinuousInput(0, 360);
    }
    public void  gyroLock() {
        gyro_lock.setSetpoint(navx.pidGet());
        
        rightSide.set(gyro_lock.calculate(navx.pidGet()));
        leftSide.set(gyro_lock.calculate(-navx.pidGet()));
    }
    public void drivingDistance(double distance) {
        gyroLock();
        Hardware.leftEncoder.setPositionConversionFactor(TICKS_PER_INCH);
        Hardware.rightEncoder.setPositionConversionFactor(TICKS_PER_INCH);
        driving_distance.setSetpoint(distance / TICKS_PER_INCH);
    }
    public void turning(double angle) {
        turning.setSetpoint(angle);
        
    }

}