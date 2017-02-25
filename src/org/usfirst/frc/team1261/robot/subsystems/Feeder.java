package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {

	CANTalon feederMotor = RobotMap.feederMotor;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setFeederPower(double power) {
		feederMotor.set(power);
	}

	public void stop() {
		setFeederPower(0.0);
	}

	public CANTalon getFeederMotor() {
		return feederMotor;
	}
}
