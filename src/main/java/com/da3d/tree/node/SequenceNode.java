package com.da3d.tree.node;

/**
 * Sequence Node acts like an AND gate, so the first FAIL will stop any further child nodes to be ticked, and FAIL to
 * be returned. Only if all child nodes return SUCCESS will this node return SUCCESS.
 */
public class SequenceNode extends ControlNode {

    public SequenceNode() {
        super();
    }

    @Override
    public String getNodeId() {
        return "SEQUENCE"+super.getNodeId();
    }
    @Override
    protected NodeStatus executeTick() {
        for(Node node : getChildren()) {
            NodeStatus status = node.tick();
            if (status == NodeStatus.FAIL)
                return NodeStatus.FAIL;
        }

        return NodeStatus.SUCCESS;
    }

    @Override
    public String toString() {
        return getNodeId()+" children:"+ getChildren().size()+ " status: "+ getNodeStatus();
    }
}
