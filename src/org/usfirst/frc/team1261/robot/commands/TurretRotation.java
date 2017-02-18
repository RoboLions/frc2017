package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretRotation extends Command {

	public final static Joystick MANIPULATOR_JOYSTICK = OI.getManipulatorJoystick();
	public final static String SD_TURRET_POWER_KEY = "Turret power: ";

	public TurretRotation() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.turret);
		SmartDashboard.putNumber(SD_TURRET_POWER_KEY, SmartDashboard.getNumber(SD_TURRET_POWER_KEY, 0.0));
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.turret.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.turret.setTurretPower(SmartDashboard.getNumber(SD_TURRET_POWER_KEY, 0.0));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.turret.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
