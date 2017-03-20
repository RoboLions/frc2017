package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlywheelSetSpeed extends Command {

	public static final double MINIMUM_SPEED = 4100.0;
	public double speed;
	
	public FlywheelSetSpeed(double speed) {
		this.speed = speed;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.flywheel);	
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.flywheel.stop();
		Robot.flywheel.setFlywheelSpeed(speed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if (timeSinceInitialized() >= 1.0)
//			SmartDashboard.putNumber("Error", Robot.flywheel.getFlywheelMotor().getClosedLoopError());
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
