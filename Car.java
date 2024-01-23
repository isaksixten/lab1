import java.awt.*;
import java.lang.Math;
import java.security.InvalidParameterException;

public class Car implements Moveable{

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double x_pos; // Coordinate Position x
    private double y_pos; // Coordinate Position y
    private double direction; // anglee in degrees, 0 is to the right.
    
    public Car(int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.color = color;
        if (enginePower >= 0){
        this.enginePower = enginePower;
        } else{ throw new InvalidParameterException("must have enginepower larger than 0");
        }
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

    protected double speedFactor(){
        return enginePower * 0.01;
    }

    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());
    }

    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
    
    // TODO fix this method according to lab pm
    public void gas(double amount){
        if(amount > 0 && amount < 1){
            incrementSpeed(amount);
        } else{
            throw new InvalidParameterException("invalid amount");
        }
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        if(amount > 0 && amount < 1){
        decrementSpeed(amount);
        } else{
            throw new InvalidParameterException("invalid amount");
        }
    }


    public void move(){ // public???
        x_pos = x_pos + Math.cos(direction * Math.PI / 180) * getCurrentSpeed();
        y_pos = y_pos + Math.sin(direction * Math.PI / 180) * getCurrentSpeed();

    }

    public void turnLeft(double amount){
        direction = direction + amount;
    }

    public void turnRight(double amount){
        direction = direction - amount;
    }
}