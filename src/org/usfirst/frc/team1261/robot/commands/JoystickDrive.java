package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDrive extends Command {
	public static Joystick DRIVER_JOYSTICK = OI.getDriverJoystick();
	public static final int THROTTLE = OI.AXIS_LEFT_Y;
	public static final int ROTATE = OI.AXIS_RIGHT_X;
	public static final boolean SQUARED_INPUTS = true;
	
    public JoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double throttle = DRIVER_JOYSTICK.getRawAxis(THROTTLE);
    	double rotate = DRIVER_JOYSTICK.getRawAxis(ROTATE);
    	Robot.driveTrain.getRobotDrive().arcadeDrive(throttle,rotate,SQUARED_INPUTS);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
