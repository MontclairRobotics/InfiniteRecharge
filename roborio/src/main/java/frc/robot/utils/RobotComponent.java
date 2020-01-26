package frc.robot.utils;

public interface RobotComponent {

    default void teleopPeriodic(){};

    default void robotPeriodic(){};

}
