package com.da3d.tree.node;

import com.da3d.tree.Blackboard;

/**
 * Test version of worker node
 */
public class TestWorkerNode extends WorkerNode{

    public TestWorkerNode(double staticValue, Blackboard blackboard) {
        super(blackboard);
        currentValue = staticValue;
    }

    @Override
    protected void regenerateValue() {
        return;
    }
}
