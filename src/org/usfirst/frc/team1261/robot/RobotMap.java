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
	
	public static final int LEFT_DRIVE_FRONT_PORT = 40;
	public static final int LEFT_DRIVE_BACK_PORT = 41;
	public static final int RIGHT_DRIVE_FRONT_PORT = 42;
	public static final int RIGHT_DRIVE_BACK_PORT = 43;
	public static final int INTAKE_PORT = 44;
	public static final int FEEDER_PORT = 45;
	public static final int CLIMBER_PORT = 46;
	public static final int TOP_FLYWHEEL_PORT = 47;
	public static final int BOT_FLYWHEEL_PORT = 48;
	public static final int TURRET_ROTATION_PORT = 49;
	public static final int AGITATOR_PORT = 50;	
	public static final int TURRET_SERVO_PORT = 7;
	
	public static CANTalon leftDriveMotorFront = new CANTalon(LEFT_DRIVE_FRONT_PORT);
	public static CANTalon leftDriveMotorRear = new CANTalon(LEFT_DRIVE_BACK_PORT);
	public static CANTalon rightDriveMotorFront = new CANTalon(RIGHT_DRIVE_FRONT_PORT);
	public static CANTalon rightDriveMotorRear = new CANTalon(RIGHT_DRIVE_BACK_PORT);
	public static CANTalon intakeMotor = new CANTalon(INTAKE_PORT);
	public static CANTalon feederMotor = new CANTalon(FEEDER_PORT);
	public static CANTalon climbMotor = new CANTalon(CLIMBER_PORT);
	public static CANTalon topFlywheelMotor = new CANTalon(TOP_FLYWHEEL_PORT);
	public static CANTalon bottomFlywheelMotor = new CANTalon(BOT_FLYWHEEL_PORT);
	public static CANTalon turretRotationMotor = new CANTalon(TURRET_ROTATION_PORT);
	
	public static Servo turretElevationServo = new Servo(TURRET_SERVO_PORT	);
	
	public static RobotDrive robotDrive = new RobotDrive(leftDriveMotorFront, leftDriveMotorRear, rightDriveMotorFront,
			rightDriveMotorRear);
	
	public static Encoder leftDriveEncoder = new Encoder(15, 14);
	public static Encoder rightDriveEncoder = new Encoder(0, 1);

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
