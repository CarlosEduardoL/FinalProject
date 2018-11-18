/**
 * 
 */
package test;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import graphTAD.Edge;
import graphTAD.Graph;

/**
 * @author Nelson Lopez
 *
 */
public class GraphTest {
	
	private Graph graph;
	private Graph r;
	private Graph l;

	@SuppressWarnings("rawtypes")
	void setupStage1() {
		Edge e1 =new Edge(5, 15, 10);
		
		graph = new Graph(true);
		graph.addEdge(7, 5, 8, 15, 10);
		graph.addEdge(8, 15, 9, 1, 1);
		graph.addEdge(7, 5, 10, 8, 15);

		
		
	}
	
	void setupStage2() {
		Edge e1 =new Edge(5, 15, 10);
		
		graph = new Graph(true);
		graph.addEdge(7, 5, 8, 15, 10);
		graph.addEdge(8, 15, 9, 1, 1);
		graph.addEdge(7, 5, 10, 8, 15);
		graph.addEdge(8, 15, 11, 4, 2);
		graph.addEdge(11, 4, 10, 8, 3);
		graph.addEdge(10, 8, 12, 6, 6);

	}
	
	void setupStage3() {
		Graph<Integer, Integer> g = new Graph<>(false);
		g.addEdge(13, 13, 8, 8, 5);
		g.addEdge(13, 13, 5, 5, 4);
		g.addEdge(13, 13, 21, 21, 2);
		g.addEdge(9, 9, 8, 8, 6);
		g.addEdge(9, 9, 21, 21, 5);
		g.addEdge(5, 5, 21, 21, 1);
		Graph<Integer, Integer> l = (Graph<Integer, Integer>) g.MST();
	}
	
	void setupStage4() {
		Graph<Integer, Integer> r = new Graph<>(false);
		r.addEdge(13, 13, 8, 8, 5);
		r.addEdge(13, 13, 21, 21, 2);
		r.addEdge(9, 9, 21, 21, 5);
		r.addEdge(5, 5, 21, 21, 1);
	}
	

	@Test
	void testAddEdge() {
		setupStage1();
		int lastOneNum =graph.getNumberOfEdges();
		
		graph.addEdge(8, 15, 11, 4, 2);
		graph.addEdge(11, 4, 10, 8, 3);
		graph.addEdge(10, 8, 12, 6, 6);
		graph.addEdge(11, 4, 12, 6, 10);
		graph.addEdge(11, 4, 13, 9, 4);
		graph.addEdge(9, 1, 13, 9, 8);
		graph.addEdge(12, 6, 13, 9, 2);
		
		int nextNum =graph.getNumberOfEdges();
		
		System.out.println(nextNum);
		assertTrue(lastOneNum+7==nextNum);
		
		
	}
	
	void testShortesPath() {
		List path =graph.shortesPath(7, 11);
		assertTrue((int)path.get(0)==8);
	}
	
	
	void testShortestPathWeight() {
		int weight =graph.shortesPathWeight(7, 11);
		assertTrue(weight==12);
	}
	
	void testIsConnect() {
		assertFalse(graph.isConnected());
	}
	
	void testRemoveEdge() {
		int lastOneNum =graph.getNumberOfEdges();

		graph.removeEdge(7, 10);
		graph.removeEdge(9, 13);
		graph.removeEdge(11, 12);
		
		int nextNum =graph.getNumberOfEdges();
		
		assertTrue(lastOneNum-2==nextNum);

	}
	
	void testRemoveNode() {
		setupStage4();
		int lastOneNum =graph.getNumberOfNodes();
		
		r.removeNode(13);
		r.removeNode(8);
		r.removeNode(9);
		r.removeNode(21);		
		
		int nextNum =graph.getNumberOfNodes();
		assertTrue(lastOneNum-4==nextNum);


	}
	
	void testAddNodes() {
		setupStage4();
		int lastOneNum =graph.getNumberOfNodes();
		
		r.addEdge(100, 9, 99, 21, 5);
		r.addEdge(88, 5, 85, 21, 1);		
		r.addEdge(70, 9, 60, 21, 5);
		r.addEdge(51, 5, 53, 21, 1);

		int nextNum =graph.getNumberOfNodes();
		assertTrue(lastOneNum+4==nextNum);
	}
	
	void testMST() {
		setupStage3();
		setupStage4();
		
		assertTrue(r==l);
	}
	
}
