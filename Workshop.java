
import java.util.*;

public class Workshop<A> extends ObjectWithPosition{

    private int maxVehicles;
    private Stack<A> loadedVehicles = new Stack<A>();     //OK av TA att implementera som stack

    public Workshop(double x, double y, int maxVehicles) {
        setCurrentPos(x, y);
        this.maxVehicles = maxVehicles;

        };


    private boolean loadingFeasible(Vehicle vehicle) {
        double x = Math.abs(getCurrentPos()[0] - vehicle.getCurrentPos()[0]);
        double y = Math.abs(getCurrentPos()[1] - vehicle.getCurrentPos()[1]);
        double distance = Math.sqrt(x*x+y*y);
        if (distance < 5) {
        return true;
        }
        else {
            return false;
        }
    }

    public void loadVehicle(A vehicle){
        if (loadingFeasible(vehicle) && loadedVehicles.size() < maxVehicles && loadedVehicles.contains(vehicle) == false) {
            loadedVehicles.push(vehicle);
            vehicle.setCurrentPos(getCurrentPos()[0], getCurrentPos()[1]);
        }
        }
     //brandWorkshop.getmodelName() == vehicle.getmodelName())
    public void unloadVehicle(){
        A vehicle = loadedVehicles.pop();
            vehicle.setCurrentPos(getCurrentPos()[0] + 5 * Math.cos(180), getCurrentPos()[1] + 5 * Math.sin(180));
        }

    

    }


