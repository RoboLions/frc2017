package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * A vision-tracking-based {@link DriveTrain} {@link PIDController}.
 */
class VisionTrackingBasedDriveTrainPIDController extends PIDController {

	// TODO: figure out these values
	public static final double kP = 0.0018;
	public static final double kI = 0.00026;
	public static final double kD = 0.01;
	public static final double DEFAULT_TOLERANCE = JetsonCommunicationAdapter.GEAR_X_AXIS_TOLERANCE;

	public static final double OUTPUT_THRESHOLD = 0.17;

	/**
	 * Error value used for PID when no target can be found.
	 */
	public static final double DEFAULT_ERROR = 0.0;

	public VisionTrackingBasedDriveTrainPIDController(DriveTrain driveTrain) {
		super(kP, kI, kD, new DisplacementPIDSource() {
			@Override
			public double pidGet() {
				try {
					edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putNumber("gear-x-error", JetsonCommunicationAdapter.getGearXOffset());
					return JetsonCommunicationAdapter.getGearXOffset();
				} catch (JetsonCommunicationAdapter.NoContoursFoundException e) {
					return DEFAULT_ERROR;
				}
			}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				if (Math.abs(output) <= OUTPUT_THRESHOLD) {
					output = Math.signum(output) * OUTPUT_THRESHOLD;
				}
				if (!JetsonCommunicationAdapter.isGearFound() || driveTrain.onTarget()) {
					output = 0.0;
				}
				edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putNumber("Drivetrain turn power", output);
				driveTrain.turn(output);
			}
		});
		setAbsoluteTolerance(DEFAULT_TOLERANCE);
	}

	/**
	 * Return {@code true} if the error is within the tolerance determined by
	 * {@link VisionTrackingBasedDriveTrainPIDController#DEFAULT_TOLERANCE}.<br>
	 * <em>This method overrides {@link PIDSubsystem}'s
	 * {@link PIDSubsystem#onTarget onTarget} method as a workaround for
	 * <a href="https://usfirst.collab.net/sf/tracker/do/viewArtifact/projects.wpilib/tracker.4_defects/artf4812">
	 * a bug in WPILib's implementation</a>.</em>
	 * 
	 * @return {@code true} if the error is less than the tolerance.
	 */
	public boolean onTarget() {
		return (JetsonCommunicationAdapter.isGearFound() && Math.abs(getError()) < DEFAULT_TOLERANCE);
	}
}