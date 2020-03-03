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
import frc.robot.utils.Constants.ControlConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
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

  private final LiftArm liftArm = new LiftArm(climberSubsystem);
  private final LowerArm lowerArm = new LowerArm(climberSubsystem);
  private final Launch launch = new Launch(launcherSubsystem, transportSubsystem);
  private final Transport transport = new Transport(transportSubsystem);


  private final Joystick driver = new Joystick(ControlConstants.kDriver);
  private final Joystick operator = new Joystick(ControlConstants.kOperator);

  //Driver Buttons (Control Scheme will likely/definitely be changed)
  private final JoystickButton gyroLock = new JoystickButton(driver, ControlConstants.kA);
  private final JoystickButton fullSpeed = new JoystickButton(driver, ControlConstants.kLStick);
  private final JoystickButton quarterSpeed = new JoystickButton(driver, ControlConstants.kLB);
  private final JoystickButton shoot = new JoystickButton(driver, ControlConstants.kB);
  private final JoystickButton invert = new JoystickButton(driver, ControlConstants.kRB);

  //Operator Buttons (Control Scheme will likely/definitely be changed)
  private final JoystickButton intake = new JoystickButton(operator, ControlConstants.kA);
  private final JoystickButton raiseLift = new JoystickButton(operator, ControlConstants.kX);
  private final JoystickButton lowerLift = new JoystickButton(operator, ControlConstants.kY);


  // private final Button liftArmButton = Controllers.driver.Y;
  // private final Button lowerArmButton = Controllers.driver.A;
  // private final Button launchButton = Controllers.auxillary.LB;
  // private final Button transportButton = Controllers.auxillary.A;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    driveSubsystem.setDefaultCommand(new Drive(driveSubsystem, driver.getRawAxis(4), driver.getRawAxis(5)));

    configureButtonBindings();
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    raiseLift.whenPressed(liftArm);
    raiseLift.negate().cancelWhenActive(liftArm);

    lowerLift.whenPressed(lowerArm);
    lowerLift.negate().cancelWhenActive(lowerArm);

    shoot.whenPressed(launch);
    shoot.negate().cancelWhenActive(launch);

    invert.whenPressed(new RunCommand(() -> driveSubsystem.invert()));



    // Not sure if there is actually going to be a button that only does transport
    // transportButton.whenPressed(transport);
    // transportButton.negate().cancelWhenActive(transport);
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
    new Drive(driveSubsystem, 1.0, 0.0), 1000);

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
