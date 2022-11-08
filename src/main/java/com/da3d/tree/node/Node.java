package com.da3d.tree.node;

/**
 * Abstract class that is the base for all nodes.
 */
public abstract class Node {

    public static int NODE_ID_COUNTER = 0;
    private int nodeId;
    private NodeStatus nodeStatus;

    public Node() {
        nodeId = NODE_ID_COUNTER++;
        nodeStatus = NodeStatus.IDLE;
    }

    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public String getNodeId() {
        return ""+nodeId;
    }

    /**
     * public interface for initiating a tick for the node.
     * @return node status after tick
     */
    public NodeStatus tick() {
        try
        {
            // add some delay or the tree is evaluated without
            // the blackboard having a chance to shuffle.
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        nodeStatus = executeTick();
        System.out.println("COMPLETE: "+ this);
        return nodeStatus;
    };

    /**
     * executes the logic associated with a tick
     * @return node status after executing logic
     */
    protected abstract NodeStatus executeTick();

}
