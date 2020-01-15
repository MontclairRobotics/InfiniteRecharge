package frc.robot.core.components;

interface climberBase {
    void armUp();
    void armDown();
    void reset();
    void pullUp();
    void climb(double height, double speed);
}

interface winchBase {
    void wind(double speed, double distance);
}

interface armBase {
    void extend();
    void retract();
}

interface moverBase {
    void balance();
    void shift(double speed, double distance);
}

public class Climber implements climberBase {
    public void armUp() {

    }

    public void armDown() {

    }

    public void reset() {

    }

    public void pullUp() {

    }

    public void climb(double height, double speed) {

    }

    public class Winch implements winchBase{

        public void wind(double speed, double distance) {

        }

    }

    public class Arm implements armBase{
        public void extend() {

        }
        
        public void retract() {

        }
    }
    public class Mover implements moverBase {
        public void balance() {

        }
        
        public void shift(double speed, double distance) {

        }
    }
}