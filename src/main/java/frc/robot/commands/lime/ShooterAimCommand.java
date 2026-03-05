package frc.robot.commands.lime;

import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class ShooterAimCommand extends Command {

    private final CommandSwerveDrivetrain drivetrain;

    // Tune these values
    private static final double kP         = 0.05;  // proportional gain for rotation
    private static final double kDeadband  = 1.0;   // degrees of tx before correcting
    private static final double kMaxOmega  = 2.0;   // max rotation speed (radians/sec)

    private final SwerveRequest.ApplyRobotSpeeds driveRequest =
        new SwerveRequest.ApplyRobotSpeeds();

    public ShooterAimCommand(CommandSwerveDrivetrain kDrivetrain) {
        drivetrain = kDrivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        var table = NetworkTableInstance.getDefault().getTable("limelight");

        double tx         = table.getEntry("tx").getDouble(0.0);
        boolean hasTarget = table.getEntry("tv").getDouble(0) == 1.0;

        if (!hasTarget) {
            drivetrain.setControl(driveRequest.withSpeeds(new ChassisSpeeds()));
            return;
        }

        // tx > 0 = target is to the RIGHT → rotate clockwise (negative omega)
        // tx < 0 = target is to the LEFT  → rotate counter-clockwise (positive omega)
        double omegaSpeed = 0.0;

        if (Math.abs(tx) > kDeadband) {
            omegaSpeed = -kP * tx; // negative because tx right = rotate right = negative omega
            omegaSpeed = Math.max(-kMaxOmega, Math.min(kMaxOmega, omegaSpeed)); // clamp
        }

        drivetrain.setControl(
            driveRequest.withSpeeds(
                new ChassisSpeeds(
                    0,          // vx: no forward/back
                    0,          // vy: no strafing
                    omegaSpeed  // omega: rotation
                )
            )
        );
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setControl(driveRequest.withSpeeds(new ChassisSpeeds()));
    }

    @Override
    public boolean isFinished() {
        double tx = NetworkTableInstance.getDefault()
                        .getTable("limelight")
                        .getEntry("tx")
                        .getDouble(0.0);
        return Math.abs(tx) < kDeadband;
    }
}