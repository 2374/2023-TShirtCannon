package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultDriveCommand extends CommandBase {
    // The subsystem to associate the commands with see invocation in
    // RobotContainer.java
    private final DrivetrainSubsystem m_subsystem;
    private final DoubleSupplier left;
    private final DoubleSupplier right;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public DefaultDriveCommand(DoubleSupplier left, DoubleSupplier right, DrivetrainSubsystem subsystem) {
        m_subsystem = subsystem;
        this.left = left;
        this.right = right;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    public void execute() {
        m_subsystem.drive(left.getAsDouble(), right.getAsDouble());
    }

    public void end(boolean interupted) {
        m_subsystem.drive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
