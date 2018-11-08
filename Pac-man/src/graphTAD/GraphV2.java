package graphTAD;

import java.util.ArrayList;
import java.util.Hashtable;

public class GraphV2<K extends Comparable<K>, V> {

	//-------------------------//
	//-------Constants---------//
	//-------------------------//
	public static final int ADJACENCY_ARRAY = 1;
	public static final int ADJACENCY_LIST = 2;

	//-------------------------//
	//-------Parameters--------//
	//-------------------------//

	// Representation 1
	private Hashtable<K, Hashtable<K, Integer>> adjacencyArray;
	// Representation 2
	private Hashtable<K,ArrayList<K>> adjacencyList;
	// Nodes
	private Hashtable<K, Node<K,V>> nodes;
	// type of graph
	private boolean directed;
	// representation
	private int representation;

	//---------------------------//
	//--------Constructor--------//
	//---------------------------//

	public GraphV2(boolean directed, int representation) {
		adjacencyArray = new Hashtable<>();
		adjacencyList = new Hashtable<>();
		nodes = new Hashtable<>();
		this.directed = directed;
	}
}
