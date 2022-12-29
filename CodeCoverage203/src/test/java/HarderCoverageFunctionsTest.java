import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HarderCoverageFunctionsTest {
    HarderCoverageFunctions testClass;

    @BeforeEach
    public void setUp() throws Exception {
        testClass = new HarderCoverageFunctions();
    }

    @Test
    public void castSpellsTest() {
        assertEquals("The spell fizzles in front of you.", testClass.castSpells(4, 0, 2, 4));
        assertEquals("You win!", testClass.castSpells(0, 0, 2, 0));
        assertEquals("Monster is still alive", testClass.castSpells(5, 0, 2, 0));
        assertEquals("The spell fizzles in front of you.", testClass.castSpells(0, 2, 0, 4));
        assertEquals("The spell destroys the monster!", testClass.castSpells(3, 2, 2, 4));
        assertEquals("The spell zaps the monster!", testClass.castSpells(4, 2, 2, 2));
    }


    @Test
    public void truthFinderTest(){
        assertEquals("YOU FOUND THE TRUTH!", testClass.truthFinder(false, true, true));
        assertEquals("Well I guess that is true too", testClass.truthFinder(true, false, true));
        assertEquals("Well that might be true", testClass.truthFinder(false, true, false));
        assertEquals("Well that might be true", testClass.truthFinder(true, true, false));
        assertEquals("Are you even trying to find the truth?", testClass.truthFinder(false, false, false));
    }
}