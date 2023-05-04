package frc.robot.commands.CanonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CanonSubsystem;

public class ShootCommand extends SequentialCommandGroup {
    public ShootCommand (CanonSubsystem canonSubsystem) {
        addCommands(
            new FireCommand(canonSubsystem),
            new WaitCommand(.5),
            new CloseCommand(canonSubsystem)
        );
    }
}