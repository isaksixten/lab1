import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CarTransportTest{
    private VolvoFM9 TestVolvoFM9;
    private Saab95 TestCar1;
    private Saab95 TestCar2;
    private Saab95 TestCar3;
    private Scania TestTruck;

    @BeforeEach
    public void setupTestCarTransport() {
        TestVolvoFM9 = new VolvoFM9();
        TestCar1 = new Saab95();
        TestCar2 = new Saab95();
        TestCar3 = new Saab95();
        TestTruck = new Scania();
    }
    @Test
    public void loadTest () {
        TestVolvoFM9.load(TestCar1);
        assertEquals(1, TestVolvoFM9.getCurrentLoad().size());
    }
}
