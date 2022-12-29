package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void testSetNewSentence() {
        String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence(), "newSentence should match betterSentence");
    }

    @Test
    void testGetPath(){
        assertEquals("readMe1.txt", sut.getPath(), "Path should be read1.txt");
    }

    @Test
    void testToString () {
        String newSentence = "New sentence";
        sut.setNewSentence(newSentence);
        int count = 1;
        sut.setCount(count);
        assertEquals(newSentence + " " + count, sut.toString(), "ToString should output: New sentence 1");
    }

    @Test
    void testGetCount () {
        int count = 4;
        sut.setCount(count);
        assertEquals(4, sut.getCount(), "Count should be equal to 4");
    }

    @Test
    void testEquals () {
        Object object = null;
        assertNotEquals(object, sut.equals(object), "object is null");

        object = "String";
        assertNotEquals(object, sut.equals(object), "object is not CustomFileReader");

        CustomFileReader newObject = new CustomFileReader(sut.getPath());
//        assertEquals(true, sut.equals(newObject), "object is CustomFileReader");
        newObject.setCount(4);
        assertNotEquals(newObject.getCount(), sut.equals(object), "counts are not equal");

        newObject.setNewSentence("this is a sentence");
        assertNotEquals(newObject.getNewSentence(), sut.equals(object), "new sentence does not match");
    }

}