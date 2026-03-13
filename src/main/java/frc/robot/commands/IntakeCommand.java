package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class IntakeCommand extends Command {
  
  private final IntakeSubsystem m_intakesubsystem;
  private final double Time;

  public IntakeCommand(IntakeSubsystem subsystem, double time) {
    m_intakesubsystem = subsystem;
    Time = time;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}
  
  @Override
  public void execute(){
    m_intakesubsystem.timedRun(Time);
  }
  
  @Override
  public boolean isFinished() {
    m_intakesubsystem.stop();
    return true;
  }
}