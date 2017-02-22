package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoMoveForward extends Command {

	public static final double POWER = 0.75; 
	public static final int ENCODER_TICKS_PER_REVOLUTION = 1077;
	public static final double WHEEL_CIRCUMFERENCE = 0.5; //in feet
	public static final double DISTANCE = 6.0; // also in feet
	public static final double REVOLUTIONS = DISTANCE/WHEEL_CIRCUMFERENCE;

	public AutoMoveForward() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.stop();
		Robot.driveTrain.getLeftEncoder().reset();
		Robot.driveTrain.getRightEncoder().reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.getRobotDrive().drive(POWER, 0.0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.driveTrain.getEncoderAverage() >= (ENCODER_TICKS_PER_REVOLUTION * REVOLUTIONS);
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
