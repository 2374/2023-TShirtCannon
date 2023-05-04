package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CanonSubsystem extends SubsystemBase {
    private CanonSubsystem instance;

    private WPI_TalonSRX canonMotor;
    private Solenoid sole;
    private Compressor compr;

    public CanonSubsystem() {
        System.out.println("NEW CANON");
        canonMotor = new WPI_TalonSRX(Constants.ELEVATOR);
        canonMotor.setNeutralMode(NeutralMode.Brake);

        // to deploy and retrack the intake requires pneumatics
        compr = new Compressor(Constants.PCMCAN, PneumaticsModuleType.CTREPCM);

        // compr.setClosedLoopControl(true);

        boolean enabled = compr.isEnabled();
        System.out.println("Compressor enabled = " + enabled);
        boolean pressureSwitch = compr.getPressureSwitchValue();
        System.out.println("Compressor switch = " + pressureSwitch);
        double current = compr.getCurrent();
        System.out.println("Compressor current = " + current);

        // compr.stop();

        sole = new Solenoid(Constants.PCMCAN, PneumaticsModuleType.CTREPCM, 0);

        sole.set(false);

    }

    public CanonSubsystem getIntakeInstance() {
        if (instance == null) {
            System.out.println("Creating new Canon Subsystem");
            instance = new CanonSubsystem();
        }

        return instance;
    }

    @Override
    public void periodic() {
    }

    public void Elevate() {
        canonMotor.set(TalonSRXControlMode.PercentOutput, -0.4);
        sole.set(false);
        System.out.println("Elevate");
    }

    public void Depress() {
        System.out.println("Depress");
        canonMotor.set(TalonSRXControlMode.PercentOutput, 0.4);
        sole.set(false);
    }

    public void Stop() {
        System.out.println("Stop");
        canonMotor.stopMotor();
    }

    public void Fire() {
        System.out.println("Fire");
        sole.set(true);
    }

    public void Close() {
        System.out.println("Clsoe");
        sole.set(false);
    }
}
