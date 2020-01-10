package frc.robot.core.components;
import frc.robot.core.components.ControlSystem.AuxillaryButtons;
import frc.robot.core.components.ControlSystem.DriverButtons;

public class LauncherRest implements LauncherState {
    public LauncherState run() {
        ControlSystem controlsystem = new ControlSystem();
        AuxillaryButtons button = null;//need to change

        if (controlsystem.getButton(button) == true) {
             LauncherState state = new LauncherRevUp(null, null);//need to change
             state = state.run();
            } 
        return this;
    }
}