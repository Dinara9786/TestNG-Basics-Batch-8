package com.syntax.class01;

import org.testng.annotations.Test;

public class TestNGIntro {

    // Test NG works with annotations

    @Test // whichever method we execute we should provide annotations
    public void hello() {
        System.out.println("Hello");
    }

    @Test
    public void sayBye() { // if no annotations the method not be executed becuase no annotations
        System.out.println("Bye");
    }

    @Test
    public void anotherMethod() {
        System.out.println("I am doing great");
    }

}
// methods are executed based on alphabetical order of methods.



