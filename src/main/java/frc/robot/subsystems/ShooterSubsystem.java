package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase{
    
    SparkFlex leftShoot;
    SparkFlex rightShoot;
    SparkFlex feed;

    double startTime;
    boolean hasStarted;
    

    public ShooterSubsystem(){

        leftShoot = new SparkFlex(ShooterConstants.outLeftID, MotorType.kBrushless);
        rightShoot = new SparkFlex(ShooterConstants.outRightID, MotorType.kBrushless);

        feed = new SparkFlex(ShooterConstants.feedID, MotorType.kBrushless);

    }

    public void speedUp(double speed, double speedUpTime){
        startTime = Timer.getFPGATimestamp();
        
        while(Timer.getFPGATimestamp() - startTime < speedUpTime){
            leftShoot.set(-speed); // clockwise
            rightShoot.set(speed); //counterclockwise
        }
    }

    public void timedFire(double fireSpeed, double shootTime){
        startTime = Timer.getFPGATimestamp();
        while(Timer.getFPGATimestamp() - startTime < shootTime){
        feed.set(-0.3);

        leftShoot.set(-fireSpeed);
        rightShoot.set(fireSpeed);
        }
    }
    public void fire(double fireSpeed){
        feed.set(-0.3);

        leftShoot.set(-fireSpeed);
        rightShoot.set(fireSpeed);
    }
    
    public void stop(){
        feed.set(0);

        leftShoot.set(0);
        rightShoot.set(0);
    }

    @Override
    public void periodic(){

        if (RobotContainer.getABoolean() == 1) {
            if (hasStarted == false) {
                speedUp(ShooterConstants.revSpeed, ShooterConstants.revTime);
                hasStarted = true;
            }
            else{
            fire(ShooterConstants.shootSpeed);
            }
        }
        else{
            stop();
            hasStarted = false;
        }
    }
}
