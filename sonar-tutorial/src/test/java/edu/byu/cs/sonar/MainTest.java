package edu.byu.cs.sonar;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testMain() {
        String[] args = {"readMe1.txt", "readMe2.txt", "readMe3.txt", "5"};
        assertDoesNotThrow(() -> Main.main(args), "no error message");
    }

    @Test
    void testMainException() {
        String[] args = {"readMe1.txt", "readMe2.txt", "readMe6.txt", "5"};
        assertDoesNotThrow(() -> Main.main(args), "no error message");
    }
}