package com.da3d;

import com.da3d.tree.Blackboard;
import com.da3d.tree.node.NodeStatus;

/**
 * This is the point of execution for this simulation. It creates a Blackboard and a Simulation object,
 * then executes the simulation
 */
public class BehaviorTreeApplication {

	public static void main(String[] args) {
		Simulation simulation = new Simulation(new Blackboard());

		NodeStatus status = simulation.execute();

		System.exit(0);
	}

}
