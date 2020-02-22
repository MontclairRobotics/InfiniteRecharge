/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.HeadingLock;
import frc.robot.commands.LowerIntake;
import frc.robot.commands.PortLock;
import frc.robot.commands.RaiseIntake;
import frc.robot.subsystems.*;

import static frc.robot.Constants.IOConstants.kAuxiliaryControllerPort;
import static frc.robot.Constants.IOConstants.kDriverControllerPort;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static final LauncherSubsystem launcherSubsystem = new LauncherSubsystem();
  public static final TransportSubsystem transportSubsystem = new TransportSubsystem();
  public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public static final ColorArm colorArm = new ColorArm();

  public static final XboxController driverController = new XboxController(kDriverControllerPort);
  public static final XboxController auxiliaryController = new XboxController(kAuxiliaryControllerPort);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();

    driveSubsystem.setDefaultCommand(
            new RunCommand(() -> driveSubsystem
                    .arcadeDrive(driverController.getY(Hand.kLeft),
                            driverController.getX(Hand.kRight)), driveSubsystem));

    transportSubsystem.setDefaultCommand(
            new RunCommand(()-> transportSubsystem.setTransportSpeed(
                    driverController.getY(Hand.kRight),
                    driverController.getY(Hand.kRight))));

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // ----------------------------------- Driver Controller --------------------------------------------------

    // 1/2 speed
    new JoystickButton(driverController, XboxController.Button.kBumperRight.value)
            .whenPressed(() -> driveSubsystem.setMaxOutput(0.5))
            .whenReleased(() -> driveSubsystem.setMaxOutput(1));

    // heading lock
    new JoystickButton(driverController, XboxController.Button.kStickLeft.value)
            .whenPressed(
                    new HeadingLock(0))
            .whenReleased(() -> driveSubsystem.resetNavx());

    // targeting system
    new JoystickButton(driverController, XboxController.Button.kBumperLeft.value)
            .whenPressed(new PortLock());

    // -------------------------------- Auxiliary Controller ---------------------------------------------------

    // Raise Intake
    new JoystickButton(auxiliaryController, XboxController.Button.kBumperLeft.value)
            .whenPressed(new RaiseIntake());

    // Lower Intake
    new JoystickButton(auxiliaryController, XboxController.Button.kBumperRight.value)
            .whenPressed(new LowerIntake());

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}