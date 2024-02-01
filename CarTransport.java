import java.awt.*;
import java.lang.Math;
import java.util.*;

public abstract class CarTransport extends Vehicle implements Loadable<Car>{

    private int maxLoadNum;
    private boolean rampDown;
    private Deque<Car> loadedVehicles = new LinkedList<Car>();
    private Loader<Car> loader = new Loader<Car>(this);
    Iterator<Car> CarIterator = loadedVehicles.iterator();

    public CarTransport(int nrDoors, Color color, double enginePower, String modelName, int maxLoadNum) {
        super(nrDoors, color, enginePower, modelName);
        rampDown = true;
        this.maxLoadNum = maxLoadNum;
    }
    
    public Deque<Car> getCurrentLoad() {
        return loadedVehicles;
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
        if (rampDown) {
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
        while (CarIterator.hasNext()) { //Move all cars to the position of the car transport, and change their heading to match the car transport.
            Car x = CarIterator.next();
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
