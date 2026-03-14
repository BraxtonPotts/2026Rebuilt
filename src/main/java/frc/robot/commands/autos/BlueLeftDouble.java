package frc.robot.commands.autos;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class BlueLeftDouble extends SequentialCommandGroup{
    public BlueLeftDouble(CommandSwerveDrivetrain swerve, ShooterSubsystem shooter, WristSubsystem wrist, IntakeSubsystem intake){
    addCommands(
        //new PathPlannerAuto("BlueRightMultiShoot"),

        Commands.runOnce(() -> wrist.setPosition(WristConstants.wristOutPostion), wrist),
        
        Commands.runOnce(() -> shooter.speedUp(ShooterConstants.autoShooterSpeed, 1.39), shooter),
        new WaitCommand(1.39),

        Commands.runOnce(() -> shooter.fire(ShooterConstants.autoShooterSpeed), shooter),
        new WaitCommand(2),
        Commands.runOnce(() -> shooter.stop(), shooter),
        new WaitCommand(3),

        Commands.runOnce(() -> intake.run(), intake),
        new WaitCommand(1.5),
        Commands.runOnce(() -> intake.stop(), intake),
        new WaitCommand(2.5),

        Commands.run(() -> shooter.speedUp(ShooterConstants.autoShooterSpeed, 1), shooter),
        new WaitCommand(1),

        Commands.run(() -> shooter.fire(ShooterConstants.autoShooterSpeed), shooter),
        new WaitCommand(2),
        Commands.runOnce(() -> shooter.stop(), shooter)

        );  
    }


}
