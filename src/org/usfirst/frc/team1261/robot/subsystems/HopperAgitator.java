package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.HopperAgitatorClockwise;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperAgitator extends Subsystem {

	CANTalon hopperAgitatorMotor = RobotMap.hopperAgitatorMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new HopperAgitatorClockwise());
    }
    
    public void setAgitatorPower(double power) {
        hopperAgitatorMotor.set(power);
    }
    
    public void stop() {
    	setAgitatorPower(0.0);
    }
    
    public CANTalon getHopperAgitatorMotor() {
    	return hopperAgitatorMotor;
    }
}

