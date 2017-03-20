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
    }
}