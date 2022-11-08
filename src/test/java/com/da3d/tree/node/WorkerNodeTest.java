package com.da3d.tree.node;

import com.da3d.tree.Blackboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WorkerNodeTest {


    protected Blackboard getMinValuesBlackboard() {
        return new Blackboard(new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
    }

    protected Blackboard getMaxValuesBlackboard() {
        return new Blackboard(new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0});
    }

    @Test
    void executeTickExpectFailWithMinimumValue() {
        WorkerNode node = new TestWorkerNode(0.0, getMinValuesBlackboard());
        assertEquals(NodeStatus.FAIL, node.tick());
    }

    @Test
    void executeTickExpectFailWithMaximumValue() {
        WorkerNode node = new TestWorkerNode(1.0, getMaxValuesBlackboard());
        assertEquals(NodeStatus.FAIL, node.tick());
    }

    @Test
    void executeTickExpectSuccess() {
        assertExpectedResult(NodeStatus.SUCCESS, getMinValuesBlackboard());
    }

    @Test
    void executeTickExpectFailure() {
        assertExpectedResult(NodeStatus.FAIL, getMaxValuesBlackboard());
    }

    @Test
    void executeTickOnRandomBlackboardValues() {
        Blackboard blackboard = new Blackboard();
        WorkerNode node = new WorkerNode(blackboard);

        for (int i = 0; i < 6; i++) {
            NodeStatus status = node.tick();
            assertNotEquals(status, NodeStatus.IDLE);
        }
    }

    void assertExpectedResult(NodeStatus expectedResult, Blackboard blackboard) {
        WorkerNode node = new WorkerNode(blackboard);
        for (int i = 0; i < 6; i++) {
            assertEquals(expectedResult, node.tick());
        }
    }

}