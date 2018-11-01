package graphTAD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph<K extends Comparable<K>, V> {

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
	private boolean directional;
	// representation
	private int representation;

	public Graph(boolean directional, int representation) {
		adjacencyArray = new Hashtable<>();
		adjacencyList = new Hashtable<>();
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
		if (!directional) {
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
		
		if (!directional) {
			if(adjacencyList.get(key2) == null) {
				adjacencyList.put(key2, new ArrayList<K>());
			}
			adjacencyList.get(key2).add(key1);
		}
		
	}
	
	private Hashtable<K, Integer> BFS(K root) {
		
		Hashtable<K, Integer> levels = new Hashtable<>();
		
		return representation == 1? BFSR1(levels,root):BFSR2(levels,root);
	}

	private Hashtable<K, Integer> BFSR2(Hashtable<K, Integer> levels, K root) {
		Queue<K> cola = new LinkedList<>();
        cola.add(root);
        levels.put(root, 1);
        while(cola.peek() != null){
            K actual = cola.poll();
            for(int i = 0; i < adjacencyList.get(actual).size(); i++){
                if(!levels.contains(adjacencyList.get(actual).get(i))){
                    cola.add(adjacencyList.get(actual).get(i));
                    levels.put(adjacencyList.get(actual).get(i),levels.get(actual) + 1);
                }
            }
            
        }
		return levels;
	}

	private Hashtable<K, Integer> BFSR1(Hashtable<K, Integer> levels, K root) {
		Queue<K> cola = new LinkedList<>();
        cola.add(root);
        levels.put(root, 1);
        while(cola.peek() != null){
        	K actual = cola.poll();
        	ArrayList<K> list = Collections.list(adjacencyArray.get(actual).keys());
            for(int i = 0; i < list.size(); i++){
                if(adjacencyArray.get(actual).get(list.get(i)) != 0 && !levels.contains(list.get(i))){
                    cola.add(list.get(i));
                    levels.put(list.get(i),levels.get(list.get(i)) + 1);
                }
            }
        }
		return levels;
	}

}
