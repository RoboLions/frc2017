package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter.NoContoursFoundException;
/**
 *
 */
public class TurretAlignAndShoot extends CommandGroup {

    public TurretAlignAndShoot() {
    	System.out.println("go");
    	try {
    		System.out.println("shoot");
        	addParallel(new AlignTurret());
        	double angle = JetsonCommunicationAdapter.getBoilerAngleTarget();
			addParallel(new ServoGoTo(angle));
			double velocity = JetsonCommunicationAdapter.getVelocityTarget();
			addParallel(new FlywheelSetSpeed(velocity*39.37*60/12.57));
			
		} catch (NoContoursFoundException e) {
			System.out.println("No contours found");
			e.printStackTrace();
		}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
