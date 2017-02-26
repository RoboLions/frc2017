package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShakeRobot extends Command {

	public static final int ENCODER_CHANGE_RELATIVE = 50;
	public int limitRight = 0;
	public int limitLeft = 0;
	public int encValue = 0;
	public boolean shakeRight = true;
	
    public ShakeRobot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    	limitLeft = Robot.driveTrain.getLeftEncoder().get() - ENCODER_CHANGE_RELATIVE;
    	limitRight = Robot.driveTrain.getLeftEncoder().get() + ENCODER_CHANGE_RELATIVE;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	encValue = Robot.driveTrain.getLeftEncoder().get();
    	
    	if(shakeRight){
    		Robot.driveTrain.getRobotDrive().setLeftRightMotorOutputs(.7, -.7);
    		if(encValue > limitRight){
    			shakeRight = false;
    		}
    	} 	
    	else{
    		Robot.driveTrain.getRobotDrive().setLeftRightMotorOutputs(-.7, .7);	
    		if(encValue < limitLeft){
    			shakeRight = true;
    		}
    	}
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
