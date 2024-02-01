import java.util.*;

public class Workshop<A extends Vehicle> extends ObjectWithPosition implements Loadable<A>{

    private int maxVehicles;
    private Deque<A> loadedVehicles = new LinkedList<A>();     //OK av TA att implementera som stack
    private Loader<A> loader = new Loader<A>(this);

    public Workshop(double x, double y, int maxVehicles) {
        setCurrentPos(x, y);
        this.maxVehicles = maxVehicles;

    };

    public Deque<A> getCurrentLoad() {
        return loadedVehicles;
    }
    
    public int getMaxLoad() {
        return maxVehicles;
    }
    
    public void load(A vehicle) {
        loader.load(vehicle);
    }

    public A unload() {
        return loader.unloadFirst();
    }
}


