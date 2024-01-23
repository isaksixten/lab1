import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Volvo240Test {
    private Volvo240 TestVolvo;
    @BeforeEach
    public void setupVolvo(){
        TestVolvo = new Volvo240();
    }

    @Test
    public void Nrofdoorsinvolvotest() {
        assertEquals(4, TestVolvo.getNrDoors());
    }
    @Test
    public void colorofvolvotest() {
        assertEquals(Color.black, TestVolvo.getColor());
    }
    @Test
    public void volvoenginepowertest() {
        assertEquals(100, TestVolvo.getEnginePower(), 0);
    }
    @Test
    public void volvomodelnametest() {
        assertEquals("Volvo240", TestVolvo.getmodelName());
    }

    @Test
    public void volvopeedfactor() {
        assertEquals(1.25,TestVolvo.speedFactor());
    }
    @Test
    public void volvoincrementspeed() {

    }

}


