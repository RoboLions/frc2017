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
    	System.out.println("shoot");
        addParallel(new AlignTurret());
		addParallel(new ServoGoTo(true));
		addParallel(new FlywheelSetSpeed());
    }
}
