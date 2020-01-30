/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.utils.Controls;
import frc.robot.component.Drivetrain;
import frc.robot.utils.Utils;
import frc.robot.utils.fsm.FiniteStateMachine;
import frc.robot.utils.fsm.stateMachines.ColorRotateTest;
import frc.robot.utils.fsm.stateMachines.DriveToLine;
import frc.robot.utils.fsm.states.ColorRotateState;

import java.awt.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String AutoDrive = "Auto Drive";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private Controls controls;
  private Drivetrain drivetrain;

  /**
   * State Machines
   */
  private DriveToLine driveToLine = new DriveToLine(this);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("Auto Drive", AutoDrive);
    SmartDashboard.putData("Auto choices", m_chooser);

    controls = new Controls();
    drivetrain = new Drivetrain();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    drivetrain.robotPeriodic();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    driveToLine = new DriveToLine(this);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case AutoDrive:
        driveToLine.run();
      case kDefaultAuto:
      default:
        driveToLine.run();
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drivetrain.teleopPeriodic();
  }

  CANSparkMax test;
  ColorSensorV3 colorSensorV3;
  ColorRotateState colorRotateState;
  FiniteStateMachine colorTest;

  @Override
  public void testInit() {
    test = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushed);
    colorSensorV3 = new ColorSensorV3(I2C.Port.kOnboard);
    colorRotateState = new ColorRotateState(test, colorSensorV3);
    colorTest = new FiniteStateMachine("", colorRotateState);

    colorTest.reset();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
//
//    test.set(0.5);
//
//    SmartDashboard.putNumber("Prox", colorSensorV3.getProximity());
//    SmartDashboard.putBoolean("Blue", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),0.2,0.3));
//    SmartDashboard.putBoolean("Yellow", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),2,3));
//    SmartDashboard.putBoolean("Red", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),3.8,4.4));
//    SmartDashboard.putBoolean("Green", Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),0.4,0.8));
//
//    if(Utils.threshold((double)colorSensorV3.getRed()/colorSensorV3.getBlue(),2,3)){
//      test.set(0);
//    }
    colorTest.run();


  }


  public Drivetrain getDrivetrain(){
    return this.drivetrain;
  }
}
