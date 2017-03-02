
package org.usfirst.frc.team1261.robot;

import org.usfirst.frc.team1261.robot.commands.ServoGoTo;
import org.usfirst.frc.team1261.robot.commands.LeftGearAuto;
import org.usfirst.frc.team1261.robot.commands.MiddleGearAuto;
import org.usfirst.frc.team1261.robot.commands.RightGearAuto;
import org.usfirst.frc.team1261.robot.subsystems.Climber;
import org.usfirst.frc.team1261.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1261.robot.subsystems.Feeder;
import org.usfirst.frc.team1261.robot.subsystems.Flywheel;
import org.usfirst.frc.team1261.robot.subsystems.Intake;
import org.usfirst.frc.team1261.robot.subsystems.Turret;

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

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<String> autoTeamChooser = new SendableChooser<>();
	SendableChooser<String> autoStartChooser = new SendableChooser<>();	
	SendableChooser<String> autoGearChooser = new SendableChooser<>();
	SendableChooser<String> autoBaselineChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putNumber("Auto Delay", 0.0);

		autoTeamChooser.addDefault("Red", "Red");
		autoTeamChooser.addObject("Blue", "Blue");
		
		autoStartChooser.addObject("Left", "Left");
		autoStartChooser.addDefault("Middle", "Middle");
		autoStartChooser.addObject("Right", "Right");
		
		autoGearChooser.addObject("Left", "Left");
		autoGearChooser.addDefault("Middle", "Middle");
		autoGearChooser.addObject("Right", "Right");
		
		autoBaselineChooser.addDefault("Left", "Left");
		autoBaselineChooser.addObject("Right", "Right");
		
		SmartDashboard.putData("Team Color", autoTeamChooser);
		SmartDashboard.putData("Starting Position", autoStartChooser);
		SmartDashboard.putData("Gear Post Position", autoGearChooser);
		SmartDashboard.putData("Baseline Crossing Side", autoBaselineChooser);
		
		SmartDashboard.putData("Move to Center", new ServoGoTo((Turret.MAX_SERVO_POSITION + Turret.MIN_SERVO_POSITION) / 2));
		SmartDashboard.putData("Move to Lower", new ServoGoTo(Turret.MAX_SERVO_POSITION));
		SmartDashboard.putData("Move to Upper", new ServoGoTo(Turret.MIN_SERVO_POSITION));

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
		//Find what gear post we're going for, then run that auto
		if(autoGearChooser.getSelected().equalsIgnoreCase("Left")){
			autonomousCommand = new LeftGearAuto(autoStartChooser.getSelected(), SmartDashboard.getNumber("Auto Delay", 0.0));
		}
		else if(autoGearChooser.getSelected().equalsIgnoreCase("Middle")){
			autonomousCommand = new MiddleGearAuto(autoStartChooser.getSelected(), autoBaselineChooser.getSelected(), SmartDashboard.getNumber("Auto Delay", 0.0));
		}
		else if(autoGearChooser.getSelected().equalsIgnoreCase("Right")){
			autonomousCommand = new RightGearAuto(autoStartChooser.getSelected(), SmartDashboard.getNumber("Auto Delay", 0.0));
		}
		
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
		SmartDashboard.putNumber("Flywheel speed: ", flywheel.getFlywheelMotor().getEncVelocity());
		SmartDashboard.putNumber("Servo Position: ", turret.getServoPosition());
		SmartDashboard.putNumber("Turret Position: ", turret.getTurretAngle());
		SmartDashboard.putNumber("Left Drive Encoder: ", driveTrain.getLeftEncoder().get());
		SmartDashboard.putNumber("Right Drive Encoder: ", driveTrain.getRightEncoder().get());
	}
}
