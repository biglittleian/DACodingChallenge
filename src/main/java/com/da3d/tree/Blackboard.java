package com.da3d.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * Blackboard holds values that the Worker Node fetches and removes from the pool. The blackboard can also
 * shuffle the remaining values in a thread safe manner.
 */
public class Blackboard {

    private static final int VALUES_COUNT = 6;
    private static Random rand = new Random();
    protected double[] values = {0.0};

    public Blackboard() {
        resetValues();
    }

    // used for testing
    public Blackboard(double[] values) {
        this.values = values;
    }

    /**
     * snapshot of values at time of method call
     *
     * @return copy of internal values
     */
    public double[] getValuesSnapshot() {
        return Arrays.copyOf(values, values.length);
    }

    /**
     * Completely reset all values and restore array size to max size
     */
    public void resetValues() {
        synchronized (values) {
            values = rand.doubles(VALUES_COUNT, 0, 1)
                    .toArray();
        }
    }

    /**
     * Shuffle the values in the array in a random order.
     */
    public void shuffle() {
        synchronized (values) {
            if (values.length == 1) return;
            for (int i = 0; i < values.length; i++) {
                int newIndex = rand.nextInt(values.length);
                double value = values[i];
                values[i] = values[newIndex];
                values[newIndex] = value;
            }
            // for debugging
            // System.out.println("- - Blackboard shuffled.  values" + Arrays.toString(values));
        }
    }

    /**
     * Used to fetch a new value. new value is removed from pool of values
     *
     * @return new value
     */
    public double nextValue() {

        synchronized (values) {
            if (values.length == 0) {
                throw new IllegalStateException("No more numbers in the blackboard");
            }
            double value = values[0];
            values = Arrays.copyOfRange(values, 1, values.length);
            return value;
        }
    }

}
