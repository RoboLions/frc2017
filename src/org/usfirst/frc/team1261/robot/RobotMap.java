package org.usfirst.frc.team1261.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static CANTalon leftDriveMotorFront;
	public static CANTalon leftDriveMotorRear;
	public static CANTalon rightDriveMotorFront;
	public static CANTalon rightDriveMotorRear;
	public static CANTalon intakeMotor;
	public static CANTalon hopperAgitatorMotor;
	public static CANTalon climbMotor;
	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;
	public static RobotDrive driveTrain;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	static {
		leftDriveMotorFront = new CANTalon(5);
		leftDriveMotorRear = new CANTalon(7);
		rightDriveMotorFront = new CANTalon(6);
		rightDriveMotorRear = new CANTalon(8);
		intakeMotor = new CANTalon(9);
		climbMotor = new CANTalon(4);
		hopperAgitatorMotor= new CANTalon(3);
		leftDriveEncoder = new Encoder(5,7);
		rightDriveEncoder = new Encoder(6,8);
		driveTrain = new RobotDrive(leftDriveMotorFront, leftDriveMotorRear, rightDriveMotorFront, leftDriveMotorRear);
		//driveTrain = new RobotDrive(leftDriveMotorFront, rightDriveMotorFront);
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
