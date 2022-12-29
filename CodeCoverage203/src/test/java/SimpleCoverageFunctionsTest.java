import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCoverageFunctionsTest {
    SimpleCoverageFunctions testClass;

    @BeforeEach
    public void setUp() throws Exception {
        testClass = new SimpleCoverageFunctions();
    }

    @Test
    public void addTwoNumTest(){
        int sum = testClass.addTwoNum(1, 2);
        assertEquals(3, sum);
    }

    @Test
    void returnLargest() {
        assertEquals(7, testClass.returnLargest(7, 5));
        assertEquals(7, testClass.returnLargest(5, 7));
    }


    @Test
    void doWeirdStuff() {
        assertEquals(35, testClass.doWeirdStuff(5, 7, 9));
        assertEquals(2, testClass.doWeirdStuff(10, 7, 8));
        assertEquals(7, testClass.doWeirdStuff(10, 7, 5));
    }
}