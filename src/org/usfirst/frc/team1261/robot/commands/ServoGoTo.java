package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter;
import org.usfirst.frc.team1261.robot.subsystems.Turret;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter.NoContoursFoundException;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ServoGoTo extends CommandGroup {

    public ServoGoTo(double position) {
        requires(Robot.turret);
        addSequential(new ServoGoToSubcommand(Turret.MAX_SERVO_ANGLE)); // go to lower limit
        addSequential(new WaitCommand(0.25));
        addSequential(new ServoGoToSubcommand(position));
    }
    
    public ServoGoTo(boolean commandGroupsAreAnnoying) {
        requires(Robot.turret);
        double position;
        
        try{
        	position = JetsonCommunicationAdapter.getBoilerAngleTarget();
        }
        catch (NoContoursFoundException e) {
			System.out.println("No contours found");
			e.printStackTrace();
			position = Turret.MAX_SERVO_ANGLE;
		}
        
        addSequential(new ServoGoToSubcommand(Turret.MAX_SERVO_ANGLE)); // go to lower limit
        addSequential(new WaitCommand(0.25));
        addSequential(new ServoGoToSubcommand(position));
    }

    public ServoGoTo() {
        requires(Robot.turret);
        addSequential(new ServoGoToSubcommand(Turret.MAX_SERVO_ANGLE)); // go to lower limit
        addSequential(new WaitCommand(0.25));
        addSequential(new ServoGoToSubcommand());
    }
}
