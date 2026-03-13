package frc.robot.commands.autos;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class BlueLeftDoubleShoot extends SequentialCommandGroup{
    public BlueLeftDoubleShoot(CommandSwerveDrivetrain swerve, ShooterSubsystem shooter, WristSubsystem wrist, IntakeSubsystem intake){
    addCommands(
        new PathPlannerAuto("BlueRightMultiShoot"),

        Commands.runOnce(() -> wrist.setPosition(0.5), wrist),
        
        Commands.runOnce(() -> shooter.speedUp(ShooterConstants.autoShooterSpeed, 1.39), shooter),
        new WaitCommand(1.39),

        Commands.runOnce(() -> shooter.timedFire(ShooterConstants.autoShooterSpeed, 2), shooter),
        new WaitCommand(3.5),

        Commands.run(() -> intake.timedRun(1.75), intake),
        new WaitCommand(2.4),

        Commands.runOnce(() -> shooter.speedUp(ShooterConstants.autoShooterSpeed, 1), shooter),
        new WaitCommand(1),

        Commands.runOnce(() -> shooter.timedFire(ShooterConstants.autoShooterSpeed, 2), shooter)

        );  
    }


}
