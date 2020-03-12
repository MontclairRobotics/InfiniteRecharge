/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.ControllerDrive;
import frc.robot.commands.DriveForward;
import frc.robot.commands.Intake;
import frc.robot.commands.Launch;
import frc.robot.commands.LiftArm;
import frc.robot.commands.LowerArm;
//import frc.robot.commands.ReportVision;
import frc.robot.commands.Transport;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TransportSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.utils.Controllers;
import frc.robot.utils.Constants.ControlConstants;
import frc.robot.utils.Controllers.Buttons;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  
  private final LauncherSubsystem launcherSubsystem = new LauncherSubsystem();
  private final TransportSubsystem transportSubsystem = new TransportSubsystem();
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  //private final VisionSubsystem visionSubsystem = new VisionSubsystem();


  //private final ReportVision reportVision = new ReportVision(visionSubsystem);
  //private final ParallelCommandGroup intake = new ParallelCommandGroup(transport, intakeOnlyIntake);

  private final DriveForward simpleAuto = new DriveForward(driveSubsystem);

  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    driveSubsystem.setDefaultCommand(new RunCommand(
      () -> 
          driveSubsystem.arcadeDrive(
          Controllers.driver.getRawAxis(ControlConstants.kLeftY), 
          Controllers.driver.getRawAxis(ControlConstants.kRightX)
          ), driveSubsystem
      )
    );
    configureButtonBindings();
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Buttons.invert.whenPressed(new RunCommand( () -> driveSubsystem.invert()));

    Buttons.fullSpeed.whenPressed(new RunCommand(() -> driveSubsystem.setMaxSpeed(1), driveSubsystem));
    Buttons.fullSpeed.whenReleased(new RunCommand(() -> driveSubsystem.setMaxSpeed(0.5), driveSubsystem));

    Buttons.quarterSpeed.whenPressed(new RunCommand(() -> driveSubsystem.setMaxSpeed(0.25), driveSubsystem));
    Buttons.quarterSpeed.whenReleased(new RunCommand(() -> driveSubsystem.setMaxSpeed(0.5), driveSubsystem));

    Buttons.shoot.whenPressed(new Launch(launcherSubsystem));
    Buttons.intake.whenPressed(new Intake(intakeSubsystem));

    //autoChooser.initSendable(new SendableBuilderImpl());
    //autoChooser.addOption("SIMPLE", simpleAuto);
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoChooser.getSelected();
  }
}