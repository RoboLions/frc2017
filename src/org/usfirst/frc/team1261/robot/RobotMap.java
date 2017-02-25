package org.usfirst.frc.team1261.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static CANTalon leftDriveMotorFront = new CANTalon(15);
	public static CANTalon leftDriveMotorRear = new CANTalon(14);
	public static CANTalon rightDriveMotorFront = new CANTalon(0);
	public static CANTalon rightDriveMotorRear = new CANTalon(1);
	public static Encoder leftDriveEncoder = new Encoder(15, 14);
	public static Encoder rightDriveEncoder = new Encoder(0, 1);
	public static CANTalon intakeMotor = new CANTalon(4);
	public static CANTalon feederMotor = new CANTalon(5);
	public static CANTalon climbMotor = new CANTalon(13);
	public static CANTalon topFlywheelMotor = new CANTalon(39);
	public static CANTalon bottomFlywheelMotor = new CANTalon(40);
	public static CANTalon turretRotationMotor = new CANTalon(41);
	public static Servo turretElevationServo = new Servo(7);
	public static RobotDrive robotDrive = new RobotDrive(leftDriveMotorFront, leftDriveMotorRear, rightDriveMotorFront,
			leftDriveMotorRear);

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
