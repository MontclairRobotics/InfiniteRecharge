package frc.robot.component;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cameraserver.CameraServerShared;
import frc.robot.utils.Controls;
import frc.robot.utils.RobotComponent;

public class VisionFeed implements RobotComponent {

    UsbCamera controlPanelFeed;
    UsbCamera shieldGeneratorAlignment;

    VideoSink videoSink;

    boolean switchFeed;

    public VisionFeed(){
        controlPanelFeed = CameraServer.getInstance().startAutomaticCapture();
        shieldGeneratorAlignment = CameraServer.getInstance().startAutomaticCapture();
        videoSink = CameraServer.getInstance().getServer();

        videoSink.setSource(controlPanelFeed);
        switchFeed = false;
    }

    @Override
    public void teleopPeriodic() {
        if(Controls.auxiliary.getStartButton()){
            if(switchFeed){
                videoSink.setSource(shieldGeneratorAlignment);
                switchFeed = false;
            }
        }


    }

    @Override
    public void robotPeriodic() {

    }
}
