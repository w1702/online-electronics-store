package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitTestDemonstrationTest {
    @Test
    public void whenCreatingClassWithX_thenReturnXInGetter(){
        // Arrange - setup data or input to your function
        int input = 1;
        // Act - calling your function
        // Create a new instance of the class
        UnitTestDemonstration unitTestDemonstration = new UnitTestDemonstration(input);
        int result = unitTestDemonstration.getX();
        // Assert - checking the output of your function
        // Check if expected value equals function return value
        Assertions.assertEquals(input, result);
    }
}