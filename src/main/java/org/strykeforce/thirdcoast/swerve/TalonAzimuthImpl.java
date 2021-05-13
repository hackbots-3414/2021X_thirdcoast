package org.strykeforce.thirdcoast.swerve;
//(c) 2021 Team 3414 Hackbots

//Licensed under MIT license

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import static com.ctre.phoenix.motorcontrol.ControlMode.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctre.phoenix.ErrorCode;
import org.strykeforce.thirdcoast.talon.Errors;

public class TalonAzimuthImpl extends TalonSRX implements AzimuthMotor {
    private static final Logger logger = LoggerFactory.getLogger(TalonAzimuthImpl.class);

    public TalonAzimuthImpl(int deviceNumber){
        super(deviceNumber);
    }

    public double getAzimuthPosition(){
        return getSelectedSensorPosition(0);
    }

    public void setNextPosition(double newPosition) {
        set(MotionMagic, newPosition);

    }

    public void disableAzimuthMotor() {
        neutralOutput();
        
    }

    public void setAzimuthReferencePosition(int newAzimuthPosition){
        ErrorCode err = setSelectedSensorPosition(newAzimuthPosition, 0, 10);
        Errors.check(err, logger);
    
    }
    
    public int getAzimuthAbsolutePosition() {
       return getSensorCollection().getPulseWidthPosition() & 0xFFF;
    }

}


