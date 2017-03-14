package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flywheel extends Subsystem {

	public static final FeedbackDevice FEEDBACK_DEVICE = FeedbackDevice.QuadEncoder;
	public static final int MOTOR_ENCODER_CODES_PER_REV = 1024;
	public static final double MOTOR_NOMINAL_OUTPUT_VOLTAGE = 0.0;
	public static final double MOTOR_PEAK_OUTPUT_VOLTAGE = 12.0;
	public static final int MOTOR_PIDF_PROFILE = 0;
	public static final double MOTOR_GAIN_F = 0.03;
	public static final double MOTOR_GAIN_P = 0.0; // 0.03;
	public static final double MOTOR_GAIN_I = 0.0;
	public static final double MOTOR_GAIN_D = 0.0;
	public static final double PERCENT_TOLERANCE = 1.0;
	public static final double NATIVE_UNITS_PER_RPM = 4096.0 / 60.0 / 10.0;

	public static final double REQUIRED_FLYWHEEL_SPEED = 200.0;
	public static final double MINIMUM_FLYWHEEL_SPEED = 600.0;

	public static final boolean FOLLOWER_MOTOR_REVERSED = false;

	CANTalon leaderFlywheelMotor = RobotMap.topFlywheelMotor;
	CANTalon followerFlywheelMotor = RobotMap.bottomFlywheelMotor;

	private FlywheelTBHSpeedController tbhSpeedController = new FlywheelTBHSpeedController(this);

	private enum FlywheelControlMode {
		SPEED, POWER, VOLTAGE;

		/**
		 * Get the {@link TalonControlMode} in which the
		 * {@link Flywheel#leaderFlywheelMotor leaderFlywheelMotor} must be
		 * operating for this flywheel control mode to function.
		 * 
		 * @return The {@link TalonControlMode} to which to set the
		 *         {@link Flywheel#leaderFlywheelMotor leaderFlywheelMotor}.
		 */
		private TalonControlMode getTalonControlMode() {
			switch (this) {
			case VOLTAGE:
				return TalonControlMode.Voltage;
			case SPEED:
			case POWER:
			default:
				return TalonControlMode.PercentVbus;
			}

			// You may be thinking, "wait a minute, this returns PercentVbus for
			// SPEED!" And you're right. SPEED mode is handled internally by our
			// take-back-half speed controller, which requires the Talon to be
			// in PercentVbus mode so that it can work its magic. So, no, don't
			// change SPEED to return TalonControlMode.Speed; that will break
			// things. -RRS
		}
	}

	public Flywheel() {
		stop(); // reset leader motor to PercentVbus mode and 0% speed

		leaderFlywheelMotor.setFeedbackDevice(FEEDBACK_DEVICE);
		leaderFlywheelMotor.reverseSensor(false);
		leaderFlywheelMotor.configEncoderCodesPerRev(MOTOR_ENCODER_CODES_PER_REV);
		leaderFlywheelMotor.configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		leaderFlywheelMotor.configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		leaderFlywheelMotor.setProfile(MOTOR_PIDF_PROFILE);
		leaderFlywheelMotor.setF(MOTOR_GAIN_F);
		leaderFlywheelMotor.setP(MOTOR_GAIN_P);
		leaderFlywheelMotor.setI(MOTOR_GAIN_I);
		leaderFlywheelMotor.setD(MOTOR_GAIN_D);

		followerFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerFlywheelMotor.set(leaderFlywheelMotor.getDeviceID());
		followerFlywheelMotor.reverseOutput(FOLLOWER_MOTOR_REVERSED);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	}

	public CANTalon getFlywheelMotor() {
		return leaderFlywheelMotor;
	}

	public void setFlywheelSpeed(double speed) {
		// The following is untested code! If it doesn't work, change it back.

		// Old code:
		//leaderFlywheelMotor.changeControlMode(TalonControlMode.Speed);
		//leaderFlywheelMotor.set(speed);

		// New, untested code:
		changeControlMode(FlywheelControlMode.SPEED);
		tbhSpeedController.setSetpoint(speed);
	}

	public void setFlywheelVoltage(double voltage) {
		changeControlMode(FlywheelControlMode.VOLTAGE);
		leaderFlywheelMotor.set(voltage);
	}

	public void setFlywheelPower(double power) {
		changeControlMode(FlywheelControlMode.POWER);
		leaderFlywheelMotor.set(power);
	}

	private void changeControlMode(FlywheelControlMode controlMode) {
		if (controlMode == FlywheelControlMode.SPEED) {
			tbhSpeedController.start();
		} else {
			tbhSpeedController.stop();
		}

		leaderFlywheelMotor.changeControlMode(controlMode.getTalonControlMode());
	}

	public boolean meetsSpeed(double speed) {
		return (Math.abs(leaderFlywheelMotor.getEncVelocity()) >= speed);
	}

	/**
	 * Checks if the speed is high enough to allow the ball to <b>exit the
	 * turret</b>.
	 * 
	 * @return A boolean value indicating if the flywheel meets
	 *         {@link #REQUIRED_FLYWHEEL_SPEED}.
	 * @see #meetsMinimumSpeed()
	 * @see #meetsSpeed(double)
	 */
	public boolean meetsRequiredSpeed() {
		return meetsSpeed(REQUIRED_FLYWHEEL_SPEED);
	}

	/**
	 * Checks if the speed is high enough to allow for <b>efficient
	 * shooting</b>.
	 * 
	 * @return A boolean value indicating if the flywheel meets
	 *         {@link #MINIMUM_FLYWHEEL_SPEED}.
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
