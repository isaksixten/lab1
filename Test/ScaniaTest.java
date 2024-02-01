import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest{
    private Scania TestScania;
    @BeforeEach
    public void setupTestScania() {
        TestScania = new Scania();
    }
    @Test
    public void raisePlatformTest() {
        TestScania.raisePlatform();
        assertEquals(TestScania.getPlatformDegree(), 10);
    }
    @Test
    public void cantStartEngineWhilePlatformRaised() {
        TestScania.raisePlatform();
        TestScania.startEngine();
        assertEquals(TestScania.getCurrentSpeed(), 0);
    }
    @Test
    public void platformDegreeNotOverMax() {
        for(int i = 0; i < 10; i++) {
            TestScania.raisePlatform();
        }
        assertEquals(TestScania.getPlatformDegree(), 70);
    }
}