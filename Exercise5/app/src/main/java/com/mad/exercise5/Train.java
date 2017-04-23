package com.mad.exercise5;

public class Train {

    private String mPlatform;
    private int mArrivalTime;
    private String mStatus;
    private String mDestination​;
    private String mDestinationTime​;

    public Train() {

    }

    public Train(String platform, int arrivalTime, String status, String destination, String destinationTime) {

        mPlatform = platform;
        mArrivalTime = arrivalTime;
        mStatus = status;
        mDestinationTime​ = destinationTime;
        mDestination​ = destination;
    }

    public String getDestinationTime​() {
        return mDestinationTime​;
    }

    public void setDestinationTime​(String DestinationTime​) {
        this.mDestinationTime​ = DestinationTime​;
    }

    public String getDestination​() {
        return mDestination​;
    }

    public void setDestination​(String Destination​) {
        this.mDestination​ = Destination​;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String Status) {
        this.mStatus = Status;
    }

    public int getArrivalTime() {
        return mArrivalTime;
    }

    public void setArrivalTime(int ArrivalTime) {
        this.mArrivalTime = ArrivalTime;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String Platform) {
        this.mPlatform = Platform;
    }
}
