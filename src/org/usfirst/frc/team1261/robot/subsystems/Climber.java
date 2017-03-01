package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	public CANTalon climbMotor = RobotMap.climbMotor;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setClimbPower(double power) {
		climbMotor.set(Math.abs(power));
	}

	public void stop() {
		setClimbPower(0.0);
	}

	public CANTalon getClimbMotor() {
		return climbMotor;
	}
}
