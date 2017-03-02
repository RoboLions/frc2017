package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.JoystickDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
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
	
	CANTalon leftMotorFront = RobotMap.leftDriveMotorFront;
	CANTalon leftMotorRear = RobotMap.leftDriveMotorRear;
	CANTalon rightMotorFront = RobotMap.rightDriveMotorFront;
	CANTalon rightMotorRear = RobotMap.rightDriveMotorRear;
	Encoder leftEncoder = RobotMap.leftDriveEncoder;
	Encoder rightEncoder = RobotMap.rightDriveEncoder;
	RobotDrive driveTrain = RobotMap.robotDrive;
	
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
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoystickDrive());
	}

	public void stop() {
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

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}
	
	public int distanceTraveled() {
		return (Robot.driveTrain.getLeftEncoder().get() + Robot.driveTrain.getRightEncoder().get())/2;
	}
}
