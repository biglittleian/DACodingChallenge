package com.da3d.tree.node;

/**
 * Fallback node acts like an OR gate. The first SUCCESS will cause any further child nodes from being ticked. If all
 * child nodes return FAIL, then this node will return FAIL.
 */
public class FallbackNode extends ControlNode {

    public FallbackNode() {
        super();
    }

    @Override
    public String getNodeId() {
        return "FALLBACK"+super.getNodeId();
    }


    @Override
    protected NodeStatus executeTick() {
        for(Node node : getChildren()) {
            NodeStatus status = node.tick();
            if (status == NodeStatus.SUCCESS)
                return NodeStatus.SUCCESS;
        }

        return NodeStatus.FAIL;
    }

    @Override
    public String toString() {
        return getNodeId()+" children:"+ getChildren().size()+ " status: "+ getNodeStatus();
    }

}
