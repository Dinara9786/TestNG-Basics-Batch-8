package com.syntax.class02;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependOnDemo {

    @Test(groups = "smoke")
        public void login () {
        System.out.println("I am login in");
        Assert.assertTrue(true); // output failure 1 and Skips 1

        }

        @Test(dependsOnMethods = "login") // if login fails, this Test method will get ignored
    public void addEmployee(){
            System.out.println("I am adding employee");
        }

        @Test(groups = "test")
    public void test3(){
            System.out.println("I am test 3");
        }
    }

