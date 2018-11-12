package graphTAD;

import java.util.Hashtable;

public interface TheGraph<K extends Comparable<K>, V> {
	
	/**
	 * add a no connected node to the graph
	 * @param key
	 * @param value
	 */
	public void addNode(K key,V value);
	/**
	 * make an edge between two nodes, if the nodes does not exist add there
	 * @param key1
	 * @param value1
	 * @param key2
	 * @param value2
	 */
	public void addEdge(K key1, V value1, K key2, V value2);
	public void addEdge(K key1, V value1, K key2, V value2, int weight);
	public Hashtable<K, Integer> BFS(K root);
	public Hashtable<K, Integer> DFS(K root);
	public boolean isConnected();
	
}
