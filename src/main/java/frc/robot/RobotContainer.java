// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.CanonCommands.ShootCommand;
import frc.robot.subsystems.CanonSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
    private final CanonSubsystem m_canonSubsystem = new CanonSubsystem();

    // private final DriveCommand m_driveCommand = new
    // DriveCommand(m_drivetrainSubsystem);

    private static XboxController m_controller = new XboxController(0);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        m_drivetrainSubsystem.setDefaultCommand(
                new DefaultDriveCommand(() -> m_controller.getLeftY(), () -> m_controller.getRightY(),
                        m_drivetrainSubsystem));
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        new Trigger(m_controller::getBButton).debounce(0.7, DebounceType.kFalling)
                .onTrue(new ShootCommand(m_canonSubsystem));
        new Trigger(m_controller::getYButton).onTrue(new InstantCommand(() -> m_canonSubsystem.Elevate()));
        new Trigger(m_controller::getYButton).onFalse(new InstantCommand(() -> m_canonSubsystem.Stop()));
        new Trigger(m_controller::getAButton).onTrue(new InstantCommand(() -> m_canonSubsystem.Depress()));
        new Trigger(m_controller::getAButton).onFalse(new InstantCommand(() -> m_canonSubsystem.Stop()));
    }

    // /**
    // * Use this to pass the autonomous command to the main {@link Robot} class.
    // *
    // * @return the command to run in autonomous
    // */
    // public Command getAutonomousCommand() {
    // // An ExampleCommand will run in autonomous
    // return m_driveCommand;
    // }
}
