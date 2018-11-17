package graphTAD;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class GraphV2<T extends Comparable<T>, E extends Comparable<E>> {

	private ArrayList<ArrayList<NodeV2<T>>> adjList;
	private ArrayList<NodeV2<T>> vertices;
	private ArrayList<EdgeV2<E>> edges;

	public GraphV2(int numVertex) {

		adjList = new ArrayList<ArrayList<NodeV2<T>>>(numVertex);
		vertices = new ArrayList<>(numVertex);
		edges = new ArrayList<>();

	}

	public int getIndexVertex(T valueVertex) {

		int index = -1;

		for (int i = 0; i < vertices.size(); i++) {

			if (vertices.get(i).getValue().equals(valueVertex))

				index = i;

		}

		return index;

	}

	public void addEdge(T from, T destination, boolean directed, double cost, E value) {

		int f = getIndexVertex(from);
		int d = getIndexVertex(destination);

		if (directed) {

			adjList.get(f).add(new NodeV2<T>(destination));
			vertices.get(f).getEdges()
					.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));

			edges.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));

		} else {

			adjList.get(f).add(new NodeV2<T>(destination));
			adjList.get(d).add(new NodeV2<T>(from));
			vertices.get(f).getEdges()
					.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));
			vertices.get(d).getEdges()
					.add(new EdgeV2<E>(new NodeV2<T>(destination), new NodeV2<T>(from), cost, directed, value));
			edges.add(new EdgeV2<E>(new NodeV2<T>(from), new NodeV2<T>(destination), cost, directed, value));
			edges.add(new EdgeV2<E>(new NodeV2<T>(destination), new NodeV2<T>(from), cost, directed, value));

		}

	}

	public void removeEdge(T from, T destination, boolean directed, double cost) {

	}

	public void addVertex(T valueVertex) {

		vertices.add(new NodeV2<T>(valueVertex));
		adjList.add(new ArrayList<NodeV2<T>>());

	}

	public void removeVertex(T valueVertex) {

	}

	public boolean isAdjacent(T vertexA, T vertexB) {

		boolean is = false;
		int index = getIndexVertex(vertexA);

		for (int i = 0; i < adjList.get(index).size(); i++) {

			if (adjList.get(index).get(i).getValue().equals(vertexB)) {

				is = true;

			}

		}

		return is;

	}

	public int getNumVertex() {

		return adjList.size();
	}

	public ArrayList<ArrayList<NodeV2<T>>> getAdjList() {
		return adjList;
	}

	public void setAdjList(ArrayList<ArrayList<NodeV2<T>>> adjList) {
		this.adjList = adjList;
	}

	public ArrayList<NodeV2<T>> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<NodeV2<T>> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<EdgeV2<E>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<EdgeV2<E>> edges) {
		this.edges = edges;
	}

	public ArrayList<EdgeV2<?>> getEdgesOfVertex(T vertex) {

		int index = getIndexVertex(vertex);

		return vertices.get(index).getEdges();

	}

	public int numEdgesOfVertex(T vertex) {

		int index = getIndexVertex(vertex);

		return vertices.get(index).getEdges().size();

	}
	
    // Obtengo la cantidad total de vértices
    int verticesTotales = g.getVertices().size();
    Vertice<T> vOrigen = g.buscarVertice(inicio);
    if (vOrigen != null) {
        Grafo<T> gNuevo = new Grafo<>(g.isDirigido());
        g.getVertices().stream().forEach((v) -> {
            gNuevo.agregarVertice(v.getContenido());
        });
        
        // Para esta implementación, los pesos son números enteros.
        PriorityQueue<Arco<T>> cola = new PriorityQueue<>((Arco a1, Arco a2) -> Integer.compare(a1.getPeso(), a2.getPeso()));

        int cont = 0;
        
        while (cont < verticesTotales) {
            for (Iterator<Arco> it = vOrigen.getArcos().iterator(); it.hasNext();) {
                Arco<T> arc = it.next();
                if (!arc.getDestino().isVisitado()) {
                    cola.offer(arc);
                }
            }
            
            Arco<T> arco = cola.poll();
            if (!arco.getDestino().isVisitado()) {
                arco.getDestino().setVisitado(true);
                gNuevo.agregarArco(arco.getPrevio().getContenido(), arco.getDestino().getContenido(), arco.getPeso());
                cont ++;
                vOrigen = arco.getDestino();
            }
        }
        return gNuevo;
        
    }
    return null;


}