package com.visl.tools;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TakeScreenshot {
    private static int      DISPLAY_NUMBER  = 99;
    private static String   XVFB            = "/usr/bin/Xvfb";
    private static String   XVFB_COMMAND    = XVFB + " :" + DISPLAY_NUMBER;
    private static String   URL             = "http://www.fjordabladet.no/";
    private static String   RESULT_FILENAME = "/tmp/screenshot.png";

    public static void main ( String[] args ) throws IOException
    {
        Process p = Runtime.getRuntime().exec(XVFB_COMMAND);
        FirefoxBinary firefox = new FirefoxBinary();
        firefox.setEnvironmentProperty("DISPLAY", ":" + DISPLAY_NUMBER);
        WebDriver driver = new FirefoxDriver(firefox, null);
        driver.get(URL);
        File scrFile = ( (TakesScreenshot) driver ).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(RESULT_FILENAME));
        driver.close();
        p.destroy();
    }
}