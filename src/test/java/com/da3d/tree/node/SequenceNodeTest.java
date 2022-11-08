package com.da3d.tree.node;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.da3d.tree.node.NodeStatus.FAIL;
import static com.da3d.tree.node.NodeStatus.SUCCESS;
import static com.da3d.tree.node.TestNode.FAIL_NODE;
import static com.da3d.tree.node.TestNode.SUCCESS_NODE;
import static org.junit.jupiter.api.Assertions.*;

class SequenceNodeTest {

    @Test
    void executeTickExpectSuccess() {
        assertResult(SUCCESS, SUCCESS_NODE);
        assertResult(SUCCESS, SUCCESS_NODE, SUCCESS_NODE);
        assertResult(SUCCESS, SUCCESS_NODE, SUCCESS_NODE, SUCCESS_NODE);
    }

    @Test
    void executeTickExpectFailure() {
        assertResult(FAIL, FAIL_NODE);
        assertResult(FAIL, FAIL_NODE, FAIL_NODE);
        assertResult(FAIL, FAIL_NODE, FAIL_NODE, FAIL_NODE);
        assertResult(FAIL, SUCCESS_NODE, FAIL_NODE);
        assertResult(FAIL, SUCCESS_NODE, SUCCESS_NODE, FAIL_NODE);
        assertResult(FAIL, FAIL_NODE,SUCCESS_NODE);
    }

    void assertResult(NodeStatus expected, Node... children) {
        SequenceNode snode = new SequenceNode();
        Arrays.stream(children).forEach(snode::addChild);

        NodeStatus status = snode.tick();
        assertEquals(expected, status);
    }
}