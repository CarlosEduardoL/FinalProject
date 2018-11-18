package graphTAD;


import java.util.ArrayList;

public class GraphV2<T extends Comparable<T>, E extends Comparable<E>> implements TheGraphV2<T,E>{

	private ArrayList<ArrayList<NodeV2<T>>> adjacencyList;
	private ArrayList<NodeV2<T>> verts;
	private ArrayList<EdgeV2<E>> edges;

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
	
	
//    // Obtengo la cantidad total de vértices
//    int verticesTotales = g.getVertices().size();
//    Vertice<T> vOrigen = g.buscarVertice(inicio);
//    if (vOrigen != null) {
//        Grafo<T> gNuevo = new Grafo<>(g.isDirigido());
//        g.getVertices().stream().forEach((v) -> {
//            gNuevo.agregarVertice(v.getContenido());
//        });
//        
//        // Para esta implementación, los pesos son números enteros.
//        PriorityQueue<Arco<T>> cola = new PriorityQueue<>((Arco a1, Arco a2) -> Integer.compare(a1.getPeso(), a2.getPeso()));
//
//        int cont = 0;
//        
//        while (cont < verticesTotales) {
//            for (Iterator<Arco> it = vOrigen.getArcos().iterator(); it.hasNext();) {
//                Arco<T> arc = it.next();
//                if (!arc.getDestino().isVisitado()) {
//                    cola.offer(arc);
//                }
//            }
//            
//            Arco<T> arco = cola.poll();
//            if (!arco.getDestino().isVisitado()) {
//                arco.getDestino().setVisitado(true);
//                gNuevo.agregarArco(arco.getPrevio().getContenido(), arco.getDestino().getContenido(), arco.getPeso());
//                cont ++;
//                vOrigen = arco.getDestino();
//            }
//        }
//        return gNuevo;
//        
//    }
//    return null;
    
}