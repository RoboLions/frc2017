package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.IntakeRoll;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	CANTalon intakeMotor = RobotMap.intakeMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeRoll());
    }
    
    /**
     * Sets the intake motor power.
     * 
     * @param power The intake motor power, between -1.0 to 1.0.
     */
    public void setIntakePower(double power) {
    	intakeMotor.set(power);
    }
    
    public void stop() {
    	setIntakePower(0.0);
    }
    
    public CANTalon getIntakeMotor() {
    	return intakeMotor;
    }
}

