package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitTestDemonstrationTest {
    @Test
    public void whenCreatingClassWithX_thenReturnXInGetter(){
        // 1 arrange - setup data or input to your function
        int expected = 1;
        // 2 act - calling your function
        // Create a new instance of the class
        UnitTestDemonstration unitTestDemonstration = new UnitTestDemonstration(expected);
        int result = unitTestDemonstration.getX();
        // assert - checking the output of your function
        // Check if expected value equals function return value
        Assertions.assertEquals(1, result);
    }
}