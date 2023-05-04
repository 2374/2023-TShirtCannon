package frc.robot.commands.CanonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CanonSubsystem;

public class FireCommand extends CommandBase {
    CanonSubsystem m_canonSubsystem;
    public FireCommand (CanonSubsystem canonSubsystem) {
        m_canonSubsystem = canonSubsystem;
        addRequirements(canonSubsystem);
    }
    public void initialize() {
        m_canonSubsystem.Fire();
    }
}