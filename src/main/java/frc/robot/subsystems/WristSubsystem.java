package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;

public class WristSubsystem extends SubsystemBase{
    
    private SparkFlex wristMotor;
    /*
     * Clockwise = Down 
     * Counter Clockwise = Up
     */
    
     private PIDController pidController;

    private RelativeEncoder wristEncoder;

    private double position;

    private double maxLimit;
    private double minLimit;

    private boolean isHoldingPosition;

    public WristSubsystem(){
        wristMotor = new SparkFlex(WristConstants.wristID, MotorType.kBrushless);

        wristEncoder = wristMotor.getEncoder();

        pidController = new PIDController(0.075, 0.0, 0.01);
        pidController.setTolerance(0.1);

        maxLimit = 0;
        minLimit = -20;
    }

    public void isHoldingPosition(){
        
        if(isHoldingPosition){
            isHoldingPosition = false;
        }
        else{
            isHoldingPosition = true;
        }
    }
    public void rotateIn(){
        position = position + .5;
        if(position > maxLimit){
            position = maxLimit;
        }
        if(position < minLimit){
            position = minLimit;
        }
    }
    
    public void rotateOut(){
        position = position - .5;
        
        if(position > maxLimit){
            position = maxLimit;
        }
        if(position < minLimit){
            position = minLimit;
        }
    }
    public void stop() {
        position = wristEncoder.getPosition(); // Save position to hold
    }
    public void setPosition(double m_position){
        position = m_position;
    }
    public boolean atSetpoint() {
    return pidController.atSetpoint(); // Uses WPILib's built-in tolerance checking
}
    
    public void holdPosition() {
        double output = pidController.calculate(wristEncoder.getPosition(), position);
        SmartDashboard.putNumber("Wrist Position", position);
        wristMotor.setVoltage(output * 12);
    }

    @Override
    public void periodic(){
        if(isHoldingPosition){
            holdPosition();
        }
    }
}