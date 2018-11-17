package graphTAD;

import java.util.ArrayList;

public class NodeV2<T extends Comparable<T>> implements Comparable<NodeV2<T>> {

	private T value;
	private ArrayList<EdgeV2<?>> edges;

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

		if (this.equals(o))
			return 0;

		else
			return -1;

	}

}
