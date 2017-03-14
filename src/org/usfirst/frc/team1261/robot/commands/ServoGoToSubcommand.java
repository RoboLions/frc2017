package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ServoGoToSubcommand extends InstantCommand {

	private double position;
	private boolean useSmartDashboard = false;

    public ServoGoToSubcommand(double position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.turret);
    	this.position = position;
    }

    public ServoGoToSubcommand() {
    	useSmartDashboard = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (useSmartDashboard) {
    		position = SmartDashboard.getNumber("Servo target: ", 0.0);
    		Robot.turret.setServoAngle(position);
    	} else {
    		Robot.turret.setServoPosition(position);
    	}
    }
}
