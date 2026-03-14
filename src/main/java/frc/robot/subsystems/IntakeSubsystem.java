package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import java.util.function.DoubleSupplier;

public class IntakeSubsystem extends SubsystemBase{
    private SparkFlex intake;
    private double startTime;

    private DoubleSupplier rightTrigger;    
    private double intakeSpeed;

    public IntakeSubsystem(DoubleSupplier rightTrigger) {
    intake = new SparkFlex(IntakeConstants.intakeID, MotorType.kBrushless);
    intakeSpeed = IntakeConstants.intakeSpeed;
    this.rightTrigger = rightTrigger;
}

    public void run (){
        intake.set(intakeSpeed);
    }

    public void stop(){
        intake.set(0);
    }

    @Override
    public void periodic(){
        
        if(rightTrigger.getAsDouble() > 0.1){
            run();
        }
        else{
            stop();
        }
    }
}