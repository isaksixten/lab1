import java.awt.*;

abstract class Truck extends Vehicle {

    private double platformDegree;
    private double maxPlatformDegree;
    private final double AngleTick = 10;

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public double getPlatformDegree() {
        return platformDegree;
    }

    protected void setMaxPlatformDegree(double angle) {
        maxPlatformDegree = angle;
    }

    public void raisePlatform() {
        if (getCurrentSpeed() == 0){
            platformDegree = Math.min(platformDegree + AngleTick, maxPlatformDegree);
        }
    }

    public void lowerPlatform() {
        if (getCurrentSpeed() == 0){
            platformDegree = Math.max(platformDegree - AngleTick, 0);
        }
    }

    public void startEngine(){
        if (getPlatformDegree() == 0) {
            currentSpeed = 0.1;
        }
    }
}
