package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.JoystickDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public static final FeedbackDevice FEEDBACK_DEVICE = FeedbackDevice.QuadEncoder;
	public static final int MOTOR_ENCODER_CODES_PER_REV = 1077;
	public static final double MOTOR_NOMINAL_OUTPUT_VOLTAGE = 0.0;
	public static final double MOTOR_PEAK_OUTPUT_VOLTAGE = 12.0;
	public static final int MOTOR_PIDF_PROFILE = 0;
	public static final double MOTOR_GAIN_F = 0.0;
	public static final double MOTOR_GAIN_P = 0.001;
	public static final double MOTOR_GAIN_I = 0.0;
	public static final double MOTOR_GAIN_D = 0.0;

	public static final double RANGEFINDER_VOLTS_TO_DISTANCE_FACTOR = 1.0;
	public static final double RANGEFINDER_NO_SIGNAL_THRESHOLD = 0.25;

	// Not actually used in this subsystem, but can be used by autonomous
	// commands
	public static final double DEFAULT_VOLTAGE_RAMP_RATE = 8.0;

	CANTalon leftMotorFront = RobotMap.leftDriveMotorFront;
	CANTalon leftMotorRear = RobotMap.leftDriveMotorRear;
	CANTalon rightMotorFront = RobotMap.rightDriveMotorFront;
	CANTalon rightMotorRear = RobotMap.rightDriveMotorRear;
	RobotDrive driveTrain = RobotMap.robotDrive;
	AnalogInput laserRangeFinder = RobotMap.frontRangeFinder;

	// Change this to change the default PIDController for the DriveTrain.
	PIDController controller = new DisabledDriveTrainPIDController(this);

	/**
	 * Predefined {@link PIDController}s that the {@link DriveTrain} can use.
	 */
	public static enum DriveTrainPIDController {
		/**
		 * A distance-based {@link PIDController} for the {@link DriveTrain}.
		 */
		DISTANCE,
		/**
		 * An angle-based {@link PIDController} for the {@link DriveTrain}.
		 */
		ANGLE,
		/**
		 * A vision-tracking-based {@link PIDController} for the
		 * {@link DriveTrain}.
		 */
		VISION_TRACK,
		/**
		 * A {@link PIDController} for the {@link DriveTrain} that does nothing.
		 */
		DISABLED;

		private PIDController getPIDControllerForDriveTrain(DriveTrain driveTrain) {
			switch (this) {
			/*
			 * case DISTANCE: return new
			 * DistanceBasedDriveTrainPIDController(driveTrain); case ANGLE:
			 * return new AngleBasedDriveTrainPIDController(driveTrain);
			 */
			case DISTANCE:
				return new DistanceBasedDriveTrainPIDController(driveTrain);
			case VISION_TRACK:
				return new VisionTrackingBasedDriveTrainPIDController(driveTrain);
			default:
				return new DisabledDriveTrainPIDController(driveTrain);
			}
		}
	}

	public class RangeFinderNoSignalException extends Exception {
		private static final long serialVersionUID = 7796196790913368565L;
	}

	public DriveTrain() {
		leftMotorFront.setFeedbackDevice(FEEDBACK_DEVICE);
		leftMotorFront.reverseSensor(false);
		leftMotorFront.configEncoderCodesPerRev(MOTOR_ENCODER_CODES_PER_REV);
		leftMotorFront.configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		leftMotorFront.configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		leftMotorFront.setProfile(MOTOR_PIDF_PROFILE);
		leftMotorFront.setF(MOTOR_GAIN_F);
		leftMotorFront.setP(MOTOR_GAIN_P);
		leftMotorFront.setI(MOTOR_GAIN_I);
		leftMotorFront.setD(MOTOR_GAIN_D);

		leftMotorRear.setFeedbackDevice(FEEDBACK_DEVICE);
		leftMotorRear.reverseSensor(false);
		leftMotorRear.configEncoderCodesPerRev(MOTOR_ENCODER_CODES_PER_REV);
		leftMotorRear.configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		leftMotorRear.configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		leftMotorRear.setProfile(MOTOR_PIDF_PROFILE);
		leftMotorRear.setF(MOTOR_GAIN_F);
		leftMotorRear.setP(MOTOR_GAIN_P);
		leftMotorRear.setI(MOTOR_GAIN_I);
		leftMotorRear.setD(MOTOR_GAIN_D);

		rightMotorFront.setFeedbackDevice(FEEDBACK_DEVICE);
		rightMotorFront.reverseSensor(false);
		rightMotorFront.configEncoderCodesPerRev(MOTOR_ENCODER_CODES_PER_REV);
		rightMotorFront.configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		rightMotorFront.configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		rightMotorFront.setProfile(MOTOR_PIDF_PROFILE);
		rightMotorFront.setF(MOTOR_GAIN_F);
		rightMotorFront.setP(MOTOR_GAIN_P);
		rightMotorFront.setI(MOTOR_GAIN_I);
		rightMotorFront.setD(MOTOR_GAIN_D);

		rightMotorRear.setFeedbackDevice(FEEDBACK_DEVICE);
		rightMotorRear.reverseSensor(false);
		rightMotorRear.configEncoderCodesPerRev(MOTOR_ENCODER_CODES_PER_REV);
		rightMotorRear.configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		rightMotorRear.configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		rightMotorRear.setProfile(MOTOR_PIDF_PROFILE);
		rightMotorRear.setF(MOTOR_GAIN_F);
		rightMotorRear.setP(MOTOR_GAIN_P);
		rightMotorRear.setI(MOTOR_GAIN_I);
		rightMotorRear.setD(MOTOR_GAIN_D);

		driveTrain.setInvertedMotor(MotorType.kFrontLeft, true);
		driveTrain.setInvertedMotor(MotorType.kFrontRight, true);
		driveTrain.setInvertedMotor(MotorType.kRearLeft, true);
		driveTrain.setInvertedMotor(MotorType.kRearRight, true);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoystickDrive());
	}

	public void setVoltageRampRate(double rampRate) {
		leftMotorFront.setVoltageRampRate(rampRate);
		rightMotorFront.setVoltageRampRate(rampRate);
		leftMotorRear.setVoltageRampRate(rampRate);
		rightMotorRear.setVoltageRampRate(rampRate);
	}

	/**
	 * Sets the {@link PIDController} for this {@link DriveTrain}.
	 * 
	 * @param pidController
	 *            A {@link PIDController}.
	 */
	public void setPIDController(PIDController pidController) {
		stop();
		controller = pidController;
		controller.enable();
	}

	/**
	 * Sets the {@link PIDController} for this {@link DriveTrain}.
	 * 
	 * @param driveTrainPIDController
	 *            A value from the {@code DriveTrainPIDController} {@code enum}
	 *            representing the desired {@link PIDController}.
	 */
	public void setPIDController(DriveTrainPIDController driveTrainPIDController) {
		setPIDController(driveTrainPIDController.getPIDControllerForDriveTrain(this));
	}

	/**
	 * Disables the {@link PIDController} for this {@link DriveTrain}.
	 */
	public void disablePIDController() {
		setPIDController(DriveTrainPIDController.DISABLED);
	}

	/**
	 * Gets the {@link PIDController} that this {@link DriveTrain} is currently
	 * using.
	 * 
	 * @return The {@link PIDController} that this {@link DriveTrain} is
	 *         currently using.
	 */
	public PIDController getPIDController() {
		return controller;
	}

	/**
	 * Sets the setpoint for the PIDController. Equivalent to
	 * getPIDController().setSetpoint(setpoint).
	 * 
	 * @param setpoint
	 *            The desired setpoint.
	 */
	public void setSetpoint(double setpoint) {
		getPIDController().setSetpoint(setpoint);
	}

	/**
	 * Return {@code true} if the error is within the specified tolerance.
	 * Equivalent to getPIDController().onTarget().
	 * 
	 * @return {@code true} if the error is less than the tolerance.
	 */
	public boolean onTarget() {
		return getPIDController().onTarget();
	}

	/**
	 * Drive to a specified distance.
	 * 
	 * @param distance
	 *            The distance, according to the encoders, to which the robot
	 *            should drive.
	 * @see {@link driveToRelative} to drive to a distance relative to the
	 *      current distance.
	 */
	public void driveTo(double distance) {
		setPIDController(DriveTrainPIDController.DISTANCE);
		setSetpoint(distance);
	}

	/**
	 * Drive to a specified distance relative to the current distance.
	 * 
	 * @param distance
	 *            The distance relative to the current distance, according to
	 *            the encoders, to which the robot should drive.
	 * @see {@link driveTo} to drive to an absolute distance.
	 */
	public void driveToRelative(double distance) {
		setPIDController(DriveTrainPIDController.DISTANCE);
		setSetpoint(distanceTraveled() + distance);
	}

	/**
	 * Stops drivetrain motors, disables drivetrain PID, and disables drivetrain
	 * ramping.
	 */
	public void stop() {
		controller.disable();
		setVoltageRampRate(0.0);
		driveTrain.stopMotor();
	}

	public RobotDrive getRobotDrive() {
		return driveTrain;
	}

	public CANTalon getLeftMotorFront() {
		return leftMotorFront;
	}

	public CANTalon getLeftMotorRear() {
		return leftMotorRear;
	}

	public CANTalon getRightMotorFront() {
		return rightMotorFront;
	}

	public CANTalon getRightMotorRear() {
		return rightMotorRear;
	}

	public int getLeftEncoderPosition() {
		return leftMotorFront.getEncPosition();
	}

	public int getRightEncoderPosition() {
		return rightMotorFront.getEncPosition();
	}

	public void resetEncoders() {
		leftMotorFront.setEncPosition(0);
		rightMotorFront.setEncPosition(0);
	}

	public int distanceTraveled() {
		return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
	}

	/**
	 * Turns the robot in place.
	 * 
	 * @param power
	 *            The power, between -1.0 and 1.0. Negative values represent
	 *            turning left, and positive values represent turning right.
	 */
	public void turn(double power) {
		driveTrain.setLeftRightMotorOutputs(-power, power);
	}

	/**
	 * Drives the robot forward or backward at the specified power and
	 * curvature.
	 * 
	 * <p>
	 * <em>This method accounts for the inverted motors on our drivetrain.</em>
	 * </p>
	 * 
	 * @param power
	 *            The power, between -1.0 and 1.0. Negative values represent
	 *            moving backward, and positive values represent moving forward.
	 * @param curve
	 *            The curvature, between -1.0 and 1.0. Negative values represent
	 *            turning left, and positive values represent turning right.
	 */
	public void drive(double power, double curve) {
		// TODO: test to figure out if curve needs to be negated
		driveTrain.drive(-power, curve);
	}

	/**
	 * Drives the robot forward or backward at the specified power.
	 * 
	 * <p>
	 * <em>This method accounts for the inverted motors on our drivetrain.</em>
	 * </p>
	 * 
	 * @param power
	 *            The power, between -1.0 and 1.0. Negative values represent
	 *            moving backward, and positive values represent moving forward.
	 */
	public void drive(double power) {
		drive(power, 0.0);
	}

	/**
	 * Gets the {@link AnalogInput} that represents the laser range finder.
	 * 
	 * @return The {@link AnalogInput} associated with the laser range finder.
	 */
	public AnalogInput getRangeFinder() {
		return laserRangeFinder;
	}

	public double getRangeFinderDistance() throws RangeFinderNoSignalException {
		double rangeFinderVoltage = laserRangeFinder.getVoltage();
		if (rangeFinderVoltage < RANGEFINDER_NO_SIGNAL_THRESHOLD) {
			throw new RangeFinderNoSignalException();
		}
		return rangeFinderVoltage * RANGEFINDER_VOLTS_TO_DISTANCE_FACTOR;
	}
}
