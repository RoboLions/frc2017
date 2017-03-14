package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.TurretLEDOn;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TurretLED extends Subsystem {

    Relay turretLED = RobotMap.turretLED;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TurretLEDOn());
    }

    public void setLEDState(boolean state) {
    	turretLED.set((state) ? Value.kOn : Value.kOff);
    }

    public boolean getLEDState() {
    	return turretLED.get() == Value.kOn;
    }
}

