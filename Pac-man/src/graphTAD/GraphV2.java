package graphTAD;


import java.util.ArrayList;
import java.util.Hashtable;

public class GraphV2<K extends Comparable<K>, V>{

	private Hashtable<K, ArrayList<K>> adjacencyList;
	private Hashtable<K, Node<K,V>> nodes;
	private ArrayList<Edge<K>> edges;
	private boolean directed;

	public GraphV2(boolean isDirected, int numVertex) {

		adjacencyList = new Hashtable<>(numVertex);
		nodes = new Hashtable<>(numVertex);
		edges = new ArrayList<>();
		directed = isDirected;
		
	}
	
	public GraphV2(boolean isDirected) {
		adjacencyList = new Hashtable<>();
		nodes = new Hashtable<>();
		edges = new ArrayList<>();
		directed = isDirected;

	}
	
	public void addNode(K key, V value) {
		if (!nodes.containsKey(key)) {
			nodes.put(key, new Node<K, V>(key, value));
		}
	}
	
	public void addEdge(K key1, V value1, K key2, V value2) {
		addEdge(key1, value1, key2, value2, 1);
	}
	
	public void addEdge(K source, V value1, K dest, V value2, int weight) {
		addNode(source, value1);
		addNode(dest, value2);
		
		if(adjacencyList.get(source) != null) {
			adjacencyList.get(source).add(dest);
		}else {
			adjacencyList.put(source, new ArrayList<>());
			adjacencyList.get(source).add(dest);
		}
		
		
		edges.add(new Edge<K>(source, dest, weight));
		
		if(!directed) {
			edges.add(new Edge<K>(dest, source, weight));
			
			if(adjacencyList.get(dest) != null) {
				adjacencyList.get(dest).add(source);
			}else {
				adjacencyList.put(dest, new ArrayList<>());
				adjacencyList.get(dest).add(source);
			}
		}
	}
	
	
	
	
	public Hashtable<K, ArrayList<K>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(Hashtable<K, ArrayList<K>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public Hashtable<K, Node<K, V>> getNodes() {
		return nodes;
	}

	public void setNodes(Hashtable<K, Node<K, V>> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge<K>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge<K>> edges) {
		this.edges = edges;
	}

	public boolean isDirected() {
		return directed;
	}

	
    
}