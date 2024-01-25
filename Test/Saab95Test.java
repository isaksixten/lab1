
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.lang.IllegalArgumentException;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class Saab95Test{
    private Saab95 TestSaab;
    @BeforeEach
    public void setupTestsaab(){
        TestSaab = new Saab95();
    }
    @Test
    public void TurboOntest() {
        assertFalse(TestSaab.statusTurboOn());
    }
    @Test
    public void Nrofdoorsinsaabtest() {
        assertEquals(2, TestSaab.getNrDoors());
    }
    @Test
    public void colorofsaabtest() {
        assertEquals(Color.red, TestSaab.getColor());
        TestSaab.setColor(Color.blue);
        assertEquals(Color.blue, TestSaab.getColor());
    }
    @Test
    public void saabenginepowertest() {
        assertEquals(125, TestSaab.getEnginePower(), 0);
    }
    @Test
    public void saabmodelnametest() {
        assertEquals("Saab95", TestSaab.getmodelName());
    }
    @Test
    public void setTurboOntest() {
        TestSaab.setTurboOn();
        assertTrue(TestSaab.statusTurboOn());
    }
    @Test
    public void setTurboOfftest() {
        TestSaab.setTurboOff();
        assertFalse(TestSaab.statusTurboOn());
    }

    @Test
    public void saabspeedfactorwithTurbotest() {
        TestSaab.setTurboOn();
        assertEquals(1.625,TestSaab.speedFactor());
    }
    @Test
    public void saabspeedfactorwithouTurbotest() {
        TestSaab.setTurboOff();
        assertEquals(1.25,TestSaab.speedFactor());
    }
    @Test
    public void saabincrementspeed() {
        TestSaab.incrementSpeed(100);
        assertEquals(125,TestSaab.getCurrentSpeed());
    }
    @Test
    public void saabdecrementspeed() {
        TestSaab.decrementSpeed(100);
        assertEquals(0,TestSaab.getCurrentSpeed());
    }
    @Test
    public void gasAsExpected() {
        assertThrows(IllegalArgumentException.class, () -> TestSaab.gas(-1), "Gas below zero is allowed");
        assertThrows(IllegalArgumentException.class, () -> TestSaab.gas(2), "Gas above 1 is allowed");
        double beforegas = TestSaab.getCurrentSpeed();
        TestSaab.gas(1);
        assertEquals(beforegas + TestSaab.getEnginePower()*0.01*1, TestSaab.getCurrentSpeed());
    }
    @Test
    public void brakeAsExpected() {
        assertThrows(IllegalArgumentException.class, () -> TestSaab.brake(-1), "Brake below zero is allowed");
        assertThrows(IllegalArgumentException.class, () -> TestSaab.brake(2), "Brake above 1 is allowed");
        TestSaab.gas(1);
        double beforebrake = TestSaab.getCurrentSpeed();
        TestSaab.brake(1);
        assertEquals(beforebrake - TestSaab.getEnginePower()*0.01*1, TestSaab.getCurrentSpeed());
    }
    @Test
    public void saabEnginePowerTest() {
        assertThrows(IllegalArgumentException.class, () -> TestSaab.setEnginePower(-10), "Expected enginePower to not be negative but it is");
    }
    @Test
    public void moveAndTurnAsExpected() {
        TestSaab.turnLeft(30);
        TestSaab.turnRight(30);
        double[] startpos = TestSaab.getCurrentPos();
        TestSaab.gas(1);
        TestSaab.move();
        double[] endpos = TestSaab.getCurrentPos();
        assertEquals(startpos[0] + TestSaab.getCurrentSpeed(), endpos[0], "Car in unexpected position on x axis");
        assertEquals(startpos[1], endpos[1], "Car has traveled in the y direction, but shouldn't");
    }
}

