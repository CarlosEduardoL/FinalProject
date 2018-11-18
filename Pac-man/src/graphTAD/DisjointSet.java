package graphTAD;

import java.util.Hashtable;

public class DisjointSet<K extends Comparable<K>> {
	
	private Hashtable<K, K> representants;
	
	public DisjointSet() {
		representants = new Hashtable<>();
	}
	
	public void makeGroup(K key) {
		if (representants.get(key) == null) {
			representants.put(key, key);
		}
	}
	
	public void union(K key1, K key2) {
		representants.put(representant(key2), representant(key1));
	}
	
	public K representant(K key) {
//		K temp = key;
		while (!representants.get(key).equals(key)) {
			representants.put(key, representants.get(representants.get(key)));
			key = representants.get(key);
		}
		return key;
	}

}
