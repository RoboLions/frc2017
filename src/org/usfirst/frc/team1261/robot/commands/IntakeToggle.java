package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeToggle extends Command {
	
	//TODO: Test this on a working robot.
	
	double currentIntakeSpeed = 0.0;
	double newIntakeSpeed = 0.0;
	
	boolean isStopped = false;
	boolean isReversed = false;

    public IntakeToggle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	if(currentIntakeSpeed > 0.0){
    		newIntakeSpeed = 0.0;
    		isStopped = true;
    		isReversed = false;
    	}
    	else if(currentIntakeSpeed == 0.0){
    		newIntakeSpeed = -1.0;
    		isStopped = false;
    		isReversed = true;
    	}
    	else{
    		newIntakeSpeed = 1.0;
    		isStopped = false;
    		isReversed = false;
    	}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setIntakePower(newIntakeSpeed);
    	
    	SmartDashboard.putBoolean("Intake Stop", isStopped);
    	SmartDashboard.putBoolean("Intake Reverse", isReversed);
    	currentIntakeSpeed = Robot.intake.getIntakePower();
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