package frc.robot.commands.autos.blue;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.WristConstants;
import frc.robot.commands.Feed;
import frc.robot.commands.Rev;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
// NEED TO SET ALIGN
public class BlueMidDepotAndOutpost extends SequentialCommandGroup{
    public BlueMidDepotAndOutpost(CommandSwerveDrivetrain swerve, ShooterSubsystem shooter, WristSubsystem wrist, IntakeSubsystem intake, LimelightSubsystem limelight) {
    addCommands(
        new PathPlannerAuto("BlueMidDepotAndOutpost"),

        Commands.runOnce(() -> wrist.setPosition(WristConstants.wristOutPostion), wrist),
            
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1), 
        new Feed(shooter).withTimeout(2),

        new WaitCommand(3.2), // 1.2 PATH PLANNER TIME 
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(1.3), // 2.5 PATH PLANNER TIME
        Commands.runOnce(() -> intake.stop(), intake),

        new WaitCommand(1.25), // 3.75 PATH PLANNER TIME
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1), 
        new Feed(shooter).withTimeout(2),

        new WaitCommand(3.25), // 5 PATH PLANNER TIME
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(1.5), // 6.5 PATH PLANNER TIME
        Commands.runOnce(() -> intake.stop(), intake),

        new WaitCommand(1.8), //  7.8 PATH PLANNER /TIME
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1), 
        new Feed(shooter).withTimeout(2),

        // START OUTPOST

        new WaitCommand(3.6), // 9.4 PATH PLANNER TIME
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(1.9), // 11.3 PATH PLANNER TIME
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(1.5), // 12.8 PATH PLANNER TIME
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1),
        new Feed(shooter).withTimeout(2)
        );
    }


}
