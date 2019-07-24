package p2Grafos;

import java.text.DecimalFormat;

/**
 * @author Cristina
 * 
 */
public class Graph <T>{
	
	private T[] nodes; // Vector de nodos
	private boolean[][] edges; // matriz de aristas
	private double[][] weights; // matriz de pesos
	private int numNodes; // número de elementos en un momento dado
	private double[][] matrizA = null; // matriz de coste mínimo para el algoritmo de Floyd
	private int[][] matrizP = null; // matriz de pesos para el algoritmo de Floyd


	/**
	 * 
	 * @param tam  Número máximo de nodos del grafo
	 */
	@SuppressWarnings("unchecked")
	public Graph(int tam) {
		nodes = (T[])new Object[tam];
		edges = new boolean[tam][tam];
		weights = new double[tam][tam];
		this.numNodes = 0;
	}
	
	
	public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd) {
		// Llama al constructor original
		this(tam); 
		
		// Pero modifica los atributos con los parametros para hacer pruebas...
		numNodes = initialNodes.length;
		
		for (int i=0;i<numNodes;i++) {
			// Si el vector de nodos se llama de otra forma (distinto de "nodes"), cambiad el nombre en la linea de abajo
			nodes[i]=initialNodes[i];
			for (int j=0;j<numNodes;j++){
				// Si la matriz de aristas se llama de otra forma (distinto de "edges"), cambiad el nombre en la linea de abajo
				edges[i][j]=initialEdges[i][j];
				// Si la matriz de pesos se llama de otra forma (distinto de "weights"), cambiad el nombre en la linea de abajo
				weights[i][j]=initialWeights[i][j];
			}
		}
		if (initialAfloyd!=null){
			// Si la matriz A de floyd se llama de otra forma (distinto de "aFloyd"), cambiad el nombre en la linea de abajo
			matrizA = initialAfloyd;
			// Si la matriz P de floyd se llama de otra forma (distinto de "pFloyd"), cambiad el nombre en la linea de abajo
			matrizP = initialPfloyd;
		}

	}
	
	

	/**
	 * Inserta un nuevo nodo que se le pasa como parámetro, en el vector de
	 * nodos, (si existe no lo inserta pero lo implementaremos más adelante…)
	 * 
	 * @param node, el nodo que se quiere insertar
	 * @return 0 si lo inserta, -1 si no puede insertarlo
	 */
	public int addNode(T node) {
		if (node == null || existNode(node))
			return -1;
		if (numNodes < nodes.length) {
			nodes[numNodes] = node;	
			for (int i = 0; i <= numNodes; i++) {
				edges[numNodes][i] = false;
				edges[i][numNodes] = false;
				weights[numNodes][i] = 0;
				weights[i][numNodes] = 0;
			}
			numNodes++;
			matrizP = null;
			matrizA = null;
			return 0;
		}		

		return -1;
	}


	/**
	 * Borra el nodo deseado del vector de nodos así como las aristas de las que
	 * forma parte
	 * 
	 * @param node
	 *            que se quiere borrar
	 * @return 0 si lo borra o -1 si no lo hace
	 */
	public int removeNode(T node) {
		int i = getNode(node);
		if ( i >= 0) {
			--numNodes;
			if (i != numNodes +1) {
				nodes[i] = nodes[numNodes];

				for (int j = 0; j <= numNodes; j++) {
					edges[j][i] = edges[j][numNodes];
					edges[i][j] = edges[numNodes][j];
					weights[i][j] = weights[numNodes][j];
					weights[j][i] = weights[j][numNodes];
	
				}

				edges[i][i] = edges[numNodes][numNodes];
				weights[i][i] = weights[numNodes][numNodes];
				
				matrizA = null;
				matrizP = null;
				return 0;
			}
		}
		return -1;
	}

	
	
	/**
	 * Obtiene el índice de un nodo en el vector de nodos
	 *
	 * ¡¡¡ OJO que es privado porque depende de la implementación !!!
	 * 
	 * @param node, el nodo que se busca
	 * @return la posición del nodo en el vector ó -1 si no lo encuentra
	 */
	private int getNode(T node) {
		for (int i = 0; i < numNodes; i++) {
			if (nodes[i].equals(node))
				return i;
		}
		return -1;
	}
	
	
	
	/**
	 * @param node  Nodo que se quiere consultar
	 * @return si existe o no el nodo
	 */
	public boolean existNode(T node) {
		return getNode(node) != -1;
	}
	

	
	/**
	 * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y
	 * otro destino. 
	 * Devuelve 0 si la inserta y -1 si no lo hace (si existe no la inserta).
	 * 
	 * @param source, nodo origen
	 * @param target, nodo destino
	 * @param edgeWeight, peso de la arista, debe ser > 0
	 * @return 0 si lo hace y -1 si no lo hace (también si el peso es <= 0)
	 */
	public int addEdge(T source, T target, double edgeWeight) {
		if (source != null && target != null && edgeWeight > 0 && !existEdge(source,target)) {
			int origen = getNode(source);
			int destino = getNode(target);
			
			edges[origen][destino] = true;
			weights[origen][destino] = edgeWeight;
			
			matrizA = null;
			matrizP = null;
			return 0;
		}
		return -1;
	}

	/**
	 * Borra una arista del grafo que conecta dos nodos
	 * 
	 * @param source 
	 *		nodo origen
	 * @param target
	 *		nodo destino
	 * @return 0 si la borra y -1 si no lo hace (también si no existe alguno de sus nodos)
	 */
	public int removeEdge(T source, T target) {
		if (existEdge(source, target)) {
			int origen = getNode(source);
			int destino = getNode(target);
			
			edges[origen][destino] = false;
			weights[origen][destino] = 0.0;
			
			matrizA = null;
			matrizP = null;
			return 0;
		}
		return -1;
	}



	/**
	 * Devuelve el peso de la arista que conecta dos nodos, si no existe la
	 * arista, devuelve -1 (también si no existe alguno de los nodos)
	 * 
	 * @param source
	 * @param target
	 * @return El peso de la arista o -1 si no existe
	 */
	public double getEdge(T source, T target) {
		if (source != null && target != null)
			return weights[getNode(source)][getNode(target)];
		return -1;
	}
	
	
	
	/**
	 * Comprueba si existe una arista entre dos nodos que se pasan como parámetro
	 * 
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @return verdadero o falso si existe o no, si alguno de los nodos no
	 *         existe también falso
	 */
	public boolean existEdge(T source, T target) {
		int i = getNode(source);
		int j = getNode(target);

		if (i >= 0 && j >= 0)
			return(edges[i][j]);
		else
			return (false);

	}


	/*
     * Devuelve un String con la informacion del grafo (incluyendo matrices de Floyd) 
     */ 
    public String toString() { 
        DecimalFormat df = new DecimalFormat("#.##"); 
        String cadena = ""; 
  
        cadena += "VECTOR NODOS\n"; 
        for (int i = 0; i < numNodes; i++) { 
            cadena += nodes[i].toString() + "\t"; 
        } 
        cadena += "\n\nMATRIZ ARISTAS\n"; 
        for (int i = 0; i < numNodes; i++) { 
            for (int j = 0; j < numNodes; j++) { 
                if (edges[i][j]) 
                    cadena += "T\t"; 
                else 
                    cadena += "F\t"; 
            } 
            cadena += "\n"; 
        } 
        cadena += "\nMATRIZ PESOS\n"; 
        for (int i = 0; i < numNodes; i++) { 
            for (int j = 0; j < numNodes; j++) { 
  
                cadena += (edges[i][j]?df.format(weights[i][j]):"-") + "\t"; 
            } 
            cadena += "\n"; 
        } 
  
        double[][] aFloyd = getAFloyd(); 
        if (aFloyd != null) { 
            cadena += "\nMATRIZ AFloyd\n"; 
            for (int i = 0; i < numNodes; i++) { 
                for (int j = 0; j < numNodes; j++) { 
                    cadena += df.format(aFloyd[i][j]) + "\t"; 
                } 
                cadena += "\n"; 
            } 
        } 
  
        int[][] pFloyd = getPFloyd(); 
        if (pFloyd != null) { 
                cadena += "\nMATRIZ PFloyd\n"; 
            for (int i = 0; i < numNodes; i++) { 
                for (int j = 0; j < numNodes; j++) { 
                    cadena += (pFloyd[i][j]>=0?df.format(pFloyd[i][j]):"-") + "\t"; 
                } 
                cadena += "\n"; 
            } 
        } 
        return cadena; 
    } 
	
	
	/**
	 * Método que devuelve el número de nodos.
	 * @return nº de nodos
	 */
	protected int getNumberOfNodes() {
		return numNodes;
	}
	
	
	
	/** 
	*  Algoritmo de Dijkstra para encontrar el camino de coste mínimo desde nodoOrigen  
	*  hasta el resto de nodos 
	*   
	* @param nodoOrigen 
	* @return vector D de dijkstra para comprobar funcionamiento 
	*/ 
	public double[] dijkstra(T nodoOrigen) {
		// Inicialización de variables:
		double[] vectorD = new double[numNodes];
		double[] vectorP = new double[numNodes];
		boolean[] conjuntoS = new boolean[numNodes];
		
		int inicio = getNode(nodoOrigen);
		if(!existNode(nodoOrigen) || inicio == -1)
			return null;
		
		// Valores iniciales:
		for (int i = 0; i < numNodes; i++) {
			conjuntoS[i] = false;
			if (i == inicio) {
				vectorD[i] = 0.0;
				vectorP[i] = -1;
				conjuntoS[i] = true;
			}

			else if(edges[inicio][i]){
				vectorD[i] = new Double(weights[inicio][i]);
				vectorP[i] = inicio;
			}
			else {
				vectorD[i] = Double.POSITIVE_INFINITY;
				vectorP[i] = -1;
			}
		}

		// Aplicación:
		for (int i = 0; i < numNodes; i++) {
			
			int w = minCost(vectorD, conjuntoS);
			if (w != -1) {
				conjuntoS[w] = true;
				for (int m = 0; m < numNodes; m++) {
					if (edges[w][m]) {
						if (vectorD[w] + weights[w][m] < vectorD[m]) {
							vectorD[m] = vectorD[w] + weights[w][m];
							vectorP[m] = w;
						}
					}
				}
			}
		}
		return vectorD;
	}


	/** 
	 * Busca el nodo con distancia mínima en D al resto de nodos 
	 * @param dist  vector d 
	 * @param v   vector con visitados (conjunto S) 
	 * @return índice del nodo buscado o -1 si el grafo es no conexo o no quedan nodos sin visitar 
	 */ 
	private int minCost(double[] dist, boolean[] v) {
		int minCost = -1;
		for (int i = 0; i < numNodes; i++){
			if(!v[i] && (minCost == -1	 || dist[i] < dist[minCost])){
				minCost = i;
			}
		}
		
		return minCost;
	} 
	
	/*
	 * Aplica el algoritmo de Floyd al grafo.
	 * 
	 * @return 0 si lo aplica y genera matrices A y P; y -1 si no lo hace
	 */

	public int floyd(){		
		// Cuando no hay nodos:
		if (numNodes == 0) {
			return -1;
		}

		matrizA = new double[numNodes][numNodes];
		matrizP = new int[numNodes][numNodes];

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j] == true) {
					matrizA[i][j] = weights[i][j];
					matrizP[i][j] = -1;
				} else {
					matrizA[i][j] = Double.POSITIVE_INFINITY;
					matrizP[i][j] = -1;
				}
			}
			matrizA[i][i] = 0.0; 
		}

		for (int k = 0; k < numNodes; k++){
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					if (matrizA[i][k] + matrizA[k][j] < matrizA[i][j]){
						matrizA[i][j] = matrizA[i][k] + matrizA[k][j];
						matrizP[i][j] = k;
					}
				}
			}
		}
		return 0;
	}	



	/**  
	* Devuelve la matriz A de Floyd, con infinito si no hay camino  
	* Si no se ha invocado a Floyd debe devolver null  
	*   
	* @return la matriz A de Floyd  
	*/  
	protected double[][] getAFloyd(){
		return matrizA;
		
	}
	
	
	/**  
	* Devuelve la matriz P de Floyd, con -1 en las posiciones en las que no hay nodo intermedio  
	* Si no se ha invocado a Floyd debe devolver null  
	*   
	* @return la matriz P de Floyd   
	*/  
	protected int[][] getPFloyd() {
		return matrizP;
	}
	
	
	
	/** 
	* Indica el camino entre los nodos que se le pasan como parámetros de esta forma: 
	* Origen<tab>(coste0)<tab>Intermedio1<tab>(coste1)….IntermedioN<tab>(costeN) Destino 
	* Si no hay camino muestra: Origen<tab>(Infinity)<tab>Destino 
	* Si Origen y Destino coinciden muestra: Origen 
	* 
	* @param origen 
	* @param destino 
	* @return El String indicado 
	*/ 
	public String path(T origen, T destino) {
		int nodoOrigen = getNode(origen);
		int nodoDestino = getNode(destino);

		if (origen == null || destino == null)
			return null;

		String cad = "";

		// Si origen y destino coinciden:
		if (nodoOrigen == nodoDestino) {
			cad += origen.toString();
			return  cad;
		}

		// Si no hay camino:
		if (matrizP[nodoOrigen][nodoDestino] == -1) {
			cad += origen.toString() + "\t(" + "Infinity" + ")\t" + destino.toString();
			return cad;
		}

		else
			cad += origen.toString() + printPath(nodoOrigen, nodoDestino);

		return cad;
	} 
	
	/**
	 * Método privado recursivo para complementar el método path().
	 * @param origen
	 * @param destino
	 * @return String
	 */
	private String printPath(int origen, int destino) {
		String cad = "";
		int p = matrizP[origen][destino];
		if (p != -1)
			cad += printPath(origen, p) + printPath(p, destino);
		else
			cad += "\t(" + matrizA[origen][destino] + ")\t" + destino;
			
		return cad;
	}
		
	
	
	
	
	/** 
	* Devuelve el coste del camino de coste mínimo entre origen y destino según Floyd 
	* Si no están generadas las matrices de floyd, las genera. 
	* Si no puede obtener el valor por alguna razón, devuelve –1 (OJO que es distinto de infinito) 
	**/ 
	public double minCostPath(T origen, T destino) {
		if (origen == null || destino == null)
			return -1;
		
		int nodoOrigen = getNode(origen);
		int nodoDestino = getNode(destino);
		
		if (matrizA == null || matrizP == null) {
			floyd();
		}
		
		return matrizA[nodoOrigen][nodoDestino];
	} 
	
	
	
	/** 
	 * Lanza el recorrido en profundidad de un grafo desde un nodo determinado, 
	 * No necesariamente recorre todos los nodos. 
	 * Al recorrer cada nodo añade el toString del nodo 
	 * Usa un método privado recursivo 
	 * Que recorran vecinos empezando por el principio del vector de nodos (antes índices bajos)  
	 *  
	 * @param nodo 
	 *            El nodo por el que se quiere empezar el recorrido en 
	 *            profundidad 
	 * @return un String con el  toString de los nodos del recorrido separados por tabulaciones 
	 *            Si no existe el nodo devuelve un String vacio 
	 */ 
	public String recorridoProfundidad(T nodo) {
		if (!existNode(nodo) || nodo == null)
			return "";

		int origen = getNode(nodo);
		boolean[] nodosVisitados = new boolean[numNodes];

		return buscarRP(origen, nodosVisitados);

	}


	/**
	 * Método privado recursivo para complementar el método recorridoProfundidad().
	 * @param origen
	 * @param nodosVisitados
	 * @return String
	 */
	private String buscarRP(int origen, boolean[] nodosVisitados) {
		String camino = "";
		nodosVisitados[origen] = true;
		camino = nodes[origen].toString() + "\t";
		
		for (int j = 0; j < numNodes; j++) {
			if (nodosVisitados[j] == false && edges[origen][j] == true)
				camino += buscarRP(j, nodosVisitados);
		}
		return camino;
	}
	
}

