package graphTAD;

import java.util.Hashtable;
import java.util.List;

public class Node<K extends Comparable<K>, V> {
	
	private K key;
	private V value;
	private List<K> adjacents;
	private Hashtable<K, Integer> weights;
	
	public Node(K key, V value) {
		super();
		this.key = key;
		this.value = value;
		
	}
	
	public K getKey() {
		return key;
	}
	public V getValue() {
		return value;
	}
	public List<K> getAdjacents() {
		return adjacents;
	}
	
	public Hashtable<K, Integer> getWeights() {
		return weights;
	}

	public void addAdjacent(K key, int weight) {
		adjacents.add(key);
		weights.put(key, weight);
	}
	
	

}
