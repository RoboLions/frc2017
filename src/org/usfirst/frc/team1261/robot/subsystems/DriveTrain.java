package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.JoystickDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	CANTalon leftMotorFront = RobotMap.leftDriveMotorFront;
	CANTalon leftMotorRear = RobotMap.leftDriveMotorRear;
	CANTalon rightMotorFront = RobotMap.rightDriveMotorFront;
	CANTalon rightMotorRear = RobotMap.rightDriveMotorRear;
	Encoder leftEncoder = RobotMap.leftDriveEncoder;
	Encoder rightEncoder = RobotMap.rightDriveEncoder;
	RobotDrive driveTrain = RobotMap.robotDrive;
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
	
	public int getEncoderAverage() {
		return (Robot.driveTrain.getLeftEncoder().get() + Robot.driveTrain.getRightEncoder().get())/2;
	}
}
