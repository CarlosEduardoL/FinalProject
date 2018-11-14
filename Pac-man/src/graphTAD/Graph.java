package graphTAD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph<K extends Comparable<K>, V> implements TheGraph<K, V>{

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
	private Hashtable<K, V> nodes;
	// type of graph
	private boolean directed;
	// representation
	private int representation;


	//---------------------------//
	//--------Constructor--------//
	//---------------------------//

	public Graph(boolean directed, int representation) {
		adjacencyArray = new Hashtable<>();
		adjacencyList = new Hashtable<>();
		nodes = new Hashtable<>();
		this.directed = directed;
	}

	//----------------------------//
	//---------Methods------------//
	//----------------------------//

	/**
	 * add not connected node to the graph
	 * @param key - the key of the new node
	 * @param value - the value of the new node 
	 */
	public void addNode(K key,V value) {
		if (!nodes.contains(key)) {
			nodes.put(key, value);
		}
	}


	public void addEdge(K key1, V value1, K key2, V value2) {
		addEdge(key1, value1, key2, value2, 1);
	}

	public void addEdge(K key1, V value1, K key2, V value2, int weight) {


		addNode(key1, value1);
		addNode(key2, value2);


		addEdgeR1(key1, key2);
		addEdgeR2(key1, key2);

	}

	private void addEdgeR1(K key1, K key2) {
		//-------------------------------------------//
		//----------- Representation 1 --------------//
		//-------------------------------------------//

		// check if the row in the key2 exist
		if (adjacencyArray.get(key1) == null) {
			adjacencyArray.put(key1, new Hashtable<K,Integer>());
		}
		Integer adjacents = adjacencyArray.get(key1).get(key2) != null? adjacencyArray.get(key1).get(key2) + 1 : 0;
		adjacencyArray.get(key1).put(key2,  adjacents);
		if (!directed) {
			if (adjacencyArray.get(key2) == null) {
				adjacencyArray.put(key2, new Hashtable<K,Integer>());
			}
			adjacents = adjacencyArray.get(key2).get(key1) != null? adjacencyArray.get(key2).get(key1) + 1 : 0;
			adjacencyArray.get(key2).put(key1,  adjacents);
		}
	}

	private void addEdgeR2(K key1, K key2)  {
		//-------------------------------------------//
		//----------- Representation 2 --------------//
		//-------------------------------------------//
		if(adjacencyList.get(key1) == null) {
			adjacencyList.put(key1, new ArrayList<K>());
		}
		adjacencyList.get(key1).add(key2);

		if (!directed) {
			if(adjacencyList.get(key2) == null) {
				adjacencyList.put(key2, new ArrayList<K>());
			}
			adjacencyList.get(key2).add(key1);
		}

	}

	public Hashtable<K, Integer> BFS(K root) {

		Hashtable<K, Integer> levels = new Hashtable<>();

		return representation == 1? BFSR1(levels,root):BFSR2(levels,root);
	}

	private Hashtable<K, Integer> BFSR2(Hashtable<K, Integer> levels, K root) {
		Queue<K> queue = new LinkedList<>();
		queue.add(root);
		levels.put(root, 1);
		while(queue.peek() != null){
			K actual = queue.poll();
			for(int i = 0; i < adjacencyList.get(actual).size(); i++){
				if(!levels.contains(adjacencyList.get(actual).get(i))){
					queue.add(adjacencyList.get(actual).get(i));
					levels.put(adjacencyList.get(actual).get(i),levels.get(actual) + 1);
				}
			}

		}
		return levels;
	}

	private Hashtable<K, Integer> BFSR1(Hashtable<K, Integer> levels, K root) {
		Queue<K> queue = new LinkedList<>();
		queue.add(root);
		levels.put(root, 1);
		while(queue.peek() != null){
			K actual = queue.poll();
			ArrayList<K> list = Collections.list(adjacencyArray.get(actual).keys());
			for(int i = 0; i < list.size(); i++){
				if(adjacencyArray.get(actual).get(list.get(i)) != 0 && !levels.contains(list.get(i))){
					queue.add(list.get(i));
					levels.put(list.get(i),levels.get(list.get(i)) + 1);
				}
			}
		}
		return levels;
	}

	public boolean isConnected() {
		boolean connected = true;
		Hashtable<K, Integer> levels = BFS(nodes.keys().nextElement());
		
		for(K key : Collections.list(nodes.keys())) {
			connected &= levels.containsKey(key);
		}

		return connected;
	}

	@Override
	public Hashtable<K, Integer> DFS(K root) {
		// TODO Auto-generated method stub
		return null;
	}

}
