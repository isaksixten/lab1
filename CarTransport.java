import java.awt.*;
import java.lang.Math;
import java.util.Stack;

public class CarTransport extends Truck {

    private int maxLoadNum;
    private Stack<Car> loadedVehicles = new Stack<Car>();

    public CarTransport(int nrDoors, Color color, double enginePower, String modelName, int maxLoadNum) {
        super(nrDoors, color, enginePower, modelName);
        rampDown = true;
        this.maxLoadNum = maxLoadNum;
    }

    @Override public void raisePlatform() {
        if (getCurrentSpeed() == 0){
            rampDown = false;
        }
    }

    @Override public void lowerPlatform() {
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

    protected double speedFactor() {
        double factor = 0.01 / loadedVehicles.size(); //this is how it works in real life
        return getEnginePower() * factor; 
    }
}
