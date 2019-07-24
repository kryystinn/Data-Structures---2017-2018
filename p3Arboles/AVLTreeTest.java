package p3Arboles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {
	AVLTree<Integer> avlTree;
	AVLNode<Integer> raiz;
	
	@Before
	public void setUp() throws Exception{
		avlTree = new AVLTree<Integer>();
	}
	

	@Test
	public void testAdd() {
		avlTree.addNode(7); // Ra�z.
		avlTree.addNode(9);
		avlTree.addNode(5);

		// A�adimos un nodo correctamente:
		assertTrue(avlTree.addNode(10));
		
		// A�adimos m�s nodos correctamente que mantengan el �rbol en equilibrio:
		assertTrue(avlTree.addNode(6));
		assertTrue(avlTree.addNode(3));
		assertTrue(avlTree.addNode(2));
		assertEquals(4, avlTree.getRaiz().getHeight());
		
		// Intentamos a�adir un nodo null:
		assertFalse(avlTree.addNode(null));	
		
		// Intentamos a�adir un nodo que ya existe en el �rbol.
		assertFalse(avlTree.addNode(2));
		assertFalse(avlTree.addNode(7));
		assertFalse(avlTree.addNode(5));
	}
		
	
	@Test
	public void testRemove(){
		// Intentamos eliminar un nodo de un �rbol vac�o.
		assertFalse(avlTree.removeNode(1));
		
		avlTree.addNode(7); // Ra�z.
		avlTree.addNode(9);
		avlTree.addNode(4);
		avlTree.addNode(2);
		avlTree.addNode(5);
		avlTree.addNode(8);
		avlTree.addNode(6);
		avlTree.addNode(10);
		avlTree.addNode(3);
		
		assertEquals(4, avlTree.getRaiz().getHeight());
		
		// Eliminamos un nodo sin hijos.
		assertTrue(avlTree.remove(10));
		
		// Eliminamos un nodo con un hijo a la izquierda.
		assertTrue(avlTree.remove(9));
		
		// Eliminamos un nodo con un hijo a la derecha.
		assertTrue(avlTree.remove(2));
		
		// Eliminamos un nodo con dos hijos.
		assertTrue(avlTree.remove(7));
		assertEquals(3, avlTree.getRaiz().getHeight());
		
		// Intentamos eliminar un nodo null
		assertFalse(avlTree.remove(null));
	}
	
	@Test
	public void testRSI(){
		avlTree.addNode(7); // Ra�z.
		avlTree.addNode(9);
		avlTree.addNode(5);
		avlTree.addNode(3);	
		assertEquals(3, avlTree.getRaiz().getHeight());
		
		// A�adimos otro nodo correctamente que no mantenga el �rbol en equilibrio y tenga que hacer
		// una rotaci�n simple a la izquierda.
		assertTrue(avlTree.addNode(1));
		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de a�adir el nodo.
	}
	
	@Test
	public void testRSD(){
		avlTree.addNode(7); // Ra�z.
		avlTree.addNode(9);
		avlTree.addNode(5);
		avlTree.addNode(10);	
		assertEquals(3, avlTree.getRaiz().getHeight());
		
		// A�adimos otro nodo correctamente que no mantenga el �rbol en equilibrio y tenga que hacer
		// una rotaci�n simple a la derecha.
		assertTrue(avlTree.addNode(15));
		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de a�adir el nodo.
//		System.out.println(avlTree.toString());
	}
	
	
	@Test
	public void testRDI(){
		avlTree.addNode(6); // Ra�z.
		avlTree.addNode(8);
		avlTree.addNode(4);
		avlTree.addNode(1);
		
		// A�adimos otro nodo correctamente que no mantenga el �rbol en equilibrio y tenga que hacer
		// una rotaci�n doble a la izquierda.
		assertTrue(avlTree.addNode(2));

		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de a�adir el nodo.
//		System.out.println(avlTree.toString());
	}

	@Test
	public void testRDD(){
		avlTree.addNode(2); // Ra�z.
		avlTree.addNode(0);
		avlTree.addNode(4);
		avlTree.addNode(8);
		
		// A�adimos otro nodo correctamente que no mantenga el �rbol en equilibrio y tenga que hacer
		// una rotaci�n doble a la derecha.
		assertTrue(avlTree.addNode(6));
		System.out.println(avlTree.toString());

		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de a�adir el nodo.
//		System.out.println(avlTree.toString());
	}

}
