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
	public static final double GEAR_TOLERANCE_FACTOR = 0.02;
	
	public static final double DEFAULT_Y_IMAGE_SIZE = 240;
	public static final double DEFAULT_X_IMAGE_SIZE = 320;
	public static final double BOILER_Y_IMAGE_SIZE = CONTOUR_TABLE.getNumber("imageSizeY", DEFAULT_Y_IMAGE_SIZE);
	public static final double BOILER_X_IMAGE_SIZE = CONTOUR_TABLE.getNumber("imageSizeX", DEFAULT_X_IMAGE_SIZE);
	
	public static final double GEAR_Y_IMAGE_SIZE = CONTOUR_TABLE.getNumber("GearImageSizeY", DEFAULT_Y_IMAGE_SIZE);
	public static final double GEAR_X_IMAGE_SIZE = CONTOUR_TABLE.getNumber("GearImageSizeX", DEFAULT_X_IMAGE_SIZE);
	
	
	public static final double BOILER_X_AXIS_TARGET = BOILER_X_IMAGE_SIZE / 2;
	public static final double BOILER_X_AXIS_TOLERANCE = BOILER_X_IMAGE_SIZE * TOLERANCE_FACTOR;

	public static final double GEAR_X_AXIS_TARGET = GEAR_X_IMAGE_SIZE / 2; // TODO: FIX THIS PLEASE - Terry
	public static final double GEAR_X_AXIS_TOLERANCE = GEAR_X_IMAGE_SIZE * GEAR_TOLERANCE_FACTOR; // TODO: FIX THIS PLEASE - Terry
	
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
	 * Gets the center x (pixels) of the Gear.
	 * 
	 * @return (x_Gear) the center x in pixels of the two contours of the Gear Peg.
	 * @throws NoContoursFoundException
	 *             If no contours representing Gear can be identified.
	 */
	public static double getGearX() throws NoContoursFoundException {
		boolean isPegFound = CONTOUR_TABLE.getBoolean("Gear_Found", false);
		if (!isPegFound) {
			throw new NoContoursFoundException();
		} else {
			return CONTOUR_TABLE.getNumber("x_Gear", DEFAULT_VALUE);
		}
	}
	
	/**
	 * Gets the x-axis offset of the boiler from where the turret is pointing.
	 * 
	 * @return The x-axis offset of the boiler in pixels.
	 * @throws NoContoursFoundException
	 *             If no contours representing boilers can be identified.
	 */
	public static double getGearXOffset() throws NoContoursFoundException {
		return GEAR_X_AXIS_TARGET - getGearX();
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
		return BOILER_X_AXIS_TARGET - getTurretX();
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
	
	/**
	 * Returns a boolean indicating whether or not a contour representing the
	 * Gear_Peg could be identified.
	 * 
	 * @return {@code true} if the contour representing the Gear/Peg could be
	 *         identified, {@code false} otherwise.
	 */
	public static boolean isGearFound() {
		return CONTOUR_TABLE.getBoolean("Gear_Found", false);
	}
}
