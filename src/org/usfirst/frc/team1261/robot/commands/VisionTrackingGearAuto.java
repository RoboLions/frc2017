package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionTrackingGearAuto extends CommandGroup {

	public static final double INITIAL_DRIVE_POWER = 0.75;
	public static final double INITIAL_DRIVE_DURATION = 1.2;

    public VisionTrackingGearAuto(boolean turnRight) {
    	requires(Robot.driveTrain);

    	addSequential(new DriveForPowerAndDuration(INITIAL_DRIVE_POWER, INITIAL_DRIVE_DURATION));
        addSequential(new TurnUntilGearSeen(turnRight));
        addSequential(new DriveToGear());
    }
}
