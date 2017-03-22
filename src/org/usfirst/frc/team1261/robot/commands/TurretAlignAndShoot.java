package org.usfirst.frc.team1261.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class TurretAlignAndShoot extends CommandGroup {
                 
    public TurretAlignAndShoot() {
    	System.out.println("go");
    	System.out.println("shoot");
        addParallel(new AlignTurret());
		addParallel(new ServoGoTo(true));
		addParallel(new FlywheelSetSpeed());
    }
}
