package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.DriveTrain.DriveTrainPIDController;
import org.usfirst.frc.team1261.robot.subsystems.DriveTrain.RangeFinderNoSignalException;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTowardsGearUntilRangefinderDistance extends Command {

	// skip align state if target lost for this many seconds
	public static final double ALIGN_STATE_LOST_THRESH = 1.0;
	// continue to drive state if we are aligned for this many seconds
	public static final double ALIGN_STATE_ALIGNED_THRESH = 0.5;

	// how long should we drive for? (seconds)
	public static final double DRIVE_STATE_DURATION = 0.75; 
	// power for driving
	public static final double DRIVE_STATE_POWER = 0.25; // positive is forwards

	private final double rangeFinderDistance;

	private State currentState = State.ALIGN_STATE;

	// state tracking variables
	private double stateStartTime = 0.0;
	private boolean targetVisible = false;
	private double targetNotVisibleSince;
	private boolean aligned = false;
	private double alignedSince;

	private static enum State {
		ALIGN_STATE, DRIVE_STATE, DRIVE_FOREVER_STATE
	}

    public DriveTowardsGearUntilRangefinderDistance(double rangeFinderDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);

    	this.rangeFinderDistance = rangeFinderDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	switchState(State.ALIGN_STATE);
    }

    private void switchState(State state) {
    	currentState = state;
    	stateStartTime = timeSinceInitialized();
    	aligned = false;
		targetVisible = false;    	
   
    	Robot.driveTrain.stop();
    	switch (state) {
    	case ALIGN_STATE:
    		Robot.driveTrain.setPIDController(DriveTrainPIDController.VISION_TRACK);
    		break;
    	case DRIVE_STATE:
    	case DRIVE_FOREVER_STATE:
    		System.out.println("drive");
    		Robot.driveTrain.drive(DRIVE_STATE_POWER);
    		break;
    	default:
    		throw new IllegalArgumentException("Unknown state in switchState");
    	}
     }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (currentState) {
    	case ALIGN_STATE:
    		boolean onTarget = Robot.driveTrain.onTarget();
    		System.out.println(onTarget);
    		if (onTarget) {
    			// we are now aligned. have we always been aligned?
    			// check by checking the value of "aligned"
    			if (!aligned) {
    				// just got aligned
    				aligned = true;
    				alignedSince = timeSinceInitialized();
    			} else if (timeSinceInitialized() >= alignedSince + ALIGN_STATE_ALIGNED_THRESH) {
    				// we've been aligned for long enough
    				switchState(State.DRIVE_STATE);
    			}
    		} else if (!onTarget && aligned) {
    			// no longer aligned (just got unaligned)
    			aligned = false;
    		} else {
    			// we haven't been aligned. is it because we can't see the target?
    			if (!JetsonCommunicationAdapter.isGearFound()) {
    				// if so, check how long we've been unable to see the target
    				if (targetVisible) {
    					// we've just lost the target
    					targetVisible = false;
    					targetNotVisibleSince = timeSinceInitialized();
    				} else if (timeSinceInitialized() >= targetNotVisibleSince + ALIGN_STATE_LOST_THRESH) {
    					// we're been waiting for the target long enough
    					switchState(State.DRIVE_FOREVER_STATE);
    				}
    			}
    		}
    		break;
    	case DRIVE_STATE:
    		if (timeSinceInitialized() >= stateStartTime + DRIVE_STATE_DURATION) {
    			// we've been driving for long enough
    			switchState(State.ALIGN_STATE);
    		} else {
    			// continue driving
    			Robot.driveTrain.drive(DRIVE_STATE_POWER);
    		}
    		break;
    	case DRIVE_FOREVER_STATE:
    		Robot.driveTrain.drive(DRIVE_STATE_POWER);
    		break;
    	default:
    		throw new IllegalStateException("Unknown state in execute");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        try {
			return (Robot.driveTrain.getRangeFinderDistance() <= rangeFinderDistance);
		} catch (RangeFinderNoSignalException e) {
			return false;
		}
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
