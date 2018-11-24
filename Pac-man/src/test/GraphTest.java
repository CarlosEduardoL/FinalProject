/**
 * 
 */
package test;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import graphTAD.Edge;
import graphTAD.Graph;

/**
 * @author Nelson Lopez
 *
 */
public class GraphTest {
	
	private Graph graph;
	@SuppressWarnings("rawtypes")
	void setupStage1() {
		Edge e1 =new Edge(5, 15, 10);
		
		graph = new Graph(false);
		graph.addEdge(7, 5, 8, 15, 10);
		graph.addEdge(8, 15, 9, 1, 1);
		graph.addEdge(7, 5, 10, 8, 15);

		
	}
	

	@Test
	void testAddNode() {
		setupStage1();
		int lastOneNum =graph.getNumberOfEdges();
		
		graph.addEdge(8, 15, 11, 4, 2);
		graph.addEdge(11, 4, 10, 8, 3);
		graph.addEdge(10, 8, 12, 6, 6);
		graph.addEdge(11, 4, 12, 6, 10);
		graph.addEdge(11, 4, 13, 9, 4);
		graph.addEdge(9, 1, 13, 9, 8);
		graph.addEdge(12, 6, 13, 9, 2);
		
	}
}