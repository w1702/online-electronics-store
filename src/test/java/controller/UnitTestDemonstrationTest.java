package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitTestDemonstrationTest {
    @Test
    public void whenCreatingClassWithX_thenReturnXInGetter(){
        // Create a new instance of the class
        UnitTestDemonstration unitTestDemonstration = new UnitTestDemonstration(1);
        // Check if expected value equals function return value
        Assertions.assertEquals(1, unitTestDemonstration.getX());
    }
}