package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoMoveBackward extends Command {

	public static final double POWER = 0.25;
	public static final int ENCODER_TICKS_PER_REVOLUTION = 1077;
	public static final double WHEEL_CIRCUMFERENCE = 0.5; //in feet
	public static final double DISTANCE = 7.0; // also in feet
	public static final double REVOLUTIONS = DISTANCE/WHEEL_CIRCUMFERENCE;
	public static final double VOLTAGE_RAMP_RATE = DriveTrain.DEFAULT_VOLTAGE_RAMP_RATE;

	public AutoMoveBackward() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		setTimeout(7.1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.stop();
		Robot.driveTrain.setVoltageRampRate(VOLTAGE_RAMP_RATE);
		Robot.driveTrain.resetEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.getRobotDrive().drive(POWER, 0.01);
		SmartDashboard.putNumber("Time", timeSinceInitialized());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
		//return Robot.driveTrain.distanceTraveled() >= (ENCODER_TICKS_PER_REVOLUTION * REVOLUTIONS);
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
