package frc.robot.commands.lime;

import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.ShooterSubsystem;


import java.util.Optional;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import limelight.Limelight;
import limelight.networktables.LimelightPoseEstimator;
import limelight.networktables.LimelightPoseEstimator.EstimationMode;
import limelight.networktables.LimelightResults;
import limelight.networktables.PoseEstimate;
import limelight.networktables.target.AprilTagFiducial;

public class ShooterAimCommand extends Command {
    private final CommandSwerveDrivetrain drivetrain;
    private final ShooterSubsystem shooter;

    public ShooterAimCommand(ShooterSubsystem shootersubsystem, CommandSwerveDrivetrain swervedrivetrain){
        drivetrain = swervedrivetrain;
        shooter = shootersubsystem;
    }

    @Override
    public void initialize() {
        
    }

    @Override
     public void execute(){

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}