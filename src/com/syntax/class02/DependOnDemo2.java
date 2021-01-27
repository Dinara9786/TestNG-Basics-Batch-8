package com.syntax.class02;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependOnDemo2 {

    @Test(priority = 1)
    public void login() {
        System.out.println("I am loging in");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "login") // this case will be executed only in case when test login passes
    public void addEmployee() {
        System.out.println("I am adding employee");
    }
}
//    @Test ()
//    public void test3() {
//        System.out.println("I am test 3");
//    }