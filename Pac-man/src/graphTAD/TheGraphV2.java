package graphTAD;

public interface TheGraphV2<T extends Comparable<T>, E extends Comparable<E>> {
	
	public int getListPosition(T valueOfVertex);
	
	public void addEdge(T from, T destination, boolean directed, double cost, E value);
	
	public void addNode(T valueVertex);
	
	public boolean isAdjacent(T vertexA, T vertexB);
	
	public int edgesOfVertex(T vertex);
	
}
