package com.syntax.class04;

import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("Starting test with name " + result.getName());// returns name fo hte test method

    }
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test pass, we are taking screenshot");

    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test fail, we are taking screenshot");

    }

    public void onTestSkipped (ITestResult result) {
        System.out.println("Finishing test with name " + result.getName());
    }




}
