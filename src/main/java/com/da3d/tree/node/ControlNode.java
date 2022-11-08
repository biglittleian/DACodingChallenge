package com.da3d.tree.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to represent Control Nodes which contain child nodes.
 */
public abstract class ControlNode extends Node{

    private List<Node> children;

    public ControlNode() {
        this.children = new ArrayList<>();
    }

    public ControlNode addChild(Node child) {
        children.add(child);
        return this;
    }

    public List<Node> getChildren() {
        return children;
    }

}
