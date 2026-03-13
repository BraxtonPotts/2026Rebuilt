package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootCommand extends Command {
  
  private final ShooterSubsystem m_shootersubsystem;

  public ShootCommand(ShooterSubsystem subsystem) {
    m_shootersubsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_shootersubsystem.speedUp(ShooterConstants.revSpeed, ShooterConstants.revTime);
  }
  
  @Override
  public void execute(){
    m_shootersubsystem.fire(ShooterConstants.shootSpeed);
  }
  
  @Override
  public boolean isFinished() {
    m_shootersubsystem.stop();
    return true;
  }
}