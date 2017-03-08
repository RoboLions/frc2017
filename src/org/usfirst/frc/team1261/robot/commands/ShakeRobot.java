package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShakeRobot extends Command {

	public static final int ENCODER_CHANGE_RELATIVE = 50;
	public static final double POWER = 0.7;

	private int limitRight = 0;
	private int limitLeft = 0;
	private boolean shakeLeft = true;

	public ShakeRobot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.stop();

		int encValue = Robot.driveTrain.getRightEncoderPosition();
		limitLeft = encValue - ENCODER_CHANGE_RELATIVE;
		limitRight = encValue + ENCODER_CHANGE_RELATIVE;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int encValue = Robot.driveTrain.getRightEncoderPosition();

		if (shakeLeft) {
			Robot.driveTrain.getRobotDrive().setLeftRightMotorOutputs(-POWER, POWER);
			shakeLeft = (encValue < limitRight);
		} else {
			Robot.driveTrain.getRobotDrive().setLeftRightMotorOutputs(POWER, -POWER);
			shakeLeft = (encValue < limitLeft);
		}
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
