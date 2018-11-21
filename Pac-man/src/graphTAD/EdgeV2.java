package graphTAD;


public class EdgeV2<E extends Comparable<E>> implements Comparable<EdgeV2<E>> {

	private E value;
	private double cost;
	private boolean directed;
	private NodeV2<?> from;
	private NodeV2<?> destination;


	public EdgeV2(boolean directed, double cost, E value) {

		this.value = value;
		this.cost = cost;
		this.directed = directed;

	}

	public EdgeV2(NodeV2<?> from, NodeV2<?> destination, double cost, boolean directed, E value) {

		this.from = from;
		this.destination = destination;
		this.value = value;
		this.cost = cost;
		this.directed = directed;

	}

	public NodeV2<?> getFrom() {
		return from;
	}

	public void setFrom(NodeV2<?> from) {
		this.from = from;
	}

	public NodeV2<?> getDestination() {
		return destination;
	}

	public void setDestination(NodeV2<?> destination) {
		this.destination = destination;
	}

	public E getValue() {
		return value;
	}

	public void setName(E value) {
		this.value = value;
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(EdgeV2<E> x) {

		return Integer.parseInt(String.valueOf(this.cost - x.getCost()));

	}

	@Override
	public String toString() {

		return cost + " - " + value.toString();

	}




}