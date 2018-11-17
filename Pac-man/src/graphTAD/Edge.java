package graphTAD;

public class Edge<K extends Comparable<K>> {
	
	private K from;
	private K to;
	private int weight;
	
	public Edge(K to, K from, int weight) {
		super();
		this.to = to;
		this.from = from;
		this.weight = weight;
	}

	public K getFrom() {
		return from;
	}

	public K getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}
	
}
