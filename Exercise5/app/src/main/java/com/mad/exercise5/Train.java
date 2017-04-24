package com.mad.exercise5;

/**
 * Train model for adapter
 */
public class Train {

    private String mPlatform;
    private int mArrivalTime;
    private String mStatus;
    private String mDestination​;
    private String mDestinationTime​;

    /**
     * Null constructor
     */
    public Train() {

    }

    /**
     * Main constructor, assigns parameters to class variables.
     *
     * @param platform
     * @param arrivalTime
     * @param status
     * @param destination
     * @param destinationTime
     */
    public Train(String platform, int arrivalTime, String status, String destination, String destinationTime) {
        mPlatform = platform;
        mArrivalTime = arrivalTime;
        mStatus = status;
        mDestinationTime​ = destinationTime;
        mDestination​ = destination;
    }

    /**
     * Get destination time
     *
     * @return mDestinationTime
     */
    public String getDestinationTime​() {
        return mDestinationTime​;
    }

    /**
     * Set destination time
     *
     * @param DestinationTime​
     */
    public void setDestinationTime​(String DestinationTime​) {
        this.mDestinationTime​ = DestinationTime​;
    }

    /**
     * Get destination
     *
     * @return mDestination
     */
    public String getDestination​() {
        return mDestination​;
    }

    /**
     * Set destination
     *
     * @param Destination​
     */
    public void setDestination​(String Destination​) {
        this.mDestination​ = Destination​;
    }

    /**
     * Get status
     *
     * @return mStatus
     */
    public String getStatus() {
        return mStatus;
    }

    /**
     * Set status
     *
     * @param Status
     */
    public void setStatus(String Status) {
        this.mStatus = Status;
    }

    /**
     * Get arrival time
     *
     * @return mArrivalTime
     */
    public int getArrivalTime() {
        return mArrivalTime;
    }

    /**
     * Set arrival time
     *
     * @param ArrivalTime
     */
    public void setArrivalTime(int ArrivalTime) {
        this.mArrivalTime = ArrivalTime;
    }

    /**
     * Get platform
     *
     * @return mPlatform
     */
    public String getPlatform() {
        return mPlatform;
    }

    /**
     * Set platform
     *
     * @param Platform
     */
    public void setPlatform(String Platform) {
        this.mPlatform = Platform;
    }
}
