package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberManager{

    public static Command getInstance(ClimberSubsystem subsystem, LiftArm armLiftingCommand, LowerArm armLoweringCommand) {
        return new ParallelCommandGroup( new AscensionManager(subsystem, armLiftingCommand), new DescensionManager(subsystem, armLiftingCommand), new ArmLoweringManager(subsystem, armLoweringCommand));
    }

    private ClimberManager() {}
    
}