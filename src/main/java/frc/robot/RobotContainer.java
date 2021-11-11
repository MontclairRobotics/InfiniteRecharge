/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.sequences.AutoDrive;
import frc.robot.commands.sequences.ShootSequence;
import frc.robot.subsystems.*;
import frc.robot.utils.LinearProfile;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

//import frc.robot.utils.MotionProfile;

import static frc.robot.Constants.IOConstants.kAuxiliaryControllerPort;
import static frc.robot.Constants.IOConstants.kDriverControllerPort;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {


    //- FIELDS -//
    private static final SendableChooser<Command> autoChooser = new SendableChooser<>();
    private static final SendableChooser<DriveState> driveStateChooser = new SendableChooser<>();

    public static final DriveSubsystem driveSubsystem = new DriveSubsystem();

    public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    public static final TransportSubsystem transportSubsystem = new TransportSubsystem();
    public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    public static final LiftSubsystem liftSubsystem = new LiftSubsystem();

    public static final XboxController driverController = new XboxController(kDriverControllerPort);
    public static final XboxController auxiliaryController = new XboxController(kAuxiliaryControllerPort);




    //- FUNCTIONALITY and SETUP- //

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() 
    {
        configureButtonBindings();

        //MotionProfile profiler = new MotionProfile(() -> driverController.getY(), () -> driveSubsystem.getAverageEncoderSpeed(), 1);
        autoChooser.setDefaultOption("Auto Drive", new AutoDrive(3));
        autoChooser.addOption("Auto Shoot 3s", new ShootSequence(3));
        autoChooser.addOption("Auto Shoot 5s", new ShootSequence(5));
        SmartDashboard.putData("Auto Modes", autoChooser);

        driveStateChooser.setDefaultOption("Simple", DriveState.SIMPLE);
        driveStateChooser.addOption("Eased", DriveState.EASED);
        SmartDashboard.putData(driveStateChooser);

        //this will start the cameras (have to begin recording for them to render)
        Shuffleboard.startRecording();
        Shuffleboard.stopRecording();

        driveSubsystem.init(
            new LinearProfile(
                () -> Constants.DriveConstants.kDriveSlope,
                () -> Constants.DriveConstants.kDriveSnapFactor
            ), 
            new LinearProfile(
                () -> Constants.DriveConstants.kDriveTurnSlope,
                () -> 1
            ),
            () -> driveStateChooser.getSelected()
        );

        driveSubsystem.setDefaultCommand(
            new RunCommand(
                () -> driveSubsystem.exec(), 
                driveSubsystem
            )
        );

    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {

        // ----------------------------------- Driver Controller --------------------------------------------------

        // Max Speed Button
        new JoystickButton(driverController, XboxController.Button.kBumperRight.value)
            .whenPressed(() -> driveSubsystem.setMaxOutput(1))
            .whenReleased(() -> driveSubsystem.setMaxOutput(0.75));

        // 1/2 Speed
        new JoystickButton(driverController, XboxController.Button.kBumperLeft.value)
            .whenPressed(() -> driveSubsystem.setMaxOutput(0.75/2))
            .whenReleased(() -> driveSubsystem.setMaxOutput(0.75));

        // heading lock
        /*new JoystickButton(driverController, XboxController.Button.kStickLeft.value)
            .whenHeld(
                new HeadingLock(0))
            .whenReleased(() -> driveSubsystem.resetNavx());*/

        // targeting system 
        /*new JoystickButton(driverController, XboxController.Button.kB.value)
            .whenHeld(new PortLock());*/

        // Drive a foot
        /*new JoystickButton(driverController, XboxController.Button.kStart.value)
            .whenHeld(new DriveStraight(12));*/

        // Shoot sequence
        /*new JoystickButton(driverController, XboxController.Button.kA.value)
            .whenHeld(new ShootSequence());*/



        // -------------------------------- Auxiliary Controller ---------------------------------------------------


        // intake movement
        new JoystickButton(auxiliaryController, XboxController.Button.kB.value)
            .whenHeld(new RunCommand(()->intakeSubsystem.setIntakeSpeed(-0.75)))
            .whenReleased(new RunCommand(()-> intakeSubsystem.setIntakeSpeed(0)));

        // intake movement intaking from above
        new JoystickButton(auxiliaryController, XboxController.Button.kX.value)
            .whenHeld(new RunCommand(()-> intakeSubsystem.setIntakeSpeed(1)))
            .whenReleased(new RunCommand(()-> intakeSubsystem.setIntakeSpeed(0)));

        // Automatic Transport Stall
        /*new JoystickButton(auxiliaryController, XboxController.Button.kB.value)
            .whenHeld(new RunCommand(()-> transportSubsystem.setTransportSpeed(1,-0.7)))
            .whenReleased(new RunCommand(()-> transportSubsystem.setTransportSpeed(0, 0)));*/

        // Manual Transport Run
        new JoystickButton(auxiliaryController, XboxController.Button.kBumperLeft.value)
            .whenHeld(new RunCommand(()-> transportSubsystem.setTransportSpeed(1, 1)))
            .whenReleased(new RunCommand(()-> transportSubsystem.setTransportSpeed(0, 0)));

        // Manual Shoot
        new JoystickButton(auxiliaryController, XboxController.Button.kA.value)
            .whenHeld(new RunCommand(()-> shooterSubsystem.setSpeed(1)))
            .whenReleased(()->shooterSubsystem.setSpeed(0));
        // Lift
        /*new JoystickButton(auxiliaryController, XboxController.Button.kX.value)
            .whenHeld(new RunCommand(()-> liftSubsystem.setLiftSpeed(0.5)))
            .whenReleased(new RunCommand(()-> liftSubsystem.setLiftSpeed(0)));
        // Winch
        new JoystickButton(auxiliaryController, XboxController.Button.kY.value)
            .whenHeld(new RunCommand(()-> liftSubsystem.setWinchSpeed(-0.4)))
            .whenHeld(new RunCommand(()-> liftSubsystem.setLiftSpeed(0.1)))
            .whenReleased(new RunCommand(()-> liftSubsystem.setLiftSpeed(0)))
            .whenReleased(new RunCommand(()-> liftSubsystem.setWinchSpeed(0)));*/
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {     
        return autoChooser.getSelected();
    }
}