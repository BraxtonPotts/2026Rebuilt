package frc.robot.commands.autos.red;

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
// NEED TO ADD ALIGN, ADD OUTPOST?
public class RedMidDepotAndOutpost extends SequentialCommandGroup{
    public RedMidDepotAndOutpost(CommandSwerveDrivetrain swerve, ShooterSubsystem shooter, WristSubsystem wrist, IntakeSubsystem intake, LimelightSubsystem limelight) {
    addCommands(
        new PathPlannerAuto("RedMidDepot"),

        Commands.runOnce(() -> wrist.setPosition(WristConstants.wristOutPostion), wrist),
            
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1), 
        new Feed(shooter).withTimeout(2),

        new WaitCommand(3), // 1 PATH PLANNER TIME 
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(1.3), // 2.3 PATH PLANNER TIME
        Commands.runOnce(() -> intake.stop(), intake),

        new WaitCommand(1), // 3.3 PATH PLANNER TIME
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1), 
        new Feed(shooter).withTimeout(2),

        new WaitCommand(3.1), // 4.4 PATH PLANNER TIME
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(1.6), // 6 PATH PLANNER TIME
        Commands.runOnce(() -> intake.stop(), intake),

        new WaitCommand(1.3), // 7.3 PATH PLANNER TIME
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1), 
        new Feed(shooter).withTimeout(2),

        //START OUTPOST

        new WaitCommand(3.2), // 8.5 PATH PLANNER TIME
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(2.2), // 10.7 PATH PLANNER TIME
        Commands.runOnce(() -> intake.run(), intake),

        new WaitCommand(1.6), // 12.3 PATH PLANNER TIME
        new Rev(shooter, limelight).withTimeout(3),

        new WaitCommand(1),
        new Feed(shooter).withTimeout(2)
        );  
    }


}
