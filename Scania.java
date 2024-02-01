import java.awt.*;

public class Scania extends Truck {

    public Scania() {
        super(2, Color.blue, 100, "Scania");
        setMaxPlatformDegree(70);
    }

    protected double speedFactor() {
        return getEnginePower() * 0.01; 
    }
}
