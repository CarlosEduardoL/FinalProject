/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import graphTAD.*;

/**
 * @author Nelson Lopez
 *
 */
public class GraphTest {
	
	private Graph<Integer, Integer> graph;
	private Graph<Integer, Integer> mst;

	@SuppressWarnings("rawtypes")
	void setupStage1() {
		
		graph = new Graph<>(true);
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
		
		graph = new Graph<>(false);
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
		int lastOneNum =graph.getNumberOfNodes();
		
		graph.addNode(11, 11);
		graph.addNode(13, 13);
		graph.addNode(14, 14);
		graph.addNode(15, 15);
		graph.addNode(16, 16);
		graph.addNode(17, 17);
		
		int afterNum =graph.getNumberOfNodes();
		assertTrue(lastOneNum+6==afterNum);
		
	}
	
	@Test
	void testAddEdge() {
		setupStage1();
		int lastOneNum =graph.getNumberOfEdges();
		
		graph.addEdge(8, 8, 11, 11, 2);
		graph.addEdge(11, 11, 10, 10, 3);
		graph.addEdge(10, 10, 8, 8, 6);
		graph.addEdge(11, 11, 4, 4, 4);
		graph.addEdge(9, 9, 11, 11, 8);
		graph.addEdge(12, 12, 9, 9, 2);
		
		int afterNum =graph.getNumberOfEdges();
		assertTrue(lastOneNum+6==afterNum);
	}
	
	
	/**
	 * This method tests the method addEdge() which receive just the parameters K1, V1, K2, V2 without weight
	 */
	@Test
	void testAddEdgeB() {
		setupStage1();
		int lastOneNum =graph.getNumberOfEdges();
		
		graph.addEdge(8, 8, 11, 11);
		graph.addEdge(11, 11, 10, 10);
		graph.addEdge(10, 10, 8, 8);
		graph.addEdge(11, 11, 4, 4);
		graph.addEdge(9, 9, 11, 11);
		graph.addEdge(12, 12, 9, 9);
		
		int afterNum =graph.getNumberOfEdges();
		assertTrue(lastOneNum+6==afterNum);
	}
	
	
	@Test
	void testShortestPath() {
		setupStage2();
		List<Integer> prueba =new ArrayList<Integer>();
		List<Integer> pruebaB =new ArrayList<Integer>();

		
		prueba.add(1);
		prueba.add(2);
		prueba.add(6);
		prueba.add(8);

		List<Integer> list = graph.shortesPath(1, 8);
		
		assertEquals(prueba, list);
		
		graph.addEdge(10, 10, 11, 11, 45);
		graph.addEdge(11, 11, 12, 12, 50);
		graph.addEdge(10, 10, 12, 12, 150);

		list = graph.shortesPath(1, 12);
		
		pruebaB.add(1);
		pruebaB.add(2);
		pruebaB.add(3);
		pruebaB.add(10);
		pruebaB.add(11);
		pruebaB.add(12);

		assertEquals(pruebaB, list);
	}
	
	@Test
	void testIsConnected() {
		setupStage2();
		
		graph.addEdge(12, 12, 13, 13, 45);
		graph.addEdge(13, 13, 14, 14, 50);
		
		assertFalse(graph.isConnected());
		
		graph.addEdge(10, 10, 11, 11, 45);
		graph.addEdge(11, 11, 12, 12, 50);


	}
	
	@Test
	void testShortestPathWeight() {
		setupStage2();

		int peso = graph.shortesPathWeight(1, 10);
		assertTrue(peso==390);
		
		graph.addEdge(10, 10, 11, 11, 45);
		graph.addEdge(11, 11, 12, 12, 50);
		graph.addEdge(10, 10, 12, 12, 150);

		int pesoPost = graph.shortesPathWeight(1, 12);
		
		assertTrue(peso+95==pesoPost);
	}
	
	
	@Test
	void testTheGraph() {
		//creo que el problema es que un grafo es dirigido y el otro no y cuando son iguales en ese booleano, las arsitas se duplican uno respecto el otro
		setupStage1();
		mst =(Graph<Integer, Integer>) graph.MST();
		List<Integer> list = Collections.list(mst.adjacencyArray.keys());

		
		Graph<Integer, Integer> prueba =new Graph<>(true);
		prueba.addEdge(1, 1, 2, 2, 84);
		prueba.addEdge(2, 2, 3, 3, 130);
		prueba.addEdge(3, 3, 4, 4, 95);
		prueba.addEdge(4, 4, 5, 5, 64);
		prueba.addEdge(5, 5, 6, 6, 92);
		prueba.addEdge(6, 6, 8, 8, 85);
		prueba.addEdge(7, 7, 8, 8, 257);
		prueba.addEdge(8, 8, 9, 9, 98);
		prueba.addEdge(9, 9, 10, 10, 245);

		assertEquals(mst, prueba);

	}
	
	
	@Test
	void testRemoveEdge() {
		setupStage2();
		int lastOneNum =graph.getNumberOfEdges();
		
		graph.removeEdge(5, 6);
		graph.removeEdge(6, 8);

		int postNum =graph.getNumberOfEdges();

		
		assertTrue(lastOneNum-4==postNum);


	}
	
	@Test
	void testRemoveNodes() {
		setupStage2();
		int lastOneNum =graph.getNumberOfNodes();
		
		graph.removeNode(6);
		graph.removeNode(5);
		graph.removeNode(7);	
		graph.removeNode(8);

		int postNum =graph.getNumberOfNodes();

		
		assertTrue(lastOneNum-2==postNum);


	}
}