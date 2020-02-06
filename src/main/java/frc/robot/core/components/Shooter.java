package frc.robot.core.components;

import frc.robot.core.utils.Component;
import frc.robot.core.utils.ControlSystem;
import frc.robot.core.utils.Hardware;
import frc.robot.core.utils.fsm.FiniteStateMachine;

public class Shooter implements Component {
    FiniteStateMachine launcherStateMachine;
    
    private double launchPower;

    public Shooter(double launchPower) {
        this.launchPower = launchPower;
    }
    public Shooter() {
        this(-1);
    }

    public void robotInit() {

    }

    public void teleopPeriodic() {
        if (ControlSystem.driver.getRawButton(ControlSystem.AuxillaryButtons.Fire.getPort())) {
            
        }
    }
    public void robotPeriodic() {
        //SmartDashboard things go here
    }
    public void autonomousPeriodic() {}

    public void belt() {
        //TODO: Tune
        Hardware.TransportBelt.set(0.1);
    }
    public void shoot() {
    }


}