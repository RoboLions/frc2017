package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakeToggle extends InstantCommand {

	// TODO: Test this on a working robot.

	public static final double DEFAULT_SPEED = 1.0;

	// Called just before this Command runs the first time
	protected void initialize() {
		double intakeSpeed = 0.0;

		switch (Robot.intake.getMotorState()) {
		case FORWARD:
			intakeSpeed = -DEFAULT_SPEED;
			break;
		case BACKWARD:
			intakeSpeed = 0.0;
			break;
		case STOP:
			intakeSpeed = DEFAULT_SPEED;
			break;
		}

		Robot.intake.setIntakePower(intakeSpeed);
	}
}
