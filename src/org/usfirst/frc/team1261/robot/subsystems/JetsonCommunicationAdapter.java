package org.usfirst.frc.team1261.robot.subsystems;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class JetsonCommunicationAdapter {

	/**
	 * Indicates that the vision processor was unable to identify any contours
	 * representing goals.
	 */
	public static class NoContoursFoundException extends Exception {
		private static final long serialVersionUID = 8913380034267672587L;
	}

	public static final NetworkTable CONTOUR_TABLE = NetworkTable.getTable("Jetson");

	public static final double TOLERANCE_FACTOR = 0.02;

	public static final double DEFAULT_Y_IMAGE_SIZE = 240;
	public static final double DEFAULT_X_IMAGE_SIZE = 320;
	public static final double Y_IMAGE_SIZE = CONTOUR_TABLE.getNumber("imageSizeY", DEFAULT_Y_IMAGE_SIZE);
	public static final double X_IMAGE_SIZE = CONTOUR_TABLE.getNumber("imageSizeX", DEFAULT_X_IMAGE_SIZE);
	public static final double X_AXIS_TARGET = X_IMAGE_SIZE / 2;
	public static final double X_AXIS_TOLERANCE = X_IMAGE_SIZE * TOLERANCE_FACTOR;

	/**
	 * Value used for x, y, and area of target when it cannot be retrieved.
	 */
	public static final double DEFAULT_VALUE = 0.0;

	public static void setShooterFired(boolean firing) {
		CONTOUR_TABLE.putBoolean("shooterFiring", firing);
	}

	/**
	 * Gets the distance (horizontal) to the boiler.
	 * 
	 * @return The distance to the boiler, in meters.
	 * @throws NoContoursFoundException
	 *             If no contours representing boilers can be identified.
	 */
	public static double getBoilerDistance() throws NoContoursFoundException {
		boolean isBoilerFound = CONTOUR_TABLE.getBoolean("Boiler_Found", false);
		if (!isBoilerFound) {
			throw new NoContoursFoundException();
		} else {
			return CONTOUR_TABLE.getNumber("distance", DEFAULT_VALUE);
		}
	}

	/**
	 * Gets the target angle for the boiler.
	 * 
	 * @return The target angle for the boiler.
	 * @throws NoContoursFoundException
	 *             If no contours representing boilers can be identified.
	 */
	public static double getBoilerAngleTarget() throws NoContoursFoundException {
		boolean isBoilerFound = CONTOUR_TABLE.getBoolean("Boiler_Found", false);
		if (!isBoilerFound) {
			throw new NoContoursFoundException();
		} else {
			return CONTOUR_TABLE.getNumber("theta", DEFAULT_VALUE);
		}
	}

	/**
	 * Gets the x-axis offset of the boiler from where the turret is pointing.
	 * 
	 * @return The x-axis offset of the boiler in pixels.
	 * @throws NoContoursFoundException
	 *             If no contours representing boilers can be identified.
	 */
	public static double getTurretXOffset() throws NoContoursFoundException {
		return X_AXIS_TARGET - getTurretX();
	}

	/**
	 * Gets the target velocity for the fuel.
	 * 
	 * @return The target velocity for the fuel.
	 * @throws NoContoursFoundException
	 *             If no contours representing boilers can be identified.
	 */
	public static double getVelocityTarget() throws NoContoursFoundException {
		boolean isBoilerFound = CONTOUR_TABLE.getBoolean("Boiler_Found", false);
		if (!isBoilerFound) {
			throw new NoContoursFoundException();
		} else {
			return CONTOUR_TABLE.getNumber("velocity", DEFAULT_VALUE);
		}
	}

	/**
	 * Gets the x-axis position of the center of the goal.
	 * 
	 * @return The x-axis position of the center of the goal in pixels.
	 * @throws NoContoursFoundException
	 *             If no contours representing boilers can be identified.
	 */
	public static double getTurretX() throws NoContoursFoundException {
		boolean isBoilerFound = CONTOUR_TABLE.getBoolean("Boiler_Found", false);
		if (!isBoilerFound) {
			throw new NoContoursFoundException();
		} else {
			return CONTOUR_TABLE.getNumber("x", DEFAULT_VALUE);
		}
	}

	/**
	 * Returns a boolean indicating whether or not a contour representing the
	 * boiler could be identified.
	 * 
	 * @return {@code true} if the contour representing the boiler could be
	 *         identified, {@code false} otherwise.
	 */
	public static boolean isBoilerFound() {
		return CONTOUR_TABLE.getBoolean("Boiler_Found", false);
	}
}
