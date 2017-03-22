package org.usfirst.frc.team1261.robot.subsystems;

public class ShooterMath {
	public static final double GRAVITY = 9.81; // m/s^2
	public static final double MASS = 0.074; // kilograms
	public static final double DRAG = 0.45; // sets the range
	public static final double TOWER_HEIGHT = 2.46; // meters
	public static final double ROBOT_HEIGHT = 0.83; // meters

	// sets height of the max trajectory
	public static final double TIME_ELAPSED = 2.0; // seconds

	private static final double DELTA_Y = TOWER_HEIGHT - ROBOT_HEIGHT;
	private static final double NUMERATOR_VELOCITY_Y = (DRAG * DELTA_Y) + (MASS * GRAVITY * TIME_ELAPSED);
	private static final double DENOMINATOR_VELOCITY = MASS * (1 - Math.exp(-DRAG * TIME_ELAPSED / MASS));
	private static final double VELOCITY_Y = (NUMERATOR_VELOCITY_Y / DENOMINATOR_VELOCITY) - (MASS * GRAVITY / DRAG);

	public static class InitialConditions {
		public final double velocity;
		public final double angle;

		private InitialConditions(double velocity, double angle) {
			this.velocity = velocity;
			this.angle = angle;
		}
	}

	public static InitialConditions getInitialConditions(double distance) {
		double velocityX = (DRAG * distance) / DENOMINATOR_VELOCITY;
		double velocity = Math.sqrt((velocityX * velocityX) + (VELOCITY_Y * VELOCITY_Y));
		double angle = Math.toDegrees(Math.atan2(VELOCITY_Y, velocityX));
		return new InitialConditions(velocity, angle);
	}
}
