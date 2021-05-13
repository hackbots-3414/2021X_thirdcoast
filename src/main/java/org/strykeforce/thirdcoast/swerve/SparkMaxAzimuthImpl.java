package org.strykeforce.thirdcoast.swerve;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

public class SparkMaxAzimuthImpl extends CANSparkMax implements AzimuthMotor {

    private static int TICKS_PER_REVOLUTION = 0;
        
    

    public SparkMaxAzimuthImpl(int deviceID, MotorType type) {
        super(deviceID, type);
    }

    /**
     * Convenience constructor for Brushless motor 
     * 
     * @param deviceID
     */
    public SparkMaxAzimuthImpl(int deviceID){
        this(deviceID, MotorType.kBrushless);
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
       return (int) (((getAzimuthPosition() % TICKS_PER_REVOLUTION) * 1000) + 1000); 
    }

    @Override
    public double getAzimuthPosition() {
         // TODO verify which encoder we are using 
        return (int) getEncoder().getPosition();
    }

    @Override
    public int getDeviceID() {
        return getDeviceId();
    }

    @Override
    public void setAzimuthReferencePosition(int newAzimuthPosition) {
        getEncoder().setPosition(newAzimuthPosition);
    }

    @Override
    public void setNextPosition(double newPosition) {
        getPIDController().setReference(newPosition, ControlType.kPosition);    
    }
    
}
