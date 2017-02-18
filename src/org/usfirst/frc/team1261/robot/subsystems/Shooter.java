package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	public static final FeedbackDevice FEEDBACK_DEVICE = FeedbackDevice.QuadEncoder;
	public static final int MOTOR_ENCODER_CODES_PER_REV = 1077;
	public static final float MOTOR_NOMINAL_OUTPUT_VOLTAGE = 0.0f;
	public static final float MOTOR_PEAK_OUTPUT_VOLTAGE = 12.0f;
	public static final int MOTOR_PIDF_PROFILE = 0;
	public static final double MOTOR_GAIN_F = 0.0;
	public static final double MOTOR_GAIN_P = 0.001;
	public static final double MOTOR_GAIN_I = 0.0;
	public static final double MOTOR_GAIN_D = 0.0;
	
	public static final double REQUIRED_FLYWHEEL_SPEED = 200.0;
	public static final double MINIMUM_FLYWHEEL_SPEED = 600.0;
	
	CANTalon shooterMotor = RobotMap.shooterMotor;
	
	public Shooter() {
		getShooterMotor().setFeedbackDevice(FEEDBACK_DEVICE);
		getShooterMotor().reverseSensor(false);
		getShooterMotor().configEncoderCodesPerRev(MOTOR_ENCODER_CODES_PER_REV);
		getShooterMotor().configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		getShooterMotor().configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		getShooterMotor().setProfile(MOTOR_PIDF_PROFILE);
		getShooterMotor().setF(MOTOR_GAIN_F);
		getShooterMotor().setP(MOTOR_GAIN_P);
		getShooterMotor().setI(MOTOR_GAIN_I);
		getShooterMotor().setD(MOTOR_GAIN_D);
	}

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public CANTalon getShooterMotor() {
    	return shooterMotor;
    }
    
    public void setFlywheelSpeed(double speed){
		getShooterMotor().changeControlMode(TalonControlMode.Speed);
		getShooterMotor().set(speed);
	}
	
	public void setFlywheelPower(double power){
		getShooterMotor().changeControlMode(TalonControlMode.PercentVbus);
		getShooterMotor().set(power);
	}
	
	public boolean meetsSpeed(double speed) {
		if (Math.abs(shooterMotor.getEncVelocity()) < speed)
			return false;
		return true;
	}
	
	/**
	 * Checks if the speed is high enough to allow the ball to <b>exit the shooter</b>.
	 * @return A boolean value indicating if the flywheel meets {@link #REQUIRED_FLYWHEEL_SPEED}.
	 * @see #meetsMinimumSpeed()
	 * @see #meetsSpeed(double)
	 */
	public boolean meetsRequiredSpeed() {
		return meetsSpeed(REQUIRED_FLYWHEEL_SPEED);
	}
	
	/**
	 * Checks if the speed is high enough to allow for <b>efficient shooting</b>.
	 * @return A boolean value indicating if the flywheel meets {@link #MINIMUM_FLYWHEEL_SPEED}.
	 * @see #meetsRequiredSpeed()
	 * @see #meetsSpeed(double)
	 */
	public boolean meetsMinimumSpeed() {
		return meetsSpeed(MINIMUM_FLYWHEEL_SPEED);
	}
	
	public void stop() {
		setFlywheelPower(0.0);
	}
}

