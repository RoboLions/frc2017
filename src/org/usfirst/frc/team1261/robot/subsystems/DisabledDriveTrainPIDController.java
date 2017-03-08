package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * A {@link PIDController} for the {@link DriveTrain} that does nothing.
 */
public class DisabledDriveTrainPIDController extends PIDController {

	public static final double kP = 0.0;
	public static final double kI = 0.0;
	public static final double kD = 0.0;
	public static final double DEFAULT_TOLERANCE = 0.0;

	/**
	 * Error value used for PID because this PID controller has no effect.
	 */
	public static final double DEFAULT_ERROR = 0.0;

	public DisabledDriveTrainPIDController(DriveTrain driveTrain) {
		super(kP, kI, kD, new DisplacementPIDSource() {
			@Override
			public double pidGet() {
				return DEFAULT_ERROR;
			}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
			}
		});
	}

	/**
	 * Return {@code false} since a disabled PID controller can never be on
	 * target.
	 * 
	 * @return {@code false}.
	 */
	public boolean onTarget() {
		return false;
	}
}