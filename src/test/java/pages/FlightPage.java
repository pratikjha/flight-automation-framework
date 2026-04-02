package pages;

import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class FlightPage {

    public void search(String from, String to) {
        Hooks.driver.findElement(By.id("from")).sendKeys(from);
        Hooks.driver.findElement(By.id("to")).sendKeys(to);
        Hooks.driver.findElement(By.tagName("button")).click();
    }

    public List<String> getFlightNumbers() {
        List<WebElement> elements =
                Hooks.driver.findElements(By.className("flight-number"));

        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}