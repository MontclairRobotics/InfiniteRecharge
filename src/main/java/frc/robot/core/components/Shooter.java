package frc.robot.core.components;

import frc.robot.core.utils.Component;
import frc.robot.core.utils.ControlSystem;
import frc.robot.core.utils.Hardware;

public class Shooter implements Component {
    
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

        shoot();
        transportBall();
        intakeBall();

    }
    public void robotPeriodic() {
        //SmartDashboard things go here
    }
    public void autonomousPeriodic() {}

    public void intakeBall() {
        //TODO: change the negative 69
        Hardware.BallIntakeHandler.run( ControlSystem.auxillary.getRawButton(-69) );
        Hardware.IntakeArmHandler.run( ControlSystem.auxillary.getY() );

    }

    public void transportBall() {

        if( ControlSystem.auxillary.getRawButton(ControlSystem.AuxillaryButtons.Intake.getPort()) ) { 
            
            Hardware.TransportBelt.set(0.1);

        } else {

            Hardware.TransportBelt.set(0);

        }

    }

    public void shoot() {

        if (ControlSystem.driver.getRawButton(ControlSystem.AuxillaryButtons.Fire.getPort())) {
            
            //TODO: this

        }

    }

}