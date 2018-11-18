package graphTAD;

import java.util.List;

public interface TheGraph<K extends Comparable<K>, V> {
	
	/**
	 * add a no connected node to the graph
	 * @param key
	 * @param value
	 */
	public void addNode(K key,V value);
	
	/**
	 * make an edge between two nodes, if the nodes does not exist add there</br>
	 * @param key1
	 * @param value1
	 * @param key2
	 * @param value2
	 */
	public void addEdge(K key1, V value1, K key2, V value2);
	
	/**
	 * make an edge between two nodes whit weight, if the nodes does not exist add there</br>
	 * @param key1
	 * @param value1
	 * @param key2
	 * @param value2
	 * @param weight
	 */
	public void addEdge(K key1, V value1, K key2, V value2, int weight);
	
	/**
	 * 
	 * @return
	 */
	public List<K> shortesPath(K key1, K key2);
	
	
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
	
	/**
	 * 
	 * @param key
	 */
	public void removeNode(K key);
	
}
