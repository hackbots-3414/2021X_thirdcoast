package org.strykeforce.thirdcoast.swerve;
//(c) 2021 Team 3414 Hackbots
//Licensed under MIT license
public interface AzimuthMotor {
    int getDeviceID();
    double getAzimuthPosition();
    void set(double newPosition);
    //TODO consider new name for neutralOutput
    void stop();
    void setAzimuthPosition(int newAzimuthPosition);
    int getAzimuthAbsolutePosition();

}
