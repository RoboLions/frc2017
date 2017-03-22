package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A vision-tracking-based {@link DriveTrain} {@link PIDController}.
 */
class VisionTrackingBasedDriveTrainPIDController extends PIDController {

	// TODO: figure out these PID constants
	public static final double kP = 0.0018;
	public static final double kI = 0.00026;
	public static final double kD = 0.01;
	public static final double DEFAULT_TOLERANCE = JetsonCommunicationAdapter.GEAR_X_AXIS_TOLERANCE;

	// set to 0.0 to turn in place
	public static final double POWER = 0.0;

	/**
	 * Error value used for PID when no target can be found.
	 */
	public static final double DEFAULT_ERROR = 0.0;

	public VisionTrackingBasedDriveTrainPIDController(DriveTrain driveTrain) {
		super(kP, kI, kD, new DisplacementPIDSource() {
			@Override
			public double pidGet() {
				try {
					SmartDashboard.putNumber("gear-x-error", JetsonCommunicationAdapter.getGearXOffset());
					// TODO: account for camera offset
					return JetsonCommunicationAdapter.getGearXOffset();
				} catch (JetsonCommunicationAdapter.NoContoursFoundException e) {
					return DEFAULT_ERROR;
				}
			}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				if (!JetsonCommunicationAdapter.isGearFound() || driveTrain.onTarget()) {
					output = 0.0;
				}
				SmartDashboard.putNumber("Drivetrain turn power", output);
				driveOrTurn(POWER, output);
			}

			private void driveOrTurn(double power, double curvature) {
				if (power != 0.0) {
					driveTrain.drive(power, -curvature);
				} else {
					driveTrain.turn(-curvature);
				}
			}
		});
		setAbsoluteTolerance(DEFAULT_TOLERANCE);
	}

	/**
	 * Return {@code true} if the error is within the tolerance and the Jetson
	 * can see a gear.
	 * 
	 * @return {@code true} if the error is less than the tolerance.
	 */
	public boolean onTarget() {
		return (JetsonCommunicationAdapter.isGearFound() && super.onTarget());
	}
}