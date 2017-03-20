package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.Turret;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ServoGoTo extends CommandGroup {

    public ServoGoTo(double position) {
        requires(Robot.turret);
        addSequential(new ServoGoToSubcommand(Turret.MAX_SERVO_ANGLE)); // go to lower limit
        addSequential(new WaitCommand(0.25));
        addSequential(new ServoGoToSubcommand(position));
    }

    public ServoGoTo() {
        requires(Robot.turret);
        addSequential(new ServoGoToSubcommand(Turret.MAX_SERVO_ANGLE)); // go to lower limit
        addSequential(new WaitCommand(0.25));
        addSequential(new ServoGoToSubcommand());
    }
}
