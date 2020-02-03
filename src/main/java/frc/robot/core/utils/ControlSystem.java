package frc.robot.core.utils;

import edu.wpi.first.wpilibj.Joystick;

public class ControlSystem{

    public static Joystick driver;
    public static Joystick auxillary;
    

    public ControlSystem() {
    }

    public static void robotInit() {
        driver = new Joystick(0);
        auxillary = new Joystick(0);
    }

    private double getAxis(Joystick joystick, Axis axis){
        switch(axis){
            case X:
                return joystick.getX();
            
            case Y:
                return joystick.getY();

            case Z:
                return joystick.getZ();

            default:
                return 0;
        }
    }

    private double getAxis(Joystick joystick, int rawAxis){
        return joystick.getRawAxis(rawAxis);
    }

    public double getJoystickAxis(Controllers controller, Axis axis){
        switch(controller){
            case DRIVER:
                return getAxis(driver, axis);

            case AUXILLARY:
                return getAxis(auxillary, axis);
            
            default:
                return 0;
        }
    }

    public double getJoystickAxis(Controllers controller, int rawAxis){
        switch(controller){
            case DRIVER:
                return getAxis(driver, rawAxis);

            case AUXILLARY:
                return getAxis(auxillary, rawAxis);
            
            default:
                return 0;
        }
    }

    public boolean getButton(DriverButtons button){
        switch(button){
            case GYRO_LOCK:
                return driver.getRawButton(button.getPort());
            
            default:
                return false;

        }
    }

    public static boolean getButton(AuxillaryButtons button){
        switch(button){
            case TEMP:
                return driver.getRawButton(button.getPort());
            
            default:
                return false;

        }
    }

    public static int getPOV(Controllers controller){
        switch(controller){
            case DRIVER:
                driver.getPOV();
            
            case AUXILLARY:
                driver.getPOV();

            default:
                return 0;
        }
    }


    public enum Controllers{
        DRIVER, AUXILLARY
    }

    public enum Axis{
        X, Y, Z
    }

    public enum DriverButtons{
        GYRO_LOCK(0), Calibrating_Sensors(1);
        int port;
        private DriverButtons(int port){
            this.port = port;
        }

        public int getPort(){
            return port;
        }
    }

    public enum AuxillaryButtons{
        TEMP(5), Fire(2), Intake(0), ColorWheel(1), Climb(3);

        int port;
        private AuxillaryButtons(int port){
            this.port = port;
        }

        public int getPort(){
            return port;
        }
    }

}