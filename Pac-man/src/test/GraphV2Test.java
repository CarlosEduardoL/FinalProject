package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import graphTAD.GraphV2;

class GraphV2Test {
	
	private GraphV2<Integer, Integer> graph;
	
	void setupStage1(){
		graph = new GraphV2<>(false);
	}
	
	
	@Test
	void testAddNode() {
		setupStage1();
		
		graph.addEdge(0, 0, 1, 1);
		graph.addEdge(1, 1, 2, 2);
		graph.addEdge(2, 2, 3, 3);
		graph.addEdge(2, 2, 4, 4);
		graph.addEdge(3, 3, 0, 0);
		
		List<Integer> list = Collections.list(graph.getAdjacencyList().keys());
		for (Integer i : list) {
			System.out.println("Key: " + i+ " value: " + graph.getNodes().get(i).getValue());
			for (int j = 0; j < graph.getAdjacencyList().get(i).size(); j++) {
				System.out.print(graph.getAdjacencyList().get(i).get(j) + " ");
				
			}
			System.out.println("");
		}
	}

}
