package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickIntake extends Command {

	public static final double DEFAULT_POWER = -1.0;
	public static final double INVERTED_POWER = -DEFAULT_POWER;

	public JoystickIntake() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.intake.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.intakeInvertButton.get()) {
			Robot.intake.setIntakePower(INVERTED_POWER);
		} else {
			Robot.intake.setIntakePower(DEFAULT_POWER);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}