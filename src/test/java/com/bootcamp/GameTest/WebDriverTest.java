package com.bootcamp.GameTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverTest {
    public static void main(String[] args){
        System.setProperty("webdriver.edge.driver", "C:\\Program Files\\WebDriver\\edge.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://google.com");

    }
}
