package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.TransportConstants.*;

public class TransportSubsystem extends SubsystemBase{

    private final CANSparkMax top = new CANSparkMax(kTransportTopPort, kBrushless);
    private final CANSparkMax bot = new CANSparkMax(kTransportBotPort, kBrushless);
    private final AnalogInput ultraSonicSensor = new AnalogInput(0);
    public  int ballCount = 0;


    public void setTransportSpeed(double speed) {
        top.set(speed);
        bot.set(speed);
    }

    public void setTransportSpeed(double topSpeed, double botSpeed){
        top.set(topSpeed);
        bot.set(botSpeed);
    }

    public boolean getIsBall() {
        boolean isBall = false;
        double volts = ultraSonicSensor.getVoltage(); //gets volts
        double mm = volts * 5 / 4.88 * 1000; //converts volts into millimeters
        double inches = mm * 0.0394; //converts millimeters to inches
        if (inches < 8) {
            isBall = true;
        } else {
            isBall = false;
        }
        return isBall;

    }
    public int getBallCount() {
        boolean hadSpace = true;
        if (getIsBall() && hadSpace == true) {
            ballCount = ballCount + 1;
            hadSpace = false;
        } else if (!getIsBall()){
            hadSpace = true;
        }
        return ballCount;
    }


}
