package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase{
    private SparkFlex intake;
    private double startTime;

    private double intakeSpeed;

    public IntakeSubsystem() {
        intake = new SparkFlex(IntakeConstants.intakeID, MotorType.kBrushless);
        intakeSpeed = IntakeConstants.intakeSpeed;
    }

    public void run (){
        intake.set(intakeSpeed);
    }

    public void timedRun (double runTime){
        startTime = Timer.getFPGATimestamp();
        
        while(Timer.getFPGATimestamp() - startTime < runTime){
            intake.set(intakeSpeed);
        }
    }

    public void stop(){
        intake.set(0);
    }

    @Override
    public void periodic(){
        
        if(RobotContainer.getRightTriggerValue() > 0.1){
            run();
        }
        else{
            stop();
        }
    }
}