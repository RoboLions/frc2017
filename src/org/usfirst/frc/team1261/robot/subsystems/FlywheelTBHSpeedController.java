package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.Notifier;

public class FlywheelTBHSpeedController {

	public static final double TOLERANCE = 100.0; // rpm
	public static final double APPROX_MAX_SPEED = 5000.0; // rpm
	public static final double PERIOD = 0.010; // seconds
	public static final double TBH_GAIN = 0.00001; // integrated every period

	private final Notifier notifier;
	private final Flywheel flywheel;
	private boolean running;

	private double setpoint;
	private boolean previousErrorPositive;
	private double motorPower;
	private double tbhFromMotorPower;

	private class TBHRunnable implements Runnable {

		@Override
		public void run() {
			double error = getError();
			motorPower += TBH_GAIN * error;

			// Clamp motorPower to [0.0, 1.0] when moving in the positive
			// direction or [-1.0, 0.0] when moving in the negative direction
			if (motorPower * Math.signum(setpoint) > 1.0) {
				motorPower = Math.signum(setpoint);
			} else if (motorPower * Math.signum(setpoint) < 0.0) {
				motorPower = 0.0;
			}

			// If the sign of the error has changed...
			if ((error > 0.0) != previousErrorPositive) {
				// Take back half of the difference in motorPower between the
				// last sign change and now.
				motorPower = (motorPower + tbhFromMotorPower) / 2.0;
				tbhFromMotorPower = motorPower;
				previousErrorPositive = (error > 0);
			}

			flywheel.getFlywheelMotor().set(motorPower);
		}
	}

	public FlywheelTBHSpeedController(Flywheel flywheel) {
		this.flywheel = flywheel;
		notifier = new Notifier(new TBHRunnable());
	}

	/**
	 * Starts the controller if it is not already running.
	 */
	public void start() {
		if (!running) {
			notifier.startPeriodic(PERIOD);
			running = true;
		}
	}

	/**
	 * Gets whether or not this controller is running.
	 * 
	 * @return {@code true} if this controller is running.
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets the target velocity of the flywheel for this controller to attempt
	 * to reach.
	 * 
	 * @param setpoint
	 *            The velocity in rpm.
	 */
	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
		motorPower = Math.signum(setpoint);
		tbhFromMotorPower = 0.0;
		previousErrorPositive = getError() > 0;
	}

	/**
	 * Gets the target velocity of the flywheel that this controller is
	 * attempting to reach.
	 * 
	 * @param setpoint
	 *            The velocity in rpm.
	 */
	public double getSetpoint() {
		return setpoint;
	}

	/**
	 * Gets the difference between the setpoint and the current velocity of the
	 * flywheel.
	 * 
	 * @return The error in rpm.
	 */
	public double getError() {
		return setpoint - flywheel.getFlywheelMotor().getSpeed();
	}

	/**
	 * Gets whether or not the error is within the tolerance.
	 * 
	 * @return {@link true} if the value returned by {@link #getError()} is less
	 *         than {@link #TOLERANCE}.
	 */
	public boolean onTarget() {
		return Math.abs(getError()) <= TOLERANCE;
	}

	/**
	 * Stops the controller if it is running.
	 */
	public void stop() {
		if (running) {
			notifier.stop();
			running = false;
		}
	}
}
