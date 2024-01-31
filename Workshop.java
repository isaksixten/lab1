import java.util.Set;

public class Workshop {
    
    private double x_pos;
    private double y_pos;
    private CarTransport parent;
    private Vehicle[] loadedVehicles;

    public Workshop(double x, double y, int maxCars, Set<String> allowedModels) {
        setCurrentPos(x, y);
    }

    private void setCurrentPos(double x, double y) {
        x_pos = x;
        y_pos = y;
    }

    public void loadCar() {
        parent.loadCar(null);
    }
}
