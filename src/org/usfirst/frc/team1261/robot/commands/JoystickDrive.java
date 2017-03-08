package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDrive extends Command {

	public static final Joystick JOYSTICK = OI.getDriverJoystick();
	public static final int THROTTLE = OI.AXIS_LEFT_STICK_Y;
	public static final int ROTATE = OI.AXIS_RIGHT_STICK_X;
	public static final int ANTI_TURBO_BUTTON = OI.BUTTON_LEFT_BUMPER;
	public static final boolean SQUARED_INPUTS = true;
	public static final double ANTI_TURBO_FACTOR = 0.5;

	public static final double CORRECTED_ANTI_TURBO_FACTOR;

	static {
		if (SQUARED_INPUTS) {
			CORRECTED_ANTI_TURBO_FACTOR = Math.sqrt(ANTI_TURBO_FACTOR);
		} else {
			CORRECTED_ANTI_TURBO_FACTOR = ANTI_TURBO_FACTOR;
		}
	}

	public JoystickDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double throttle = JOYSTICK.getRawAxis(THROTTLE);
		double rotate = JOYSTICK.getRawAxis(ROTATE);

		if (JOYSTICK.getRawButton(ANTI_TURBO_BUTTON)) {
			throttle *= CORRECTED_ANTI_TURBO_FACTOR;
			rotate *= CORRECTED_ANTI_TURBO_FACTOR;
		}

		Robot.driveTrain.getRobotDrive().arcadeDrive(throttle, rotate, SQUARED_INPUTS);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
