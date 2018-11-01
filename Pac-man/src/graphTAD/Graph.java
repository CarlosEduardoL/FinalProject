package graphTAD;

import java.util.ArrayList;
import java.util.Hashtable;

public class Graph<K extends Comparable<K>, V> {

	//-------------------------//
	//-------Constants---------//
	//-------------------------//
	public static final int ADJACENCY_ARRAY = 1;
	public static final int ADJACENCY_LIST = 2;
	public static final int EDGE_ARRAY = 3;

	//-------------------------//
	//-------Parameters--------//
	//-------------------------//

	// Representation 1
	private Hashtable<K, Hashtable<K, Integer>> adjacencyArray;
	// Representation 2
	private Hashtable<Integer, Hashtable<K, Integer>> edgeArray;
	// Representation 3
	private ArrayList<ArrayList<K>> adjacencyList;
	// Nodes
	private Hashtable<K, V> nodes;
	// type of graph
	private boolean directional;
	// representation
	private int representation;

	public Graph(boolean directional, int representation) {
		adjacencyArray = new Hashtable<>();
		edgeArray = new Hashtable<>();
		adjacencyList = new ArrayList<>();
		nodes = new Hashtable<>();
		this.directional = directional;
	}

	public void addNode(K key,V value) {
		nodes.put(key, value);
	}

	public void addEdge(K key1, V value1, K key2, V value2) {

		// check if the node 1 doesn't exist
		if (nodes.get(key1) == null) {
			// if the node 1 doesn't exist so add there
			nodes.put(key1, value1);
		}

		// check if the node 2 doesn't exist
		if (nodes.get(key2) == null) {
			// if the node 2 doesn't exist so add there
			nodes.put(key2, value2);
		}

		//-------------------------------------------//
		//----------- Representation 1 --------------//
		//-------------------------------------------//
		
		// check if the row in the key2 exist
		if (adjacencyArray.get(key1) == null) {
			adjacencyArray.put(key1, new Hashtable<K,Integer>());
		}
		Integer adjacents = adjacencyArray.get(key1).get(key2) != null? adjacencyArray.get(key1).get(key2) + 1 : 0;
		adjacencyArray.get(key1).put(key2,  adjacents);
		if (!directional) {
			if (adjacencyArray.get(key2) == null) {
				adjacencyArray.put(key2, new Hashtable<K,Integer>());
			}
			adjacents = adjacencyArray.get(key2).get(key1) != null? adjacencyArray.get(key2).get(key1) + 1 : 0;
			adjacencyArray.get(key2).put(key1,  adjacents);
		}
		
	}

}
