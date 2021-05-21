package org.strykeforce.thirdcoast.swerve;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SparkMaxAzimuthImpl extends CANSparkMax implements AzimuthMotor {

    private static int TICKS_PER_REVOLUTION = 0;
        
    private CANCoder steeringEncoder;

    private PIDController pidController;

    public SparkMaxAzimuthImpl(int deviceID, MotorType type, int encoderCANId, double kP, double kI, double kD) {
        super(deviceID, type);
        steeringEncoder = new CANCoder(encoderCANId);
        pidController = new PIDController(kP, kI, kD);
        SmartDashboard.putData("azimuth-"+ deviceID, pidController);
    }

    /**
     * Convenience constructor for Brushless motor 
     * 
     * @param deviceID
     */
    public SparkMaxAzimuthImpl(int deviceID, int encoderCANId, double kP, double kI, double kD){
        this(deviceID, MotorType.kBrushless, encoderCANId, kP, kI, kD);
    }

    @Override
    public void disableAzimuthMotor() {
        disable();
    }

    @Override
    public int getAzimuthAbsolutePosition() {
        // TODO verify which encoder we are using 
        // Our SparkMax full reverse input pulse = 1000 us
        // Neutral input pulse = 1500 us 
        // Full forward input pulse = 2000 us 
      // return (int) (((getAzimuthPosition() % TICKS_PER_REVOLUTION) * 1000) + 1000); 
    
      return (int) getAzimuthPosition();
    }

    @Override
    public double getAzimuthPosition() {
        return  steeringEncoder.getPosition();
    }

    @Override
    public int getDeviceID() {
        return getDeviceId();
    }

    @Override
    public void setAzimuthReferencePosition(int newAzimuthPosition) {
        steeringEncoder.setPosition(newAzimuthPosition, 10);
    }

    @Override
    public void setNextPosition(double newPosition) {
        set(pidController.calculate(getAzimuthPosition(), newPosition));

    }
    
}
