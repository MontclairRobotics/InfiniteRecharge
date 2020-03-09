/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.HeadingLock;
import frc.robot.commands.VisionLock;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Network;

import static frc.robot.Constants.IOConstants.kDriverControllerPort;
import static frc.robot.Constants.IOConstants.kAuxControllerPort;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final Launcher launcher = new Launcher();
  private final Network network = new Network();

  Joystick driverController = new Joystick(kDriverControllerPort);
  Joystick auxController = new Joystick(kAuxControllerPort);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();

    driveSubsystem.setDefaultCommand(
            new RunCommand(() -> driveSubsystem
                    .arcadeDrive(driverController.getRawAxis(1),
                            driverController.getRawAxis(5))));
    launcher.setDefaultCommand(
            new RunCommand(() -> launcher.stopAll()));

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Drive train 1/2 speed
        new JoystickButton(driverController, XboxController.Button.kBumperRight.value)
            .whenPressed(() -> driveSubsystem.setMaxOutput(0.5))
            .whenReleased(() -> driveSubsystem.setMaxOutput(1));

    // Drive train Heading lock
        new JoystickButton(driverController, XboxController.Button.kStickLeft.value)
            .whenPressed(
                    new HeadingLock(driverController.getY(GenericHID.Hand.kLeft),
                            0,
                            driveSubsystem))
            .whenReleased(() -> driveSubsystem.resetNavx());

        new POVButton(driverController, 90)
            .whenPressed(
                    new HeadingLock(0,
                            90,
                            driveSubsystem))
            .whenReleased(() -> driveSubsystem.resetNavx());

        new POVButton(driverController, 180)
            .whenPressed(
                    new HeadingLock(0,
                            180,
                            driveSubsystem))
            .whenReleased(() -> driveSubsystem.resetNavx());

        new POVButton(driverController, 270)
            .whenPressed(
                    new HeadingLock(0,
                            -90,
                            driveSubsystem))
            .whenReleased(() -> driveSubsystem.resetNavx());


            //shoots
        new JoystickButton(driverController, XboxController.Button.kA.value) 
        .whenPressed(() -> launcher.shoot(1))
        .whenReleased(() -> launcher.stopAll());
        //intakes
        new JoystickButton(driverController, XboxController.Button.kB.value)
        .whenPressed(() -> launcher.intake())
        .whenReleased(() -> launcher.stopAll());


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
        double auto = SmartDashboard.getNumber("Auto", 1);
        HeadingLock driveOffLine = new HeadingLock(1, 0, driveSubsystem);
        HeadingLock driveToAim = new HeadingLock(3, 0, driveSubsystem);
        VisionLock aiming = new VisionLock(0, 0, driveSubsystem, network);
        if (driveToAim.isFinished()) {
                return (aiming);
        }
        
        switch ((int)auto) {
                case 1:
                return (driveOffLine);
                case 2:
                return (driveToAim);
                default:
                return(driveOffLine);
        }
        
  }
}