package server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        todoInput.clear();
        driver.quit();
        server.stop();
    }

    private void submitTodo(String todoText) {
        todoInput.sendKeys(todoText);
        todoInput.submit();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        todoInput.clear();

    }

    @Test
    void testAddTodos() {
        List<WebElement> foundTodoElements = driver.findElements(By.className("todo-text"));
        Assertions.assertEquals(todoTestList.size(), foundTodoElements.size());

        List<String> foundTodoStrings = getTextForElements(foundTodoElements);

        for(String expectedTodo : todoTestList) {
            Assertions.assertTrue(foundTodoStrings.contains(expectedTodo), "Missing todo " + expectedTodo);
        }
    }

    @Test
    void testMarkAndShowCompleted() {
        List<String> expectedCompletedItems = Arrays.asList("Learn Selenium", "Code profiling with YourKit");

        for (String item : expectedCompletedItems) {
            WebElement completedItemCheckbox = findCheckboxForTodoItemByText(item);
            Assertions.assertNotNull(completedItemCheckbox);
            completedItemCheckbox.click();
        }

        WebElement showCompletedButton = driver.findElement(By.id("show-completed"));
        Assertions.assertNotNull(showCompletedButton);
        showCompletedButton.click();

        List<WebElement> displayedTodoElements = driver.findElements(By.className("todo-text"));
        Assertions.assertEquals(expectedCompletedItems.size(), displayedTodoElements.size());

        List<String> displayedElementText = getTextForElements(displayedTodoElements);
        for (String completedElement : expectedCompletedItems) {
            Assertions.assertTrue(displayedElementText.contains(completedElement),
                    "Expected item " + completedElement + " not displayed");
        }

    }

    @Test
    void testDeleteCompleted() {
        List<String> expectedCompletedItems = Arrays.asList(
                "Learn Selenium",
                "Understanding and modifying an existing codebase",
                "Static analysis with IntelliJ Static Analyzer",
                "Code profiling with YourKit",
                "Dependency Management Tools: Maven and Gradle",
                "Unit Testing with JUnit"
        );

        //mark all items in the list as completed
        for (String item : expectedCompletedItems) {
            WebElement completedItemCheckbox = findCheckboxForTodoItemByText(item);
            Assertions.assertNotNull(completedItemCheckbox);
            completedItemCheckbox.click();
        }

        WebElement deleteCompletedButton = driver.findElement(By.id("delete-completed"));
        Assertions.assertNotNull(deleteCompletedButton);
        deleteCompletedButton.click();

        List<WebElement> displayedTodoElements = driver.findElements(By.className("todo-text"));
        Assertions.assertEquals(0, displayedTodoElements.size());
    }

    private List<String> getTextForElements(List<WebElement> webElements) {
        List<String> webElementText = new ArrayList<>(webElements.size());

        for(WebElement webElement : webElements) {
            webElementText.add(webElement.getText());
        }
        return webElementText;
    }

    private WebElement findCheckboxForTodoItemByText (String todoItemText) {
        String xpathExpression = String.format("//*[contains(text(), '%s')]/parent::li", todoItemText);
        WebElement liElement = driver.findElement(By.xpath(xpathExpression));
        return liElement.findElement(By.className("completed-checkbox"));
    }

}