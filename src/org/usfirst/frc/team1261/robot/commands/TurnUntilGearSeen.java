package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnUntilGearSeen extends Command {

	public static final double MINIMUM_DURATION_GEAR_VISIBLE = 0.25;
	public static final double POWER = 0.5;

	private final boolean turnRight;
	private boolean gearWasVisible = false;
	private double gearVisibleSince;

    public TurnUntilGearSeen(boolean turnRight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.turnRight = turnRight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearWasVisible = false;
    	Robot.driveTrain.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (JetsonCommunicationAdapter.isOnline()) {
    		Robot.driveTrain.turn((turnRight ? 1.0 : -1.0) * POWER);
    	} else {
    		Robot.driveTrain.turn(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean gearCurrentlyVisible = JetsonCommunicationAdapter.isGearFound();
    	if (!gearWasVisible && gearCurrentlyVisible) {
    		gearWasVisible = true;
    		gearVisibleSince = Timer.getFPGATimestamp();
    	} else if (gearWasVisible && !gearCurrentlyVisible) {
    		gearWasVisible = false;
    	}
        return (gearWasVisible && gearVisibleSince >= MINIMUM_DURATION_GEAR_VISIBLE);
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
