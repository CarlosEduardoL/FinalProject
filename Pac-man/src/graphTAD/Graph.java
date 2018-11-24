package graphTAD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author Carlos Eduardo Lizalda Valencia
 *
 * @param <K> Key or index of the node
 * @param <V> Value or the same K of the node
 */
public class Graph<K extends Comparable<K>, V> implements TheGraph<K, V>{

	//-------------------------//
	//-------Parameters--------//
	//-------------------------//


	private Hashtable<K, Hashtable<K, Integer>> adjacencyArray;
	// Nodes
	private Hashtable<K, Node<K, V>> nodes;
	// Edges
	private List<Edge<K>> edges;
	// type of graph
	private boolean directed;

	private Hashtable<K, Hashtable<K, List<V>>> paths;
	private boolean changedPaths;
	
	private Hashtable<K, Hashtable<K, Integer>> minimumWeigths;

	
	//---------------------------//
	//--------Constructor--------//
	//---------------------------//

	public Graph(boolean directed) {
		// initialize attributes
		adjacencyArray = new Hashtable<>();
		nodes = new Hashtable<>();
		edges = new ArrayList<>();
		this.directed = directed;
	}

	public Graph(boolean directed, int numberOfNodes) {
		// initialize attributes
		adjacencyArray = new Hashtable<>(numberOfNodes);
		nodes = new Hashtable<>(numberOfNodes);
		edges = new ArrayList<>();
		this.directed = directed;
	}

	//----------------------------//
	//---------Methods------------//
	//----------------------------//

	public int getNumberOfEdges() {
		return edges.size();
	}

	public int getNumberOfNodes() {
		return nodes.size();
	}
	
	public Hashtable<K, Hashtable<K, Integer>> getAdjacencyArray(){
		return adjacencyArray;
	}

	@Override
	public void addNode(K key,V value) {
		if (!nodes.containsKey(key)) {
			nodes.put(key, new Node<K, V>(key, value));
		}
		changedPaths = true;
	}

	@Override
	public void addEdge(K key1, V value1, K key2, V value2) {
		addEdge(key1, value1, key2, value2, 1);
	}

	@Override
	public void addEdge(K key1, V value1, K key2, V value2, int weight) {
		changedPaths = true;
		addNode(key1, value1);
		addNode(key2, value2);
		edges.add(new Edge<K>(key1, key2, weight));
		if (!directed) {	
			edges.add(new Edge<K>(key2, key1, weight));
		}

		// check if the row in the key2 exist
		if (adjacencyArray.get(key1) == null) {
			adjacencyArray.put(key1, new Hashtable<K,Integer>());
		}
		Integer adjacents = adjacencyArray.get(key1).get(key2) != null? adjacencyArray.get(key1).get(key2) + 1 : 1;
		adjacencyArray.get(key1).put(key2,  adjacents);
		if (!directed) {
			if (adjacencyArray.get(key2) == null) {
				adjacencyArray.put(key2, new Hashtable<K,Integer>());
			}
			adjacents = adjacencyArray.get(key2).get(key1) != null? adjacencyArray.get(key2).get(key1) + 1 : 1;
			adjacencyArray.get(key2).put(key1,  adjacents);
		}

	}

	@Override
	public boolean isConnected() {

		boolean connected = true;
		Hashtable<K, Integer> levels = BFS(nodes.keys().nextElement());

		for(K key : Collections.list(nodes.keys())) {
			connected &= levels.containsKey(key);
		}

		return connected;
	}

	@Override
	public List<V> shortesPath(K key1, K key2) {
		if (changedPaths) {
			paths = Floyd_Warshall();
			changedPaths = false;
		}
		return paths.get(key1).get(key2);
	}
	
	@Override
	public int shortesPathWeight(K key1, K key2) {
		paths = Floyd_Warshall();
		changedPaths = false;
		return BellmanFord(key1, key2);
	}

	@Override
	public void removeEdge(K from, K to) {
		adjacencyArray.get(from).remove(to);
		if (!directed) {
			adjacencyArray.get(to).remove(from);
		}
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getFrom().equals(from) && edges.get(i).getTo().equals(to)) {
				edges.remove(i);
				i--;
			}
			if (!directed && edges.get(i).getTo().equals(from) && edges.get(i).getFrom().equals(to)) {
				edges.remove(i);
				i--;
			}
		}
	}

	@Override
	public TheGraph<K, V> MST() {
		TheGraph<K, V> minimun = new Graph<>(directed);
		Collections.sort(edges);
		DisjointSet<K> dis = new DisjointSet<>(); 
		int cost = 0;
		for (int i = 0; i < edges.size(); i++) {
			K key1 = edges.get(i).getFrom();
			K key2 = edges.get(i).getTo();
			dis.makeGroup(key1);
			dis.makeGroup(key2);

			if (!dis.representant(key1).equals(dis.representant(key2))) {
				dis.union(key1, key2);
				minimun.addEdge(key1, nodes.get(key1).getValue(), key2, nodes.get(key2).getValue(), edges.get(i).getWeight());
				cost += edges.get(i).getWeight();
			}
		}
		return minimun;
	}

	//---------------------------------------------------------//
	//------------------Private Methods------------------------//
	//---------------------------------------------------------//
	private Hashtable<K, Integer> BFS(K root) {

		Hashtable<K, Integer> levels = new Hashtable<>();

		Queue<K> queue = new LinkedList<>();
		queue.add(root);
		levels.put(root, 1);
		while(queue.peek() != null){
			K actual = queue.poll();
			ArrayList<K> list = Collections.list(adjacencyArray.get(actual).keys());
			for(int i = 0; i < list.size(); i++){
				if(adjacencyArray.get(actual).get(list.get(i)) != 0 && !levels.containsKey(list.get(i))){
					queue.add(list.get(i));
					levels.put(list.get(i),levels.get(actual) + 1);
				}
			}
		}
		return levels;
	}

	private Hashtable<K, Integer> DFS(K root) {
		Hashtable<K, Integer> levels = new Hashtable<>();

		Stack<K> queue = new Stack<>();
		queue.add(root);
		levels.put(root, 1);
		while(queue.peek() != null){
			K actual = queue.pop();
			ArrayList<K> list = Collections.list(adjacencyArray.get(actual).keys());
			for(int i = 0; i < list.size(); i++){
				if(adjacencyArray.get(actual).get(list.get(i)) != 0 && !levels.contains(list.get(i))){
					queue.add(list.get(i));
					levels.put(list.get(i),levels.get(list.get(i)) + 1);
				}
			}
		}
		return levels;
	}

	private int BellmanFord(K root, K objetive) {

		List<K> keys = Collections.list(adjacencyArray.keys());
		Hashtable<K, Integer> distance = new Hashtable<>(keys.size());
		for(K key : keys) {
			distance.put(key, 999999);
		}
		distance.put(root, 0);

		for (int i = 0; i < keys.size()-1; i++) {
			for (int j = 0; j < edges.size(); j++) {
				if (distance.get(edges.get(j).getFrom()) + edges.get(j).getWeight() < distance.get(edges.get(j).getTo()) ) {
					distance.put(edges.get(j).getTo(), distance.get(edges.get(j).getFrom()) + edges.get(j).getWeight());
				}
			}
		}

		return distance.get(objetive);
	}
	
	private Hashtable<K, Hashtable<K, List<V>>> Floyd_Warshall(){

		Hashtable<K, Hashtable<K, Integer>> weigthArray = new Hashtable<>(nodes.size());
		paths = new Hashtable<>(nodes.size());
		List<K> keys = Collections.list(adjacencyArray.keys());
		for(K key1 : keys) {
			weigthArray.put(key1, new Hashtable<>(nodes.size()));
			paths.put(key1, new Hashtable<>());
			for(K key2 : keys) {
				weigthArray.get(key1).put(key2, key1.equals(key2)? 0 : 999999);
				paths.get(key1).put(key2, new ArrayList<>());
				if (adjacencyArray.get(key1).get(key2) != null) {
					List<V> list = new ArrayList<>();
					list.add(nodes.get(key1).getValue());
					list.add(nodes.get(key2).getValue());
					paths.get(key1).put(key2, list);
				}else if(key1.equals(key2)) {
					List<V> list = new ArrayList<>();
					list.add(nodes.get(key1).getValue());
					paths.get(key1).put(key2, list);
				}
			}
		}

		for (Edge<K> edge: edges) {
			weigthArray.get(edge.getFrom()).put(edge.getTo(), edge.getWeight());
			List<V> list = new ArrayList<>();
			list.add(nodes.get(edge.getFrom()).getValue());
			list.add(nodes.get(edge.getTo()).getValue());
			paths.get(edge.getFrom()).put(edge.getTo(), list);
		}

		for(K key1 : keys) {
			for(K key2 : keys) {
				for(K key3 : keys) {
					int num1 = weigthArray.get(key2).get(key3);
					int num2 = weigthArray.get(key2).get(key1) + weigthArray.get(key1).get(key3);
					if (num1 > num2) {
						weigthArray.get(key2).put(key3,num2);
						List<V> l;
						l = new ArrayList<>(paths.get(key2).get(key1));
						if (l.size() > 0) {
							l.remove(l.size()-1);
						}
						l.addAll(paths.get(key1).get(key3));
						paths.get(key2).put(key3, l);

					}
				}
			}
		}
		
		minimumWeigths = weigthArray;
		return paths;
	}
	
	public static void main(String[] args) {
		Graph<Integer, Integer> g = new Graph<>(false);
		g.addEdge(13, 13, 8, 8, 5);
		g.addEdge(13, 13, 5, 5, 4);
		g.addEdge(13, 13, 21, 21, 2);
		g.addEdge(9, 9, 8, 8, 6);
		g.addEdge(9, 9, 21, 21, 5);
		g.addEdge(5, 5, 21, 21, 1);
		g.MST();
		
		List<Integer> list = g.shortesPath(13, 21);
		int p =g.shortesPathWeight(8, 9);
		
		
		List<Integer> tempList = Collections.list(g.Floyd_Warshall().keys());
		
		for (Integer i : tempList) {
			for(Integer j: tempList) {
				System.out.print(g.minimumWeigths.get(i).get(j) +" " );
			}
			System.out.println();
		}
		
	}
	
	public void print () {
		
	}

}
