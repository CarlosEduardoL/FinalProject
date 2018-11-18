package graphTAD;

import java.util.Hashtable;

public class DisjointSet<K extends Comparable<K>> {
	
	private Hashtable<K, K> representants;
	
	public void makeGroup(K key) {
		representants.put(key, key);
	}
	
	public void union(K key1, K key2) {
		representants.put(key1, representants.get(key2));
	}
	
	public K representant(K key) {
		K temp = key;
		while (!representants.get(temp).equals(temp)) {
			representants.put(temp, representants.get(representants.get(temp)));
			temp = representant(temp);
		}
		return temp;
	}

}
