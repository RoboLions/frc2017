package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {
	
	public static final int MOTOR_ENCODER_CODES_PER_REV = 0;
	public static final double MOTOR_NOMINAL_OUTPUT_VOLTAGE = 0.0;
	public static final double MOTOR_PEAK_OUTPUT_VOLTAGE = 0.0;
	public static final int MOTOR_FPID_PROFILE = 0;
	public static final double MOTOR_F_GAIN = 0.0;
	public static final double MOTOR_P_GAIN = 0.0;
	public static final double MOTOR_I_GAIN = 0.0;
	public static final double MOTOR_D_GAIN = 0.0;

	CANTalon turretRotationMotor = RobotMap.turretRotationMotor;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Turret(){
		turretRotationMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		turretRotationMotor.reverseSensor(false);
		turretRotationMotor.configEncoderCodesPerRev(MOTOR_ENCODER_CODES_PER_REV);
		turretRotationMotor.configNominalOutputVoltage(MOTOR_NOMINAL_OUTPUT_VOLTAGE, -MOTOR_NOMINAL_OUTPUT_VOLTAGE);
		turretRotationMotor.configPeakOutputVoltage(MOTOR_PEAK_OUTPUT_VOLTAGE, -MOTOR_PEAK_OUTPUT_VOLTAGE);
		turretRotationMotor.setProfile(MOTOR_FPID_PROFILE);
		turretRotationMotor.setF(MOTOR_F_GAIN);
		turretRotationMotor.setP(MOTOR_P_GAIN);
		turretRotationMotor.setI(MOTOR_I_GAIN);
		turretRotationMotor.setD(MOTOR_D_GAIN);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new Turret());
    }
    
	public void setTurretPower(double power) {
        turretRotationMotor.set(power);
    }
	
    public void stop() {
    	setTurretPower(0.0);
    }
    
    public CANTalon getTurretMotor() {
    	return turretRotationMotor;
    }
}