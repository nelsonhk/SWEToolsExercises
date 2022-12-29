package server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    private Server server;
    private WebDriver driver;
    private WebElement todoInput;

    // You may not have created a list before the way this code does.
    // This is a shortcut to creating a list and calling add() multiple times to add the items.
    private final List<String> todoTestList = Arrays.asList(
            "Learn Selenium",
            "Understanding and modifying an existing codebase",
            "Static analysis with IntelliJ Static Analyzer",
            "Code profiling with YourKit",
            "Dependency Management Tools: Maven and Gradle",
            "Unit Testing with JUnit");

    @BeforeEach
    void setUp() throws IOException {
        server = new Server();
        server.start();

        driver = new ChromeDriver();
        driver.get("http://localhost:" + server.getPort());

        todoInput = driver.findElement(By.tagName("input"));

        for (String todo : todoTestList) {
            submitTodo(todo);
        }
    }

    @AfterEach
    void teardown() {
        // TODO: Implement this according to the tutorial
    }

    private void submitTodo(String todoText) {
        // TODO: Implement this according to the tutorial
    }

    @Test
    void testAddTodos() {
        // TODO: Implement this according to the tutorial
    }
}