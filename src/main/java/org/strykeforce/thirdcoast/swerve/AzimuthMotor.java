package org.strykeforce.thirdcoast.swerve;
//(c) 2021 Team 3414 Hackbots
//Licensed under MIT license
public interface AzimuthMotor {
    int getDeviceID();
    double getAzimuthPosition();
    void setNextPosition(double newPosition);
    void disableAzimuthMotor();
    void setAzimuthReferencePosition(int newAzimuthPosition);
    int getAzimuthAbsolutePosition();

}
