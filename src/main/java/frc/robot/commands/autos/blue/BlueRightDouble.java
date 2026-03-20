package frc.robot.commands.autos.blue;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import frc.robot.commands.Feed;
import frc.robot.commands.Rev;
// NEED TO SET ALIGN
public class BlueRightDouble extends SequentialCommandGroup{
    public BlueRightDouble(CommandSwerveDrivetrain swerve, ShooterSubsystem shooter, WristSubsystem wrist, IntakeSubsystem intake, LimelightSubsystem limelight) {
    addCommands(
        new PathPlannerAuto("BlueRightMultiShoot"),

        Commands.runOnce(() -> wrist.setPosition(WristConstants.wristOutPostion), wrist),
        
        new Rev(shooter, limelight).withTimeout(3.4),

        new WaitCommand(1.4), // 1.4 PATHPLANNER TIME
        new Feed(shooter).withTimeout(2),

        new WaitCommand(4.4), // 3.8 PATHPLANNER TIME
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(2.4), // 6.2 PATHPLANNER TIME
        Commands.runOnce(() -> intake.stop(), intake),

        new WaitCommand(2.8), // 9 PATHPLANNER TIME
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1),
        new Feed(shooter).withTimeout(2)
        );  
    }


}
