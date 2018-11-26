package graphTAD;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import org.w3c.dom.ls.LSInput;

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
	
	private Hashtable<K, Integer> DFS(K root) {
		Hashtable<K, Integer> levels = new Hashtable<>();

		Stack<K> queue = new Stack<>();
		queue.add(root);
		levels.put(root, 1);
		while(queue.peek() != null){
			K actual = queue.pop();
			ArrayList<K> list = adjacencyList.get(actual);
			for(int i = 0; i < list.size(); i++){
				if(i <= adjacencyList.get(actual).size() && !levels.contains(list.get(i))){
					queue.add(list.get(i));
					levels.put(list.get(i),levels.get(list.get(i)) + 1);
				}
			}
		}
		return levels;
	}
	
    public GraphV2<K,V> arbolExpMinPrim(GraphV2<K,V> g, K inicio) {
        // Obtengo la cantidad total de vértices
        int verticesTotales = g.nodes.size();
        Node<K,V> vOrigen = g.nodes.get(inicio);
        
        if (vOrigen != null) {
            GraphV2<K,V> gNuevo = new GraphV2<>(g.isDirected());
            

    		List<K> list = Collections.list(g.getNodes().keys());
            for (K i : list) {
				gNuevo.addNode(i,g.getNodes().get(i).getValue());;
			}
            
            // Para esta implementación, los pesos son números enteros.
            PriorityQueue<Edge<K>> cola = new PriorityQueue<>();

            int cont = 0;
            
            while (cont < verticesTotales) {
            	
            	ArrayList<Edge<K>> listTemp = g.getEdgesOfNode(g, vOrigen.getKey());
                for (int i = 0; i < listTemp.size(); i++) {
                	
                	Edge<K> edge = listTemp.get(i);
                	g.getNodes().get(edge.getTo());
                	if(!g.getNodes().get(edge.getTo()).isVisited()) {
                		cola.offer(edge);
                	}
				}
                
                Edge<K> arco = cola.poll();
                Node temp = g.getNodes().get(arco.getTo());
                
                if (!temp.isVisited()) {
                    temp.setVisited(true);
                    gNuevo.addEdge(arco.getFrom() , g.getNodes().get(arco.getFrom()).getValue() , arco.getTo(), g.getNodes().get(arco.getTo()).getValue(), arco.getWeight());
                    cont ++;
                    vOrigen = g.getNodes().get(arco.getTo());
                }
            }
            return gNuevo;
            
        }
        return null;
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

	public ArrayList<Edge<K>> getEdgesOfNode(GraphV2<K, V> g, K node){
		
		ArrayList<Edge<K>> edges = g.edges;
		
		ArrayList<Edge<K>> edgesOfNode = new ArrayList<>();
		
		for (int i = 0; i < edges.size(); i++) {
			
			if(edges.get(i).getFrom().compareTo(node) == 0) {
				edgesOfNode.add(edges.get(i));
			}
		}
		
		
		
		return edgesOfNode;
		
		
	}
    
}