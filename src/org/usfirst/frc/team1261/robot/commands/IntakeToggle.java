package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeToggle extends Command {

	// TODO: Test this on a working robot.

	private double intakeSpeed = 0.0;

	private boolean isStopped = false;
	private boolean isReversed = false;

	public IntakeToggle() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		switch (Robot.intake.getMotorState()) {
		case FORWARD:
			Robot.intake.setIntakePower(-intakeSpeed);
			break;
		case BACKWARD:
			Robot.intake.setIntakePower(0.0);
			break;
		case STOP:
			Robot.intake.setIntakePower(intakeSpeed);
			break;
		}

		/*
		 * if(currentIntakeSpeed > 0.0){ newIntakeSpeed = 0.0; isStopped = true;
		 * isReversed = false; } else if(currentIntakeSpeed == 0.0){
		 * newIntakeSpeed = -1.0; isStopped = false; isReversed = true; } else{
		 * newIntakeSpeed = 1.0; isStopped = false; isReversed = false; }
		 */

		Robot.intake.setIntakePower(intakeSpeed);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putBoolean("Intake Stop", isStopped);
		SmartDashboard.putBoolean("Intake Reverse", isReversed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
