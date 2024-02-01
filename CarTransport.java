import java.awt.*;
import java.lang.Math;
import java.util.*;

public abstract class CarTransport extends Vehicle implements Loadable<Car>{

    private int maxLoadNum;
    private boolean rampDown;
    private Deque<Car> loadedVehicles = new LinkedList<Car>();
    private Loader<Car> loader = new Loader<Car>(this);
    private double[] sizeLimits = {0, 0};

    public CarTransport(int nrDoors, Color color, double enginePower, String modelName, int maxLoadNum, double lengthLimit, double widthLimit) {
        super(nrDoors, color, enginePower, modelName);
        setSizeLimits(lengthLimit, widthLimit);
        rampDown = true;
        this.maxLoadNum = maxLoadNum;
    }
    
    public Deque<Car> getCurrentLoad() {
        return loadedVehicles;
    }

    public double[] getSizeLimits() {
        return sizeLimits;
    }

    public void setSizeLimits(double length, double width) {
        sizeLimits[0] = length;
        sizeLimits[1] = width;
    }

    public int getMaxLoad() {
        return maxLoadNum;
    }

    public void raiseRamp() {
        if (getCurrentSpeed() == 0){
            rampDown = false;
        }
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0){
            rampDown = true;
        }
    }

    public void load(Car car) {
        if (rampDown && car.getSize()[0] < sizeLimits[0] && car.getSize()[1] < sizeLimits[1]) {
            loader.load(car);
        }
    }

    public Car unload() {
        if (rampDown) {
            return loader.unloadLast();
        } 
        return null;
    }

    @Override public void move(){ 
        setCurrentPos(getCurrentPos()[0] + Math.cos(getDirection() * Math.PI / 180) * getCurrentSpeed(), getCurrentPos()[1] + Math.sin(getDirection() * Math.PI / 180) * getCurrentSpeed());
        for (Car x : loadedVehicles) { //Move all cars to the position of the car transport, and change their heading to match the car transport.
            x.setDirection(getDirection());
            x.setCurrentPos(getCurrentPos()[0], getCurrentPos()[1]);
        }
    }

    public void startEngine(){
        if (rampDown) {
            currentSpeed = 0.1;
        }
    }
}
