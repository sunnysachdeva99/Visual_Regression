package com.practice;



import io.visual_regression_tracker.sdk_java.TestRunOptions;
import io.visual_regression_tracker.sdk_java.VisualRegressionTracker;
import io.visual_regression_tracker.sdk_java.VisualRegressionTrackerConfig;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    protected WebDriver driver;
    VisualRegressionTracker visualRegressionTracker;
    @BeforeSuite
    public void bs() throws InterruptedException {

        VisualRegressionTrackerConfig config = new VisualRegressionTrackerConfig(
                "http://localhost:8686", // replace with your data
                "GoogleTest", // replace with your data
                "7QPEGW2CSZ4MTFJ7BNT2SM0821X8",
                //"41d0bcef-f26e-46a8-8d6a-95c0d39dc70a", // replace with your data
                "Main" // replace with your data
        );

        visualRegressionTracker = new VisualRegressionTracker(config);

        System.setProperty("webdriver.chrome.driver", "/Users/admin/Downloads/chromedriver");
        driver= new ChromeDriver();
        driver.get("https://google.com");
        Thread.sleep(3000);
        driver.manage().window().setSize(new Dimension(1200,800));


    }


    @Test
    public void testExample() throws IOException {
        visualRegressionTracker.track(
                "Home page",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),
                TestRunOptions.builder()
                        .diffTollerancePercent(0)
                        .build()
        );
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }
}
