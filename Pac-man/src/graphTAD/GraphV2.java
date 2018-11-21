package graphTAD;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Stack;

public class GraphV2<T extends Comparable<T>, E extends Comparable<E>> implements TheGraphV2<T,E>{

	private ArrayList<ArrayList<NodeV2<T>>> adjacencyList;
	private ArrayList<NodeV2<T>> verts;
	private ArrayList<EdgeV2<E>> edges;
	private boolean directed;

	public GraphV2(int numVertex) {

		adjacencyList = new ArrayList<ArrayList<NodeV2<T>>>(numVertex);
		verts = new ArrayList<>(numVertex);
		edges = new ArrayList<>();

	}
	
	public GraphV2() {

		adjacencyList = new ArrayList<ArrayList<NodeV2<T>>>();
		verts = new ArrayList<>();
		edges = new ArrayList<>();

	}

	public int getListPosition(T valueVertex) {

		int index = -1;

		for (int i = 0; i < verts.size(); i++) {

			if (verts.get(i).getValue().equals(valueVertex))

				index = i;

		}

		return index;

	}

	public void addEdge(T from, T destination, boolean directed, double cost, E value) {

		int f = getListPosition(from);
		int d = getListPosition(destination);

		if (directed) {

			adjacencyList.get(f).add(new NodeV2<T>(destination));
			verts.get(f).getEdges()
					.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));

			edges.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));
			directed = true;
		} else {

			adjacencyList.get(f).add(new NodeV2<T>(destination));
			adjacencyList.get(d).add(new NodeV2<T>(from));
			verts.get(f).getEdges()
					.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));
			verts.get(d).getEdges()
					.add(new EdgeV2<E>(new NodeV2<T>(destination), new NodeV2<T>(from), cost, directed, value));
			edges.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));
			edges.add(new EdgeV2<E>(new NodeV2<T>(destination), new NodeV2<T>(from), cost, directed, value));
		}

	}

	public void addNode(T valueVertex) {

		verts.add(new NodeV2<T>(valueVertex));
		adjacencyList.add(new ArrayList<NodeV2<T>>());

	}


	public boolean isAdjacent(T vertexA, T vertexB) {

		boolean is = false;
		int index = getListPosition(vertexA);

		for (int i = 0; i < adjacencyList.get(index).size(); i++) {

			if (adjacencyList.get(index).get(i).getValue().equals(vertexB)) {

				is = true;

			}

		}

		return is;

	}

	public int getNumberOfVertices() {

		return adjacencyList.size();
	}

	public ArrayList<ArrayList<NodeV2<T>>> getAdjList() {
		return adjacencyList;
	}

	public void setAdjList(ArrayList<ArrayList<NodeV2<T>>> adjList) {
		this.adjacencyList = adjList;
	}

	public ArrayList<NodeV2<T>> getVertices() {
		return verts;
	}

	public void setVertices(ArrayList<NodeV2<T>> vertices) {
		this.verts = vertices;
	}

	public ArrayList<EdgeV2<E>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<EdgeV2<E>> edges) {
		this.edges = edges;
	}

	public ArrayList<EdgeV2<?>> getEdgesOfVertex(T vertex) {

		int index = getListPosition(vertex);

		return verts.get(index).getEdges();

	}

	public int edgesOfVertex(T vertex) {

		int index = getListPosition(vertex);

		return verts.get(index).getEdges().size();

	}
	
	public boolean isDirected() {
		return this.directed;
	}
	
//	public void DFSUtil(NodeV2 v)
//	{
//		
//		v.setVisited(true);
//
//
//	    Iterator<Integer> i = adj[v].listIterator();
//	    while (i.hasNext())
//	    {
//	        int n = i.next();
//	        if (!visited[n])
//	            DFSUtil(n, visited);
//	    }
//	}
//
//
//	public void DFS(int v)
//	{
//
//	    boolean visited[] = new boolean[V];
//
//	    // Call the recursive helper function to print DFS traversal
//	    DFSUtil(v, visited);
//	}
	
	//Prim
	public GraphV2<T,E> MST (GraphV2<T,E> graph, T source){
		
		NodeV2<T> sourceVertex = verts.get(getListPosition(source));
		
		if(graph != null) {
			
			GraphV2<T,E> minimumGraph = new GraphV2<>();
			minimumGraph.addNode(sourceVertex.getValue());
			ArrayList<NodeV2<T>> nodes = graph.getVertices();
			PriorityQueue<NodeV2<T>> queue = new PriorityQueue<>();
			
			for(int i=0; i<nodes.size(); i++) {
				nodes.get(i).setMinValue(Double.MAX_VALUE);
				nodes.get(i).setVisited(false);
				
			}
			
			sourceVertex.setMinValue(0);
			
			for(int i=0; i<nodes.size(); i++) {
				queue.offer(nodes.get(i));
				
			}
			
			while(!queue.isEmpty()) {
				
				NodeV2<T> temp = queue.poll();
				
				for (int i = 0; i < temp.getEdges().size(); i++) {
					NodeV2<T> v = (NodeV2<T>) temp.getEdges().get(i).getDestination();
					temp.getEdges().get(i);
					if(temp.getEdges().get(i).getCost()< v.minEdge()) {
						minimumGraph.addNode(v.getValue());
					}
				}
				
			}
			
			return minimumGraph; 
			
		}
		
		return null;
		
	}
	
	public void dijkstra(NodeV2 source){
		// Algo:
		// 1. Take the unvisited node with minimum weight.
		// 2. Visit all its neighbours.
		// 3. Update the distances for all the neighbours (In the Priority Queue).
		// Repeat the process till all the connected nodes are visited.
		
		source.setMinValue(0);
		PriorityQueue<NodeV2> queue = new PriorityQueue<NodeV2>();
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			NodeV2 u = queue.poll();
		
			for(EdgeV2 neighbour:(ArrayList<EdgeV2>)(u.getEdges())){
				Double newDist = u.minEdge()+neighbour.getCost();
				
				if(neighbour.getDestination().minEdge()>newDist){
					// Remove the node from the queue to update the distance value.
					queue.remove(neighbour.getDestination());
					neighbour.getDestination().setMinValue(newDist);
					
					// Take the path visited till now and add the new node.s
					neighbour.getDestination().setPredecessors(new LinkedList<NodeV2>(u.getPredecessors()));
					neighbour.getDestination().getPredecessors().add(u);
					
					//Reenter the node with new distance.
					queue.add(neighbour.getDestination());					
				}
			}
		}
	}

	
	
//    public GraphV2<T,E> arbolExpMinPrim(GraphV2<T,E> g, T inicio) {
//        // Obtengo la cantidad total de vértices
//        int verticesTotales = g.getVertices().size();
//        NodeV2<?> vOrigen = verts.get(g.getListPosition(inicio));
//        if (vOrigen != null) {
//            GraphV2<T,E> gNuevo = new GraphV2<T,E>();
//            g.getVertices().stream().forEach((v) -> {
//                gNuevo.addNode(v.getValue());
//            });
//            
//            // Para esta implementación, los pesos son números enteros.
//            PriorityQueue<EdgeV2<?>> cola = new PriorityQueue<>((EdgeV2<?> a1, EdgeV2<?> a2) -> Double.compare(a1.getCost(), a2.getCost()));
//
//            int cont = 0;
//            
//            while (cont < verticesTotales) {
//                for (Iterator<EdgeV2<?>> it = vOrigen.getEdges().iterator(); it.hasNext();) {
//                    EdgeV2<?> arc = it.next();
//                    if (!arc.getDestination().isVisitado()) {
//                        cola.offer(arc);
//                    }
//                }
//                
//                EdgeV2<?> arco = cola.poll();
//                if (!arco.getDestination().isVisitado()) {
//                    arco.getDestination().setVisitado(true);
//                    gNuevo.agregarArco(arco.getPrevio().getContenido(), arco.getDestination().getContenido(), arco.getPeso());
//                    cont ++;
//                    vOrigen = arco.getDestination();
//                }
//            }
//            return gNuevo;
//            
//        }
//        return null;
//    }
	
    
}