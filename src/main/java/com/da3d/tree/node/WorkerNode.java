package com.da3d.tree.node;

import com.da3d.tree.Blackboard;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Worker Node simulated work by comparing a randomly generated value with a value from the Blackboard, and return
 * SUCCESS if it's number if larger than the blackboard number, otherwise returns FAIL.
 */
public class WorkerNode extends Node {

    private static Random RANDOM = new Random();
    private Blackboard blackboard;

    protected double currentValue;

    public WorkerNode(Blackboard blackboard) {
        this.blackboard = blackboard;
    }

    @Override
    public String getNodeId() {
        return "WORKER"+super.getNodeId();
    }

    @Override
    public NodeStatus executeTick() {
        regenerateValue();
        double currentValue = getCurrentValue();
        double blackboardValue = blackboard.nextValue();
        System.out.println(String.format("--- worker %s : value %f, blackboard value: %f", getNodeId(), currentValue, blackboardValue));
        NodeStatus nodeStatus = currentValue > blackboardValue ?
                NodeStatus.SUCCESS : NodeStatus.FAIL;

        return nodeStatus;
    }

    protected void regenerateValue() {
        currentValue = RANDOM.nextDouble(0,1);
    }

    public double getCurrentValue() {
        return currentValue;
    }

    @Override
    public String toString() {
        return "WORKER node "+getNodeId()+":  "+ getNodeStatus();
    }
}
