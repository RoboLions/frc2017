package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

/**
 *
 */
public class JoystickTurret extends Command {

	public static final Joystick JOYSTICK = OI.getManipulatorJoystick();
	public static final int JOYSTICK_ROTATION_AXIS = OI.AXIS_RIGHT_STICK_X;

	public static final double JOYSTICK_AXIS_DEADBAND = 0.1;

	public static final double ROTATION_POWER_SCALING_FACTOR = -0.1;

	public JoystickTurret() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.turret);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.turret.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double talonSpeed = OI.getManipulatorJoystick().getRawAxis(OI.AXIS_RIGHT_STICK_X);
		if (Math.abs(talonSpeed) < JOYSTICK_AXIS_DEADBAND)
			talonSpeed = 0.0;
		Robot.turret.setTurretPower(talonSpeed * ROTATION_POWER_SCALING_FACTOR);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.turret.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
