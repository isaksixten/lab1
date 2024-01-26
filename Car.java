import java.awt.*;
import java.lang.Math;

abstract class Car implements Moveable{

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double x_pos; // Coordinate Position x
    private double y_pos; // Coordinate Position y
    private double direction; // anglee in degrees, 0 is to the right.
    private final int turndegrees = 30; // Constant number of degrees for a turn.
    
    public Car(int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.color = color;
        setEnginePower(enginePower);
        this.modelName = modelName;
        this.x_pos = 0;
        this.y_pos = 0;
        this.direction = 0;
        stopEngine();
    }
    
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    public double[] getCurrentPos() { // Added for test of move().
        double[] pos = {x_pos, y_pos};
        return pos;
    }
    public double getDirection() { // Added for test of turn()
        return direction;
    }
    public void setEnginePower(double amount){
        if (amount >= 0){
	        enginePower = amount;
        } else {
            throw new IllegalArgumentException("Enginepower needs to be >= 0");
        }
    }
    public void setColor(Color clr){
	    color = clr;
    }
    public String getmodelName(){
        return modelName;
    }
    public void startEngine(){
	    currentSpeed = 0.1;
    }
    public void stopEngine(){
	    currentSpeed = 0;
    }

    abstract protected double speedFactor();

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
    
    public void gas(double amount){
        if(amount >= 0 && amount <= 1){
            incrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("invalid amount");
        }
    }

    public void brake(double amount){
        if(amount >= 0 && amount <= 1){
        decrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("invalid amount");
        }
    }

    public void move(){ 
        x_pos = x_pos + Math.cos(direction * Math.PI / 180) * getCurrentSpeed();
        y_pos = y_pos + Math.sin(direction * Math.PI / 180) * getCurrentSpeed();

    }

    public void turnLeft(){
        direction = direction + turndegrees;
    }

    public void turnRight(){
        direction = direction - turndegrees;
    }
}