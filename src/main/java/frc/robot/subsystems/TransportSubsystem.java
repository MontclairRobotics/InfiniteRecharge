package frc.robot.subsystems;


import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.LauncherConstants;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.TransportConstants.*;

public class TransportSubsystem extends SubsystemBase{

    private final CANSparkMax top = new CANSparkMax(kTransportTopPort, kBrushless);
    private final CANSparkMax bot = new CANSparkMax(kTransportBotPort, kBrushless);

    public void setTransportSpeed(double speed) {
        top.set(speed);
        bot.set(speed);
    }

    public void setTransportSpeed(double topSpeed, double botSpeed){
        top.set(topSpeed);
        bot.set(botSpeed);
    }

}
