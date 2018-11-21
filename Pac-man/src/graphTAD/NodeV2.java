package graphTAD;

import java.util.ArrayList;

public class NodeV2<T extends Comparable<T>> implements Comparable<NodeV2<T>> {

	private T value;
	private boolean visited;
	private ArrayList<EdgeV2<?>> edges;
	private double minimumEdgeCost;
	
	
	public NodeV2(T value) {

		this.value = value;
		edges = new ArrayList<>();

	}

	public ArrayList<EdgeV2<?>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<EdgeV2<?>> edges) {
		this.edges = edges;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {

		return value.toString();

	}

	public boolean equals(NodeV2<T> v) {

		return this.value == v.value;
	}

	@Override
	public int compareTo(NodeV2<T> o) {
		
		return (int)( this.minimumEdgeCost - o.minimumEdgeCost);

	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean b) {
		this.visited = b;
	}
	
	public double minEdge() {
		
		double menor = Integer.MAX_VALUE;
		for (int i = 0; i < edges.size(); i++) {
			if(edges.get(i).getCost() < menor) {
				menor = edges.get(i).getCost();
			}
		}
		
		return menor;
	}
	
	public void setMinValue(double newMin) {
		minimumEdgeCost =  newMin;
	}

}
