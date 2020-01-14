package frc.robot.core.utils;

public interface Component {
    default public void robotInit() {}
    default public void teleopInit() {}
    default public void autonomousInit() {}


    default public void teleopPeriodic() {}
    default public void autonomousPeriodic() {}
    default public void testPeriodic() {}
    /**
     * This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     *
     * Warning: This is apparently invoked even if the robot is in 
     * a diabled state.
     */
    default public void robotPeriodic() {}

}
