
package org.usfirst.frc.team1261.robot;

import org.usfirst.frc.team1261.robot.commands.GearAuto;
import org.usfirst.frc.team1261.robot.commands.ServoGoTo;
import org.usfirst.frc.team1261.robot.commands.ServoGoToSubcommand;
import org.usfirst.frc.team1261.robot.subsystems.Climber;
import org.usfirst.frc.team1261.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1261.robot.subsystems.Feeder;
import org.usfirst.frc.team1261.robot.subsystems.Flywheel;
import org.usfirst.frc.team1261.robot.subsystems.Intake;
import org.usfirst.frc.team1261.robot.subsystems.JetsonCommunicationAdapter;
import org.usfirst.frc.team1261.robot.subsystems.Turret;
import org.usfirst.frc.team1261.robot.subsystems.TurretLED;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Flywheel flywheel = new Flywheel();
	public static final Climber climber = new Climber();
	public static final Feeder feeder = new Feeder();
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Intake intake = new Intake();
	public static final Turret turret = new Turret();
	public static final TurretLED turretLED = new TurretLED();

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Boolean> autoTurnChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putNumber("Auto Delay", 0.0);
		SmartDashboard.putNumber("Servo target: ", 0.0);
		SmartDashboard.putNumber("Flywheel target speed: ", 4100.0);

		autoTurnChooser.addDefault("Turn left (starting on right or center)", false);
		autoTurnChooser.addObject("Turn right (starting on left or center)", true);

		SmartDashboard.putData("Starting Position", autoTurnChooser);

		SmartDashboard.putData("Move to Center",
				new ServoGoTo((Turret.MAX_SERVO_POSITION + Turret.MIN_SERVO_POSITION) / 2));
		SmartDashboard.putData("Move to Lower", new ServoGoToSubcommand(Turret.MAX_SERVO_POSITION));
		SmartDashboard.putData("Move to Upper", new ServoGoToSubcommand(Turret.MIN_SERVO_POSITION));
		SmartDashboard.putData("Servo go to", new ServoGoTo());

		//SmartDashboard.putBoolean("Run gear auto", true);

		SmartDashboard.putData(Scheduler.getInstance());

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// Find what gear post we're going for, then run that auto

		autonomousCommand = new GearAuto(autoTurnChooser.getSelected());

		// schedule the autonomous command
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Flywheel speed: ", flywheel.getFlywheelMotor().getSpeed());
		SmartDashboard.putNumber("Servo angle: ", turret.getServoAngle());
		SmartDashboard.putNumber("Turret angle: ", turret.getTurretAngle());
		SmartDashboard.putNumber("Left Drive Encoder: ", driveTrain.getLeftEncoderPosition());
		SmartDashboard.putNumber("Right Drive Encoder: ", driveTrain.getRightEncoderPosition());
		SmartDashboard.putBoolean("Jetson online: ", JetsonCommunicationAdapter.isOnline());
		SmartDashboard.putBoolean("Can see gear: ", JetsonCommunicationAdapter.isGearFound());
	}
}
