package frc.robot.utils;

@FunctionalInterface
public interface Input<T> 
{
    T get();
}