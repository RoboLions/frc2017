package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter.NoContoursFoundException;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlywheelSetSpeed extends Command {

	public static final double MINIMUM_SPEED = 4100.0;
	public double speed;
	public boolean isVision = false;
	
	public FlywheelSetSpeed(double speed) {
		this.speed = speed;
		isVision = false;
		requires(Robot.flywheel);	
	}
	
	public FlywheelSetSpeed(){
		isVision = true;
		requires(Robot.flywheel);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.flywheel.stop();
		
		if(isVision){
			try{
				speed = JetsonCommunicationAdapter.getVelocityTarget();
				speed = speed * 39.37 * 60 / 12.57; 
			}
			catch (NoContoursFoundException e) {
				System.out.println("No contours found");
				e.printStackTrace();
				speed = 0;
			}
		}
		
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
