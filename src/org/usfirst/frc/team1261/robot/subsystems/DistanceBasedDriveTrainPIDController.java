package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * A distance-based {@link DriveTrain} {@link PIDController}.
 */
class DistanceBasedDriveTrainPIDController extends PIDController {

	public static final double kP = 0.001;
	public static final double kI = 0.00002;
	public static final double kD = 0.0;
	public static final double DEFAULT_TOLERANCE = 10.0;

	public static final double STRAIGHTENING_kP = 0.0055;

	public DistanceBasedDriveTrainPIDController(DriveTrain driveTrain) {
		super(kP, kI, kD, new DisplacementPIDSource() {
			@Override
			public double pidGet() {
				return driveTrain.distanceTraveled();
			}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				double curvature = STRAIGHTENING_kP
						* (driveTrain.getLeftEncoderPosition() - driveTrain.getRightEncoderPosition());
				driveTrain.getRobotDrive().drive(-output, curvature);
			}
		});
		setAbsoluteTolerance(DEFAULT_TOLERANCE);
	}
}
