package org.usfirst.frc.team1261.robot;

import org.usfirst.frc.team1261.robot.commands.Climb;
import org.usfirst.frc.team1261.robot.commands.ClimbReverse;
import org.usfirst.frc.team1261.robot.commands.FlywheelOn;
import org.usfirst.frc.team1261.robot.commands.HopperAgitatorClockwise;
import org.usfirst.frc.team1261.robot.commands.HopperAgitatorCounterclockwise;
import org.usfirst.frc.team1261.robot.commands.IntakeRollIn;
import org.usfirst.frc.team1261.robot.commands.IntakeRollOut;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final int DRIVER_JOYSTICK = 0;
	public static final int MANIPULATOR_JOYSTICK = 1;

	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_LEFT_BUMPER = 5;
	public static final int BUTTON_RIGHT_BUMPER = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int BUTTON_LEFT_STICK = 9;
	public static final int BUTTON_RIGHT_STICK = 10;

	public static final int AXIS_LEFT_X = 0;
	public static final int AXIS_LEFT_Y = 1;
	public static final int AXIS_LEFT_TRIGGER = 2;
	public static final int AXIS_RIGHT_TRIGGER = 3;
	public static final int AXIS_RIGHT_X = 4;
	public static final int AXIS_RIGHT_Y = 5;

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	static Joystick driverJoystick = new Joystick(DRIVER_JOYSTICK);
	static Joystick manipulatorJoystick = new Joystick(MANIPULATOR_JOYSTICK);

	Button intakeInButton = new JoystickButton(manipulatorJoystick, BUTTON_A);
	Button intakeOutButton = new JoystickButton(manipulatorJoystick, BUTTON_Y);
	Button agitatorCWButton = new JoystickButton(manipulatorJoystick, BUTTON_B);
	Button agitatorCCWButton = new JoystickButton(manipulatorJoystick, BUTTON_X);
	Button climbButton = new JoystickButton(manipulatorJoystick, BUTTON_LEFT_BUMPER);
	Button climbReverseButton = new JoystickButton(manipulatorJoystick, BUTTON_RIGHT_BUMPER);
	Button shooterButton = new JoystickButton(driverJoystick, AXIS_RIGHT_TRIGGER);

	public OI() {
		intakeInButton.toggleWhenPressed(new IntakeRollIn());
		intakeOutButton.toggleWhenPressed(new IntakeRollOut());
		agitatorCWButton.toggleWhenPressed(new HopperAgitatorClockwise());
		agitatorCCWButton.toggleWhenPressed(new HopperAgitatorCounterclockwise());
		shooterButton.toggleWhenPressed(new FlywheelOn());
		climbButton.whileHeld(new Climb());
		climbReverseButton.whileHeld(new ClimbReverse());
	}

	public static Joystick getDriverJoystick() {
		return driverJoystick;
	}

	public static Joystick getManipulatorJoystick() {
		return manipulatorJoystick;
	}
}
