package com.da3d.tree.node;

/**
 * Node specifically for testing
 */
public class TestNode extends Node {

    public static final TestNode SUCCESS_NODE = new TestNode(NodeStatus.SUCCESS);
    public static final TestNode FAIL_NODE = new TestNode(NodeStatus.FAIL);
    NodeStatus status;

    public TestNode(NodeStatus status) {
        this.status = status;
    }

    @Override
    protected NodeStatus executeTick() {
        return status;
    }

}
