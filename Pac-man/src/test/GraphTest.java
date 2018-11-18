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
	
	private Graph<Integer, Integer> graph;
	private Graph<Integer, Integer> r;
	private Graph<Integer, Integer> l;

	@SuppressWarnings("rawtypes")
	void setupStage1() {
		Edge<Integer> e1 =new Edge<Integer>(5, 15, 10);
		
		graph = new Graph<Integer, Integer>(true);
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
		Graph<Integer, Integer> graph = new Graph<>(false);
		graph.addEdge(13, 13, 8, 8, 5);
		graph.addEdge(13, 13, 5, 5, 4);
		graph.addEdge(13, 13, 21, 21, 2);
		graph.addEdge(9, 9, 8, 8, 6);
		graph.addEdge(9, 9, 21, 21, 5);
		graph.addEdge(5, 5, 21, 21, 1);
		Graph<Integer, Integer> l = (Graph<Integer, Integer>) graph.MST();
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
	@Test
	void testShortesPath() {
		setupStage2();
		List path =graph.shortesPath(7, 11);
		assertTrue((int)path.get(0)==8);
	}
	
	@Test
	void testShortestPathWeight() {
		setupStage2();
		int weight =graph.shortesPathWeight(7, 11);
		assertTrue(weight==12);
	}
	@Test
	void testIsConnect() {
		setupStage2();
		assertFalse(graph.isConnected());
	}
	@Test
	void testRemoveEdge() {
		setupStage2();
		int lastOneNum =graph.getNumberOfEdges();

		graph.removeEdge(7, 10);
		graph.removeEdge(9, 13);
		graph.removeEdge(11, 12);
		
		int nextNum =graph.getNumberOfEdges();
		
		assertTrue(lastOneNum-2==nextNum);

	}
	@Test
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
	@Test
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
	@Test
	void testMST() {
		setupStage3();
		setupStage4();
		
		assertTrue(r.equals(l));
	}
	
}
