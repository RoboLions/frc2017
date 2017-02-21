package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlywheelOn extends Command {

	public static final double MINIMUM_SPEED = 600.0;

	public FlywheelOn() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.flywheel);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.flywheel.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.flywheel.setFlywheelSpeed(MINIMUM_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.flywheel.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
