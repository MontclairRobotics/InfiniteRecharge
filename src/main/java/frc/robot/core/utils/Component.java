package frc.robot.core.utils;

public interface Component {
    default public void robotInit() {}

    default public void teleopInit() {}

    default public void teleopPeriodic() {}

    default public void autonomousPeriodic() {}
}