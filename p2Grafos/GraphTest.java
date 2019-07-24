package p2Grafos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	Graph<Integer> graph;
	Graph<Integer> graphD;
	
	@Before
	public void setUp() throws Exception{
		graph = new Graph<Integer>(4);
		graphD = new Graph<Integer>(6);
	}
	
	@Test
	public void testAddNodes() {
		
		// Añadir un nodo correctamente.
		assertEquals(0, graph.getNumberOfNodes());
		assertEquals(0, graph.addNode(1));
		assertEquals(1, graph.getNumberOfNodes());
		
		// Intentar añadir el mismo nodo.
		assertEquals(-1, graph.addNode(1));
		assertEquals(1, graph.getNumberOfNodes());
		
		// Intentar añadir un nodo nulo.
		assertEquals(-1, graph.addNode(null));
		assertEquals(1, graph.getNumberOfNodes());
		
		// Intentar añadir más nodos de los que permite su tamaño.
		assertEquals(0, graph.addNode(2));
		assertEquals(0, graph.addNode(3));
		assertEquals(0, graph.addNode(4));
		assertEquals(-1, graph.addNode(5));
		assertEquals(4, graph.getNumberOfNodes());
				
		System.out.println(graph.toString());
		
	}
	
	@Test
	public void testAddRemoveAdd() {
		assertEquals(0, graph.addNode(1));
		assertEquals(0, graph.addNode(2));
		assertEquals(0, graph.addNode(4));
		assertEquals(0, graph.addNode(3));
		

		graph.addEdge(4, 1, 2);
		graph.addEdge(1, 2, 11);
		graph.addEdge(4, 2, 13);
		graph.addEdge(2, 3, 5);
		System.out.println(graph.toString());
		
		assertEquals(0, graph.removeNode(2));
		System.out.println(graph.toString());
		assertEquals(0, graph.addNode(2));
		
		System.out.println(graph.toString());
	}
	
	@Test
	public void testRemoveNodes() {
		//Preparamos el grafo:
		assertEquals(0, graph.getNumberOfNodes());
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		// Eliminamos el primer nodo del vector correctamente.
		assertEquals(0, graph.removeNode(1));
		assertEquals(2, graph.getNumberOfNodes());
		
		// Eliminamos el último nodo del vector correctamente.
		assertEquals(0, graph.removeNode(3));
		assertEquals(1, graph.getNumberOfNodes());
		
		// Intentamos eliminar un nodo nulo.
		assertEquals(-1, graph.removeNode(null));
		assertEquals(1, graph.getNumberOfNodes());
		
		// Intentamos eliminar un nodo inexistente.
		assertEquals(-1, graph.removeNode(4));
		assertEquals(1, graph.getNumberOfNodes());
		
		System.out.println(graph.toString());
	}
	
	@Test
	public void testExistNode() {
		// Existe el nodo.
		graph.addNode(1);
		assertEquals(1, graph.getNumberOfNodes());
		assertEquals(true, graph.existNode(1));
		
		// No existe el nodo.
		assertEquals(false, graph.existNode(4));
		
		// Intentamos buscar un nodo nulo.
		assertEquals(false, graph.existNode(null));
	}
	
	@Test
	public void addEdge() {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		// Añadimos una arista correctamente.
		assertEquals(3, graph.getNumberOfNodes());
		assertEquals(0, graph.addEdge(1,2,1));
		
		// Intentar añadir la misma arista.
		assertEquals(-1, graph.addEdge(1,2,1));
		
		// Intentamos añadir una arista con cualquier parámetro nulo.
		assertEquals(-1, graph.addEdge(null,2,1));
		assertEquals(-1, graph.addEdge(1,null,1));
		assertEquals(-1, graph.addEdge(1,null,1));
		
		// Intentamos añadir una arista cuyo peso sea menor o igual que 0.
		assertEquals(-1, graph.addEdge(1,2,0));
		assertEquals(-1, graph.addEdge(1,2,-4));
		
		
	}
	
	@Test
	public void testRemoveEdges() {
		//Preparamos el grafo:
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(1, 2, 2);
		
		// Eliminamos la arista correctamente.
		assertEquals(true, graph.existEdge(1, 2));
		assertEquals(0, graph.removeEdge(1, 2));
		assertEquals(false, graph.existEdge(1, 2));
		
		// Intentamos eliminar una arista nula.
		assertEquals(-1, graph.removeEdge(2, 3));
		
		// Intentamos eliminar una arista inexistente.
		assertEquals(-1, graph.removeEdge(4, 5));
		
		System.out.println(graph.toString());
	}
	
	@Test
	public void testExistEdge() {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		// Existe la arista.
		assertEquals(0, graph.addEdge(1,2,1));
		assertEquals(true, graph.existEdge(1,2));
		
		// No existe la arista.
		assertEquals(false, graph.existEdge(2,3));
		
		// Intentamos buscar una arista con algún parámetro nulo, o con ambos.
		assertEquals(false, graph.existEdge(null, 2));
		assertEquals(false, graph.existEdge(1, null));
	}
	
	@Test
	public void testDijkstra() {
		// Pasando parámetro null:
		assertEquals(null, graph.dijkstra(null));
		
		// Preparamos el grafo:
		
			// Añadimos los nodos:		
		graphD.addNode(0);
		graphD.addNode(1);
		graphD.addNode(2);
		graphD.addNode(3);
		graphD.addNode(4);
		graphD.addNode(5);
		
			// Añadimos las aristas:		
		graphD.addEdge(0, 1, 11);
		graphD.addEdge(0, 3, 13);
		graphD.addEdge(0, 5, 5);
		
		graphD.addEdge(1, 4, 9);
		
		graphD.addEdge(2, 1, 1);
		graphD.addEdge(2, 3, 2);
		
		graphD.addEdge(3, 4, 3);
		
		graphD.addEdge(4, 2, 12);
		
		graphD.addEdge(5, 1, 4);
		graphD.addEdge(5, 4, 54);
		
		// Probamos el algoritmo:		
		assertArrayEquals(new double[] {0.0, 9.0, 28.0, 13.0, 16.0, 5.0}, graphD.dijkstra(0), 0.001);
		assertArrayEquals(new double[] {Double.POSITIVE_INFINITY, 0.0, 21.0, 23.0, 9.0, Double.POSITIVE_INFINITY}, graphD.dijkstra(1), 0.001);
		assertArrayEquals(new double[] {Double.POSITIVE_INFINITY, 1.0, 0.0, 2.0, 5.0, Double.POSITIVE_INFINITY}, graphD.dijkstra(2), 0.001);
		assertArrayEquals(new double[] {Double.POSITIVE_INFINITY, 16.0, 15.0, 0.0, 3.0, Double.POSITIVE_INFINITY}, graphD.dijkstra(3), 0.001);
		assertArrayEquals(new double[] {Double.POSITIVE_INFINITY, 13.0, 12.0, 14.0, 0.0, Double.POSITIVE_INFINITY}, graphD.dijkstra(4), 0.001);
		assertArrayEquals(new double[] {Double.POSITIVE_INFINITY, 4.0, 25.0, 27.0, 13.0, 0.0}, graphD.dijkstra(5), 0.001);
		
		// Añadimos una arista del nodo 4 al 0 de peso 400.
		graphD.addEdge(4, 0, 400);
		
		// Probamos el algoritmo:		
		assertArrayEquals(new double[] {0.0, 9.0, 28.0, 13.0, 16.0, 5.0}, graphD.dijkstra(0), 0.001);
		assertArrayEquals(new double[] {409.0, 0.0, 21.0, 23.0, 9.0, 414.0}, graphD.dijkstra(1), 0.001);
		assertArrayEquals(new double[] {405.0, 1.0, 0.0, 2.0, 5.0, 410.0}, graphD.dijkstra(2), 0.001);
		assertArrayEquals(new double[] {403.0, 16.0, 15.0, 0.0, 3.0, 408.0}, graphD.dijkstra(3), 0.001);
		assertArrayEquals(new double[] {400.0, 13.0, 12.0, 14.0, 0.0, 405.0}, graphD.dijkstra(4), 0.001);
		assertArrayEquals(new double[] {413.0, 4.0, 25.0, 27.0, 13.0, 0.0}, graphD.dijkstra(5), 0.001);
		
//		System.out.println(graphD.toString());
	}
	
	@Test
	public void testFloyd() {
		// Comprobamos floyd() sin grafo existente:
		assertEquals(-1, graphD.floyd());
		
		// Preparamos el grafo:

		// Añadimos los nodos:		
		graphD.addNode(0);
		graphD.addNode(1);
		graphD.addNode(2);
		graphD.addNode(3);
		graphD.addNode(4);
		graphD.addNode(5);

		// Añadimos las aristas:		
		graphD.addEdge(0, 1, 11);
		graphD.addEdge(0, 3, 13);
		graphD.addEdge(0, 5, 5);

		graphD.addEdge(1, 4, 9);

		graphD.addEdge(2, 1, 1);
		graphD.addEdge(2, 3, 2);

		graphD.addEdge(3, 4, 3);

		graphD.addEdge(4, 2, 12);

		graphD.addEdge(5, 1, 4);
		graphD.addEdge(5, 4, 54);

		graphD.floyd();

		// Llamamos al método floyd():
		graphD.floyd();
		
		System.out.println(graphD.toString());

		// Comprobamos matrizA y matrizP:
		assertArrayEquals(new double[][] {{0.0, 9, 28, 13, 16, 5},
										  {Double.POSITIVE_INFINITY, 0.0, 21, 23, 9, Double.POSITIVE_INFINITY},
										  {Double.POSITIVE_INFINITY, 1, 0.0, 2, 5, Double.POSITIVE_INFINITY},
										  {Double.POSITIVE_INFINITY, 16, 15, 0.0, 3, Double.POSITIVE_INFINITY},
										  {Double.POSITIVE_INFINITY, 13, 12, 14, 0.0, Double.POSITIVE_INFINITY},
										  {Double.POSITIVE_INFINITY, 4, 25, 27, 13, 0.0}}, graphD.getAFloyd());
		
		assertArrayEquals(new int[][]{{-1, 5, 4, -1, 3, -1},
									  {-1, -1, 4, 4, -1, -1},
									  {-1, -1, -1, -1, 3, -1},
									  {-1, 4, 4, -1, -1, -1},
									  {-1, 2, -1, 2, -1, -1},
									  {-1, -1, 4, 4, 1, -1}}, graphD.getPFloyd());
		
		// Comprobamos floyd() aplicado al grafo dado:
		assertEquals(0, graphD.floyd());
	}
	

	@Test
	public void testMinCostPath(){
		// Preparamos el grafo:

		// Añadimos los nodos:		
		graphD.addNode(0);
		graphD.addNode(1);
		graphD.addNode(2);
		graphD.addNode(3);
		graphD.addNode(4);
		graphD.addNode(5);

		// Añadimos las aristas:		
		graphD.addEdge(0, 1, 11);
		graphD.addEdge(0, 3, 13);
		graphD.addEdge(0, 5, 5);

		graphD.addEdge(1, 4, 9);

		graphD.addEdge(2, 1, 1);
		graphD.addEdge(2, 3, 2);

		graphD.addEdge(3, 4, 3);

		graphD.addEdge(4, 2, 12);

		graphD.addEdge(5, 1, 4);
		graphD.addEdge(5, 4, 54);
		
		// Llamamos el método floyd():
		graphD.floyd();

		//Comprobamos los resultados para el grafo dado:
				// Desde 0:
		assertEquals(0.0, graphD.minCostPath(0, 0), 0.001);
		assertEquals(9.0, graphD.minCostPath(0, 1), 0.001);
		assertEquals(28.0, graphD.minCostPath(0, 2), 0.001);
		assertEquals(13.0, graphD.minCostPath(0, 3), 0.001);
		assertEquals(16.0, graphD.minCostPath(0, 4), 0.001);
		assertEquals(5.0, graphD.minCostPath(0, 5), 0.001);

				// Desde 1:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(1, 0), 0.001);
		assertEquals(0.0, graphD.minCostPath(1, 1), 0.001);
		assertEquals(21.0, graphD.minCostPath(1, 2), 0.001);
		assertEquals(23.0, graphD.minCostPath(1, 3), 0.001);
		assertEquals(9.0, graphD.minCostPath(1, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(1, 5), 0.001);

				// Desde 2:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(2, 0), 0.001);
		assertEquals(1.0, graphD.minCostPath(2, 1), 0.001);
		assertEquals(0.0, graphD.minCostPath(2, 2), 0.001);
		assertEquals(2.0, graphD.minCostPath(2, 3), 0.001);
		assertEquals(5.0, graphD.minCostPath(2, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(2, 5), 0.001);

				// Desde 3:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(3, 0), 0.001);
		assertEquals(16.0, graphD.minCostPath(3, 1), 0.001);
		assertEquals(15.0, graphD.minCostPath(3, 2), 0.001);
		assertEquals(0.0, graphD.minCostPath(3, 3), 0.001);
		assertEquals(3.0, graphD.minCostPath(3, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(3, 5), 0.001);

				// Desde 4:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(4, 0), 0.001);
		assertEquals(13.0, graphD.minCostPath(4, 1), 0.001);
		assertEquals(12.0, graphD.minCostPath(4, 2), 0.001);
		assertEquals(14.0, graphD.minCostPath(4, 3), 0.001);
		assertEquals(0.0, graphD.minCostPath(4, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(4, 5), 0.001);

				// Desde 5:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(5, 0), 0.001);
		assertEquals(4.0, graphD.minCostPath(5, 1), 0.001);
		assertEquals(25.0, graphD.minCostPath(5, 2), 0.001);
		assertEquals(27.0, graphD.minCostPath(5, 3), 0.001);
		assertEquals(13.0, graphD.minCostPath(5, 4), 0.001);
		assertEquals(0.0, graphD.minCostPath(5, 5), 0.001);
	}

	@Test
	public void testMinCostPath2(){
		// Aquí se prueba el funcionamiento del método sin haber llamado previamente al método floyd() (debería llamarse y ejecutarse correctamente):


		// Preparamos el grafo:

		// Añadimos los nodos:		
		graphD.addNode(0);
		graphD.addNode(1);
		graphD.addNode(2);
		graphD.addNode(3);
		graphD.addNode(4);
		graphD.addNode(5);

		// Añadimos las aristas:		
		graphD.addEdge(0, 1, 11);
		graphD.addEdge(0, 3, 13);
		graphD.addEdge(0, 5, 5);

		graphD.addEdge(1, 4, 9);

		graphD.addEdge(2, 1, 1);
		graphD.addEdge(2, 3, 2);

		graphD.addEdge(3, 4, 3);

		graphD.addEdge(4, 2, 12);

		graphD.addEdge(5, 1, 4);
		graphD.addEdge(5, 4, 54);

				// Desde 0:
		assertEquals(0.0, graphD.minCostPath(0, 0), 0.001);
		assertEquals(9.0, graphD.minCostPath(0, 1), 0.001);
		assertEquals(28.0, graphD.minCostPath(0, 2), 0.001);
		assertEquals(13.0, graphD.minCostPath(0, 3), 0.001);
		assertEquals(16.0, graphD.minCostPath(0, 4), 0.001);
		assertEquals(5.0, graphD.minCostPath(0, 5), 0.001);

				// Desde 1:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(1, 0), 0.001);
		assertEquals(0.0, graphD.minCostPath(1, 1), 0.001);
		assertEquals(21.0, graphD.minCostPath(1, 2), 0.001);
		assertEquals(23.0, graphD.minCostPath(1, 3), 0.001);
		assertEquals(9.0, graphD.minCostPath(1, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(1, 5), 0.001);

				// Desde 2:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(2, 0), 0.001);
		assertEquals(1.0, graphD.minCostPath(2, 1), 0.001);
		assertEquals(0.0, graphD.minCostPath(2, 2), 0.001);
		assertEquals(2.0, graphD.minCostPath(2, 3), 0.001);
		assertEquals(5.0, graphD.minCostPath(2, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(2, 5), 0.001);

				// Desde 3:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(3, 0), 0.001);
		assertEquals(16.0, graphD.minCostPath(3, 1), 0.001);
		assertEquals(15.0, graphD.minCostPath(3, 2), 0.001);
		assertEquals(0.0, graphD.minCostPath(3, 3), 0.001);
		assertEquals(3.0, graphD.minCostPath(3, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(3, 5), 0.001);

				// Desde 4:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(4, 0), 0.001);
		assertEquals(13.0, graphD.minCostPath(4, 1), 0.001);
		assertEquals(12.0, graphD.minCostPath(4, 2), 0.001);
		assertEquals(14.0, graphD.minCostPath(4, 3), 0.001);
		assertEquals(0.0, graphD.minCostPath(4, 4), 0.001);
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(4, 5), 0.001);

				// Desde 5:
		assertEquals(Double.POSITIVE_INFINITY, graphD.minCostPath(5, 0), 0.001);
		assertEquals(4.0, graphD.minCostPath(5, 1), 0.001);
		assertEquals(25.0, graphD.minCostPath(5, 2), 0.001);
		assertEquals(27.0, graphD.minCostPath(5, 3), 0.001);
		assertEquals(13.0, graphD.minCostPath(5, 4), 0.001);
		assertEquals(0.0, graphD.minCostPath(5, 5), 0.001);
	}

	@Test
	public void testPath() {
		// Preparamos el grafo:

		// Añadimos los nodos:		
		graphD.addNode(0);
		graphD.addNode(1);
		graphD.addNode(2);
		graphD.addNode(3);
		graphD.addNode(4);
		graphD.addNode(5);

		// Añadimos las aristas:		
		graphD.addEdge(0, 1, 11);
		graphD.addEdge(0, 3, 13);
		graphD.addEdge(0, 5, 5);

		graphD.addEdge(1, 4, 9);

		graphD.addEdge(2, 1, 1);
		graphD.addEdge(2, 3, 2);

		graphD.addEdge(3, 4, 3);

		graphD.addEdge(4, 2, 12);

		graphD.addEdge(5, 1, 4);
		graphD.addEdge(5, 4, 54);
		
		// Llamamos al método floyd():
		graphD.floyd();
		
		// Comprobamos los resultados para el grafo dado:
				// Desde 0:
		assertEquals("0", graphD.path(0, 0)); // El nodo origen coincide con el nodo destino.
		assertEquals("0	(5.0)	5	(4.0)	1", graphD.path(0, 1));
		assertEquals("0	(13.0)	3	(3.0)	4	(12.0)	2", graphD.path(0, 2));
		assertEquals("0	(Infinity)	3", graphD.path(0, 3));
		assertEquals("0	(13.0)	3	(3.0)	4", graphD.path(0, 4));
		assertEquals("0	(Infinity)	5", graphD.path(0, 5));
		
				// Desde 1:
		assertEquals("1	(Infinity)	0", graphD.path(1, 0)); 
		assertEquals("1", graphD.path(1, 1)); // El nodo origen coincide con el nodo destino.
		assertEquals("1	(9.0)	4	(12.0)	2", graphD.path(1, 2));
		assertEquals("1	(9.0)	4	(12.0)	2	(2.0)	3", graphD.path(1, 3));
		assertEquals("1	(Infinity)	4", graphD.path(1, 4));
		assertEquals("1	(Infinity)	5", graphD.path(1, 5));
		
				// Desde 2:
		assertEquals("2	(Infinity)	0", graphD.path(2, 0)); 
		assertEquals("2	(Infinity)	1", graphD.path(2, 1));
		assertEquals("2", graphD.path(2, 2)); // El nodo origen coincide con el nodo destino.
		assertEquals("2	(Infinity)	3", graphD.path(2, 3));
		assertEquals("2	(2.0)	3	(3.0)	4", graphD.path(2, 4));
		assertEquals("2	(Infinity)	5", graphD.path(2, 5));
		
				// Desde 3:
		assertEquals("3	(Infinity)	0", graphD.path(3, 0)); 
		assertEquals("3	(3.0)	4	(12.0)	2	(1.0)	1", graphD.path(3, 1));
		assertEquals("3	(3.0)	4	(12.0)	2", graphD.path(3, 2));
		assertEquals("3", graphD.path(3, 3)); // El nodo origen coincide con el nodo destino.
		assertEquals("3	(Infinity)	4", graphD.path(3, 4));
		assertEquals("3	(Infinity)	5", graphD.path(3, 5));
		
				// Desde 4:
		assertEquals("4	(Infinity)	0", graphD.path(4, 0)); 
		assertEquals("4	(12.0)	2	(1.0)	1", graphD.path(4, 1));
		assertEquals("4	(Infinity)	2", graphD.path(4, 2));
		assertEquals("4	(12.0)	2	(2.0)	3", graphD.path(4, 3));
		assertEquals("4", graphD.path(4, 4)); // El nodo origen coincide con el nodo destino.
		assertEquals("4	(Infinity)	5", graphD.path(4, 5));
		
				// Desde 5:
		assertEquals("5	(Infinity)	0", graphD.path(5, 0)); 
		assertEquals("5	(Infinity)	1", graphD.path(5, 1));
		assertEquals("5	(4.0)	1	(9.0)	4	(12.0)	2", graphD.path(5, 2));
		assertEquals("5	(4.0)	1	(9.0)	4	(12.0)	2	(2.0)	3", graphD.path(5, 3));
		assertEquals("5	(4.0)	1	(9.0)	4", graphD.path(5, 4));
		assertEquals("5", graphD.path(5, 5)); // El nodo origen coincide con el nodo destino.
		
		// Probamos con parámetros null:
		assertEquals(null, graphD.path(null, 5));
		assertEquals(null, graphD.path(0, null));
		assertEquals(null, graphD.path(null, null));
		
	}
	
	@Test
	public void testRecorridoProfundidad() {
		// Preparamos el grafo:

		// Añadimos los nodos:		
		graphD.addNode(0);
		graphD.addNode(1);
		graphD.addNode(2);
		graphD.addNode(3);
		graphD.addNode(4);
		graphD.addNode(5);

		// Añadimos las aristas:		
		graphD.addEdge(0, 1, 11);
		graphD.addEdge(0, 3, 13);
		graphD.addEdge(0, 5, 5);

		graphD.addEdge(1, 4, 9);

		graphD.addEdge(2, 1, 1);
		graphD.addEdge(2, 3, 2);

		graphD.addEdge(3, 4, 3);

		graphD.addEdge(4, 2, 12);

		graphD.addEdge(5, 1, 4);
		graphD.addEdge(5, 4, 54);

		// Llamamos al método floyd():
		graphD.floyd();
		
		// Comprobamos los resultados para el grafo dado:
		assertEquals("0	1	4	2	3	5	", graphD.recorridoProfundidad(0));
		assertEquals("1	4	2	3	", graphD.recorridoProfundidad(1));
		assertEquals("2	1	4	3	", graphD.recorridoProfundidad(2));
		assertEquals("3	4	2	1	", graphD.recorridoProfundidad(3));
		assertEquals("4	2	1	3	", graphD.recorridoProfundidad(4));
		assertEquals("5	1	4	2	3	", graphD.recorridoProfundidad(5));
		
		// Comprobamos en caso de pasar null por parámetro:
		assertEquals("", graphD.recorridoProfundidad(null));
		
		// Comprobamos en caso de que no exista el nodo que pasamos por parámetro:
		assertEquals("", graphD.recorridoProfundidad(8));
		assertEquals("", graphD.recorridoProfundidad(-3));
		
		

	}

}
