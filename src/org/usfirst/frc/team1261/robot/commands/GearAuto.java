package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearAuto extends CommandGroup {

	// TODO: The distance from the airship wall where we want to end.
	public static final double RANGEFINDER_DISTANCE_THRESHOLD = 0.0;
	// TODO: The distance we want to drive in the first part.
	public static final double INITIAL_DRIVE_DISTANCE = 0.0;

	/**
	 * The super awesome and advanced gear autonomous program.
	 * 
	 * @param turnRight
	 *            Turns left if {@code false}, right if {@code true}.
	 */
	public GearAuto(boolean turnRight) {
		requires(Robot.driveTrain);

		addSequential(new DriveDistance(INITIAL_DRIVE_DISTANCE));
		addSequential(new TurnUntilGearSeen(turnRight));
		addSequential(new DriveTowardsGearUntilRangefinderDistance(RANGEFINDER_DISTANCE_THRESHOLD));
	}
}
