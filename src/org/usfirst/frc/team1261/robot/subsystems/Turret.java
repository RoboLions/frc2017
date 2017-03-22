package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.JoystickTurret;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {

	public static final int MOTOR_ENCODER_TICKS_PER_REV = (int) Math.round(1024.0 * 32 / 9);
	public static final double MOTOR_NOMINAL_OUTPUT_VOLTAGE = 0.5;
	public static final double MOTOR_PEAK_OUTPUT_VOLTAGE = 9.0;
	public static final int MOTOR_FPID_PROFILE = 0;
	public static final double MOTOR_F_GAIN = 0.0;
	public static final double MOTOR_P_GAIN = 0.2;
	public static final double MOTOR_I_GAIN = 0.00005;
	public static final double MOTOR_D_GAIN = 0.0;
	public static final int MOTOR_TOLERANCE = (int) (1.0 / 360.0 * MOTOR_ENCODER_TICKS_PER_REV); // 1deg

	public static final double MIN_TURRET_ANGLE = -200.0;
	public static final double MAX_TURRET_ANGLE = 200.0;

	public static final double MIN_SERVO_POSITION = 0.40;
	public static final double MAX_SERVO_POSITION = 0.575;
	public static final double MIN_SERVO_ANGLE = 36.62; // angle at min position
	public static final double MAX_SERVO_ANGLE = 62.62; // angle at max position

	public static final double SERVO_DELTA_PER_DEGREE = (MAX_SERVO_POSITION - MIN_SERVO_POSITION)
			/ (MAX_SERVO_ANGLE - MIN_SERVO_ANGLE);
	public static final double SERVO_POSITION_ZERO_DEG = MIN_SERVO_POSITION
			- (SERVO_DELTA_PER_DEGREE * MIN_SERVO_ANGLE);

	CANTalon turretRotationMotor = RobotMap.turretRotationMotor;
	Servo turretElevationServo = RobotMap.turretElevationServo;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Turret() {
		turretRotationMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		turretRotationMotor.reverseSensor(false);
		turretRotationMotor.reverseOutput(true);
		turretRotationMotor.configEncoderCodesPerRev(MOTOR_ENCODER_TICKS_PER_REV);
		turretRotationMotor.configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		turretRotationMotor.configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		turretRotationMotor.setProfile(MOTOR_FPID_PROFILE);
		turretRotationMotor.setF(MOTOR_F_GAIN);
		turretRotationMotor.setP(MOTOR_P_GAIN);
		turretRotationMotor.setI(MOTOR_I_GAIN);
		turretRotationMotor.setD(MOTOR_D_GAIN);
		turretRotationMotor.setAllowableClosedLoopErr(MOTOR_TOLERANCE);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoystickTurret());
	}

	public void setTurretPower(double power) {
		turretRotationMotor.changeControlMode(TalonControlMode.PercentVbus);
		turretRotationMotor.set(power);
	}

	/**
	 * Changes turret rotation motor control mode to
	 * {@link TalonControlMode#Position}, and sets the heading the turret is
	 * facing, capped between {@link #MIN_TURRET_ANGLE} and
	 * {@link #MAX_TURRET_ANGLE}.
	 * 
	 * @param angle
	 *            Angle, in degrees.
	 */
	public void setTurretAngle(double angle) {
		turretRotationMotor.changeControlMode(TalonControlMode.Position);

		if (angle > MAX_TURRET_ANGLE) {
			angle = MAX_TURRET_ANGLE;
		} else if (angle < MIN_TURRET_ANGLE) {
			angle = MIN_TURRET_ANGLE;
		}

		turretRotationMotor.set(angle / 360.0);
	}

	public double getTurretPosition() {
		return turretRotationMotor.getPosition();
	}

	/**
	 * Returns the heading the turret is facing.
	 * 
	 * @return Angle, in degrees.
	 */
	public double getTurretAngle() {
		return turretRotationMotor.getPosition() * 360.0;
		// >>>>>>> 2928885723344580fae33ed8ee303d4a3a167f12
	}

	/*
	 * DO NOT TOUCH ANY SERVO CODE WITHOUT EXPLICIT CONSENT FROM CHRIS. SERVO
	 * CODE IS VERY SENSITIVE AND WILL BREAK OUR $42 EQUIPMENT IF YOU DO IT
	 * WRONG
	 */
	public void setServoPosition(double value) {
		if (value > MAX_SERVO_POSITION) {
			value = MAX_SERVO_POSITION;
		} else if (value < MIN_SERVO_POSITION) {
			value = MIN_SERVO_POSITION;
		}

		turretElevationServo.set(value);
	}

	/*
	 * DO NOT TOUCH ANY SERVO CODE WITHOUT EXPLICIT CONSENT FROM CHRIS. SERVO
	 * CODE IS VERY SENSITIVE AND WILL BREAK OUR $42 EQUIPMENT IF YOU DO IT
	 * WRONG
	 */
	public void setServoPositionRelative(double value) {
		setServoPosition(getServoPosition() + value);
	}

	public double getServoPosition() {
		return turretElevationServo.get();
	}

	/**
	 * Sets the servo's position based on the desired angle from the ground.
	 * 
	 * @param angle
	 *            The angle from the ground, in degrees.
	 */
	public void setServoAngle(double angle) {
		setServoPosition((SERVO_DELTA_PER_DEGREE * angle) + SERVO_POSITION_ZERO_DEG);
	}

	/**
	 * Sets the servo's position relative to its current position based on the
	 * desired change in angle.
	 * 
	 * @param angleDelta
	 *            The desired change in angle, in degrees.
	 */
	public void setServoAngleRelative(double angleDelta) {
		setServoPositionRelative(SERVO_DELTA_PER_DEGREE * angleDelta);
	}

	/**
	 * Gets the servo's position as the angle from the ground.
	 * 
	 * @return The angle from the ground, in degrees.
	 */
	public double getServoAngle() {
		return (getServoPosition() - SERVO_POSITION_ZERO_DEG) / SERVO_DELTA_PER_DEGREE;
	}

	public void stop() {
		setTurretPower(0.0);
	}
	
	public CANTalon getTurretMotor() {
		return turretRotationMotor;
	}
}
