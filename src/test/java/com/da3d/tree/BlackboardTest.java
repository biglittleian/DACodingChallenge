package com.da3d.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackboardTest {

    @Test
    void resetValues() {
        Blackboard blackboard = new Blackboard();
        double[] originalValues = blackboard.getValuesSnapshot();
        blackboard.resetValues();
        double[] resetValues = blackboard.getValuesSnapshot();
        assertArraysHaveDifferentValues(originalValues, resetValues);
    }

    @Test
    void shuffle() {
        Blackboard blackboard = new Blackboard();
        double[] originalValues = blackboard.getValuesSnapshot();
        blackboard.shuffle();
        double[] shuffledValues = blackboard.getValuesSnapshot();
        assertArraysHaveDifferentValues(originalValues, shuffledValues);
    }

    @Test
    void nextValue() {
        Blackboard blackboard = new Blackboard();
        int valuesCount = blackboard.getValuesSnapshot().length;
        for (int i = 0; i < 6; i++) {
            double value = blackboard.nextValue();
            assertEquals(valuesCount - (i + 1), blackboard.getValuesSnapshot().length);
        }
        assertThrows(IllegalStateException.class, blackboard::nextValue);
    }

    private void assertArraysHaveDifferentValues(double[] original, double[] changed) {

        assertTrue(original.length == changed.length, "Both arrays should be same length");

        int exactMatches = 0;
        for (int i = 0; i < original.length; i++) {
            if (original[i] == changed[i]) {
                exactMatches++;
            }
        }
        assertTrue(exactMatches != original.length, "All values matched");
    }

}