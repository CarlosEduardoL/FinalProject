package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import graphTAD.Graph;
import graphTAD.GraphV2;

/**
 * @author Nelson Lopez
 *
 */

class GraphV2Test {
	
	private GraphV2<Integer, Integer> graph;
	private GraphV2<Integer, Integer> mst;
	
	@SuppressWarnings("rawtypes")
	void setupStage1() {
		
		graph = new GraphV2<>(true);
		graph.addEdge(1, 1, 2, 2, 84);
		graph.addEdge(2, 2, 3, 3, 130);
		graph.addEdge(2, 2, 6, 6, 268);
		graph.addEdge(3, 3, 4, 4, 95);
		graph.addEdge(3, 3, 10, 10, 176);
		graph.addEdge(4, 4, 5, 5, 64);
		graph.addEdge(5, 5, 6, 6, 92);
		graph.addEdge(5, 5, 7, 7, 399);
		graph.addEdge(6, 6, 10, 10, 224);
		graph.addEdge(6, 6, 8, 8, 85);
		graph.addEdge(7, 7, 8, 8, 257);
		graph.addEdge(8, 8, 9, 9, 98);
		graph.addEdge(9, 9, 10, 10, 245);

	}
	
	void setupStage2() {
		
		graph = new GraphV2<>(false);
		graph.addEdge(1, 1, 2, 2, 84);
		graph.addEdge(2, 2, 3, 3, 130);
		graph.addEdge(2, 2, 6, 6, 268);
		graph.addEdge(3, 3, 4, 4, 95);
		graph.addEdge(3, 3, 10, 10, 176);
		graph.addEdge(4, 4, 5, 5, 64);
		graph.addEdge(5, 5, 6, 6, 92);
		graph.addEdge(5, 5, 7, 7, 399);
		graph.addEdge(6, 6, 10, 10, 224);
		graph.addEdge(6, 6, 8, 8, 85);
		graph.addEdge(7, 7, 8, 8, 257);
		graph.addEdge(8, 8, 9, 9, 98);
		graph.addEdge(9, 9, 10, 10, 245);

	}
	
	@Test
	void testAddNode() {
		setupStage1();
		
		int lastOneNum =graph.getNodes().size();
		
		graph.addNode(11, 11);
		graph.addNode(13, 13);
		graph.addNode(14, 14);
		graph.addNode(15, 15);
		graph.addNode(16, 16);
		graph.addNode(17, 17);
		
		int afterNum =graph.getNodes().size();
		assertTrue(lastOneNum+6==afterNum);

	}
	
	@Test
	void testAddEdge() {
		setupStage1();
		int lastOneNum =graph.getEdges().size();
		
		graph.addEdge(8, 8, 11, 11, 2);
		graph.addEdge(11, 11, 10, 10, 3);
		graph.addEdge(10, 10, 8, 8, 6);
		graph.addEdge(11, 11, 4, 4, 4);
		graph.addEdge(9, 9, 11, 11, 8);
		graph.addEdge(12, 12, 9, 9, 2);
		
		int afterNum =graph.getEdges().size();
		assertTrue(lastOneNum+6==afterNum);
	}
	
	
	/**
	 * This method tests the method addEdge() which receive just the parameters K1, V1, K2, V2 without weight
	 */
	@Test
	void testAddEdgeB() {
		setupStage1();
		int lastOneNum =graph.getEdges().size();
		
		graph.addEdge(8, 8, 11, 11);
		graph.addEdge(11, 11, 10, 10);
		graph.addEdge(10, 10, 8, 8);
		graph.addEdge(11, 11, 4, 4);
		graph.addEdge(9, 9, 11, 11);
		graph.addEdge(12, 12, 9, 9);
		
		int afterNum =graph.getEdges().size();
		assertTrue(lastOneNum+6==afterNum);
	}

}
