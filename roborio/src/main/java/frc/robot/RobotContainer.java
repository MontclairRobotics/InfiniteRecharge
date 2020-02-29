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
import frc.robot.commands.Launch;
import frc.robot.commands.LiftArm;
import frc.robot.commands.LowerArm;
import frc.robot.commands.Transport;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TransportSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
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

  private final LiftArm liftArm = new LiftArm(climberSubsystem);
  private final LowerArm lowerArm = new LowerArm(climberSubsystem);
  private final Launch launch = new Launch(launcherSubsystem, transportSubsystem);
  private final Transport transport = new Transport(transportSubsystem);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  //TODO: FINALIZE PORTS
  private final Joystick driver = new Joystick(1);
  private final Joystick auxillary = new Joystick(2);

  //TODO: FINALIZE PORTS
  private final JoystickButton liftArmButton = new JoystickButton(driver, 1);
  private final JoystickButton lowerArmButton = new JoystickButton(driver, 2);
  private final JoystickButton launchButton = new JoystickButton(auxillary, 1);
  private final JoystickButton transportButton = new JoystickButton(auxillary, 2);

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    liftArmButton.whenPressed(liftArm);
    liftArmButton.negate().cancelWhenActive(liftArm);

    lowerArmButton.whenPressed(lowerArm);
    lowerArmButton.negate().cancelWhenActive(lowerArm);

    launchButton.whenPressed(launch);
    launchButton.negate().cancelWhenActive(launch);

    transportButton.whenPressed(transport);
    transportButton.negate().cancelWhenActive(transport);
  }

    /////////////////////////
   // AUTONOMOUS HANDLING //
  /////////////////////////

                            //            AUTONOMOUS ENUM            //
  public enum AutonomousMode{ //add a value for each auto command you wish to run
    SIMPLE(1), NONE(0);

    int val; //store the index of the corresponding command in autoCommands here
    AutonomousMode(int command) {val = command;}
  }

  private final DoFor simpleAuto = 
  new DoFor(
    new Drive(
      driveSubsystem, 
      ()->1,
      ()->0
    ), 
  1000);

                          //            AUTONOMOUS MODES            //
  private final CommandBase[] autoCommands = new CommandBase[]{null, simpleAuto}; //Add your auto functions here

                          //            AUTONOMOUS MODE SETTING            //
  public static AutonomousMode autonomousMode = AutonomousMode.SIMPLE;

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommands[autonomousMode.val];
  }
}
