import java.awt.*;
import java.lang.Math;
import java.util.*;
import java.util.Stack;

public class CarTransport extends Vehicle {

    private int maxLoadNum;
    private boolean rampDown;
    private Stack<Car> loadedVehicles = new Stack<Car>();
    Iterator<Car> CarIterator = loadedVehicles.iterator();

    public CarTransport(int nrDoors, Color color, double enginePower, String modelName, int maxLoadNum) {
        super(nrDoors, color, enginePower, modelName);
        rampDown = true;
        this.maxLoadNum = maxLoadNum;
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

    private boolean loadingFeasible(Car car) {
        double x = Math.abs(getCurrentPos()[0] - car.getCurrentPos()[0]);
        double y = Math.abs(getCurrentPos()[1] - car.getCurrentPos()[1]);
        double distance = Math.sqrt(x*x+y*y);
        if (distance < 5 && rampDown) {
            return true;
        } else {
            return false;
        }
    }

    public void loadCar(Car car) {
        if (loadingFeasible(car) && loadedVehicles.size() < maxLoadNum && loadedVehicles.contains(car) == false) {
            loadedVehicles.push(car);
            car.setCurrentPos(getCurrentPos()[0], getCurrentPos()[1]);
        }
    }

    public void unloadCar() {
        if (rampDown) {
            Car car = loadedVehicles.pop();
            car.setCurrentPos(getCurrentPos()[0] + 5 * Math.cos(180 + getDirection()), getCurrentPos()[1] + 5 * Math.sin(180 + getDirection()));
        }
    }

    @Override public void move(){ 
        setCurrentPos(getCurrentPos()[0] + Math.cos(getDirection() * Math.PI / 180) * getCurrentSpeed(), getCurrentPos()[1] + Math.sin(getDirection() * Math.PI / 180) * getCurrentSpeed());
        while (CarIterator.hasNext()) { //Move all cars to the position of the car transport, and change their heading to match the car transport.
            Car x = CarIterator.next();
            x.setDirection(getDirection());
            x.setCurrentPos(getCurrentPos()[0], getCurrentPos()[1]);
        }
    }

    protected double speedFactor() {
        double factor = 0.01 / loadedVehicles.size(); //Speedfactor impacted by total weight
        return getEnginePower() * factor; 
    }

    public void startEngine(){
        if (rampDown) {
            currentSpeed = 0.1;
        }
    }
}
