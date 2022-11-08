package com.da3d;

import com.da3d.tree.Blackboard;
import com.da3d.tree.node.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class runs the simulation.  It builds the solution tree, and executes two jobs asynchonously:
 *  - script to shuffle the blackboard values every 2 milliseconds
 *  - script to execute the root node of the tree and return a final value
 *
 *  Call `execute()` to kick off a simulation.
 */
public class Simulation {

    Blackboard blackboard;

    public Simulation(Blackboard blackboard) {
        this.blackboard = blackboard;
    }

    private Node createTree() {

        //                                |- worker
        //                   |- sequence -|
        //                   |            |- worker
        //  root (fallback) -|
        //                   |
        //                   | ... x3
        //
        Node root = new FallbackNode()
                .addChild(
                        new SequenceNode()
                                .addChild(new WorkerNode(blackboard))
                                .addChild(new WorkerNode(blackboard))
                )
                .addChild(
                        new SequenceNode()
                                .addChild(new WorkerNode(blackboard))
                                .addChild(new WorkerNode(blackboard))
                )
                .addChild(
                        new SequenceNode()
                                .addChild(new WorkerNode(blackboard))
                                .addChild(new WorkerNode(blackboard))
                );

        return root;

    }

    public NodeStatus execute() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            final AtomicBoolean complete = new AtomicBoolean(false);

            Callable<Boolean> blackboardCallable = () -> {
                while (!complete.get()) {
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    blackboard.shuffle();
                }
                return true;
            };
            executor.submit(blackboardCallable);

            Callable<NodeStatus> decisionTreeCallable = () -> {
                Node root = createTree();
                System.out.println("\nSTART ==========\n");
                NodeStatus status = root.tick();
                System.out.println("\n============ END Final Status: '"+status+"'\n");


                return status;
            };

            Future<NodeStatus> futureResult = executor.submit(decisionTreeCallable);

            NodeStatus status = futureResult.get();
            complete.set(true);
            // shutdown the executor service.
            executor.awaitTermination(0, TimeUnit.MILLISECONDS);

            return status;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }


}
