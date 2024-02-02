import java.awt.*;

public class Scania extends Truck {
    
    private double platformDegree;
    private double maxPlatformDegree;
    private final double AngleTick = 10;

    public Scania() {
        super(2, Color.blue, 100, "Scania");
        setMaxPlatformDegree(70);
    }

    private void setMaxPlatformDegree(double angle) {
        if (angle < 180) {
            maxPlatformDegree = angle;
        }
    }

    public boolean driveableTilt() {
        return platformDegree == 0;
    }

    public void raiseTilt() {
        if (getCurrentSpeed() == 0){
            platformDegree = Math.min(platformDegree + AngleTick, maxPlatformDegree);
        }
    }

    public void lowerTilt() {
        if (getCurrentSpeed() == 0){
            platformDegree = Math.max(platformDegree - AngleTick, 0);
        }
    }

    protected double speedFactor() {
        return getEnginePower() * 0.01; 
    }

    @Override
    public void gas(double amount){
        if (isEngineOn() && driveableTilt()) {
            if(amount >= 0 && amount <= 1){
                incrementSpeed(amount);
            } else {
                throw new IllegalArgumentException("invalid amount");
            }
        } else {
            throw new IllegalStateException("Engine needs to be turned on!");
        }
    }
}
