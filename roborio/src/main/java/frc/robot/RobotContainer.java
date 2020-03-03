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
import frc.robot.commands.DoFor;
import frc.robot.commands.Drive;
import frc.robot.commands.Intake;
import frc.robot.commands.Launch;
import frc.robot.commands.LiftArm;
import frc.robot.commands.LowerArm;
import frc.robot.commands.Transport;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TransportSubsystem;
import frc.robot.utils.Controllers;
import frc.robot.utils.Constants.ControlConstants;
import frc.robot.utils.Controllers.Buttons;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  private final LauncherSubsystem launcherSubsystem = new LauncherSubsystem();
  private final TransportSubsystem transportSubsystem = new TransportSubsystem();
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  private final LiftArm liftArm = new LiftArm(climberSubsystem);
  private final LowerArm lowerArm = new LowerArm(climberSubsystem);
  private final Launch launch = new Launch(launcherSubsystem, transportSubsystem);
  private final Transport transport = new Transport(transportSubsystem);
  private final Intake intakeOnlyIntake = new Intake(intakeSubsystem);
  private final ParallelCommandGroup intake = new ParallelCommandGroup(transport, intakeOnlyIntake);

  // private final Button liftArmButton = Controllers.driver.Y;
  // private final Button lowerArmButton = Controllers.driver.A;
  // private final Button launchButton = Controllers.auxillary.LB;
  // private final Button transportButton = Controllers.auxillary.A;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    driveSubsystem.setDefaultCommand(new Drive(driveSubsystem, Controllers.driver.getRawAxis(4), Controllers.driver.getRawAxis(5)));

    configureButtonBindings();
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Buttons.raiseLift.whenPressed(liftArm);

    Buttons.lowerLift.whenPressed(lowerArm);

    Buttons.shoot.whenPressed(launch);

    Buttons.intake.whenActive(intake);
    Buttons.invert.whenPressed(new RunCommand( () -> driveSubsystem.invert()));

    Buttons.fullSpeed.whenPressed(new RunCommand(() -> driveSubsystem.setMaxSpeed(1)));
    Buttons.fullSpeed.whenReleased(new RunCommand(() -> driveSubsystem.setMaxSpeed(0.5)));


    Buttons.quarterSpeed.whenPressed(new RunCommand(() -> driveSubsystem.setMaxSpeed(0.25)));
    Buttons.quarterSpeed.whenReleased(new RunCommand(() -> driveSubsystem.setMaxSpeed(0.5)));

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return simpleAuto;
  }
}
