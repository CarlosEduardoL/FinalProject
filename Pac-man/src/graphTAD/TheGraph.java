package graphTAD;

import java.util.List;

public interface TheGraph<K extends Comparable<K>, V> {
	
	/**
	 * add a no connected node to the graph
	 * @param key - the identification of the node
	 * @param value - data that the node contains
	 */
	public void addNode(K key,V value);
	
	/**
	 * make an edge between two nodes, if the nodes does not exist add there</br>
	 * @param keyFrom - the identification of the node where the edge begins
	 * @param valueFrom - the data of the node where the edge begins
	 * @param keyTo - the identification of the node where the edge ends
	 * @param valueTo - the data of the node where the edge ends
	 */
	public void addEdge(K keyFrom, V valueFrom, K keyTo, V valueTo);
	
	/**
	 * make an edge between two nodes whit weight, if the nodes does not exist add there</br>
	 * @param keyFrom - the identification of the node where the edge begins
	 * @param valueFrom - the data of the node where the edge begins
	 * @param keyTo - the identification of the node where the edge ends
	 * @param valueTo - the data of the node where the edge ends
	 * @param weight - the weight of the edge
	 */
	public void addEdge(K keyFrom, V valueFrom, K keyTo, V valueTo, int weight);
	
	/**
	 * returns a list with the nodes in the order that must be traveled to get</br>
	 * from node A to node B in the shortest path</br>
	 * @return
	 */
	public List<V> shortesPath(K key1, K key2);
	
	
	/**
	 * check if the graph is connected
	 * @return
	 */
	public boolean isConnected();
	
	/**
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 */
	public int shortesPathWeight(K key1, K key2);
	
	/**
	 * 
	 * @return
	 */
	public TheGraph<K, V> MST();
	
	/**
	 * 
	 * @param key1
	 * @param key2
	 */
	public void removeEdge(K from, K to);
	
}
