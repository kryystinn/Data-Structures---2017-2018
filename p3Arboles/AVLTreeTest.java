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
		avlTree.addNode(7); // Raíz.
		avlTree.addNode(9);
		avlTree.addNode(5);

		// Añadimos un nodo correctamente:
		assertTrue(avlTree.addNode(10));
		
		// Añadimos más nodos correctamente que mantengan el árbol en equilibrio:
		assertTrue(avlTree.addNode(6));
		assertTrue(avlTree.addNode(3));
		assertTrue(avlTree.addNode(2));
		assertEquals(4, avlTree.getRaiz().getHeight());
		
		// Intentamos añadir un nodo null:
		assertFalse(avlTree.addNode(null));	
		
		// Intentamos añadir un nodo que ya existe en el árbol.
		assertFalse(avlTree.addNode(2));
		assertFalse(avlTree.addNode(7));
		assertFalse(avlTree.addNode(5));
	}
		
	
	@Test
	public void testRemove(){
		// Intentamos eliminar un nodo de un árbol vacío.
		assertFalse(avlTree.removeNode(1));
		
		avlTree.addNode(7); // Raíz.
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
		avlTree.addNode(7); // Raíz.
		avlTree.addNode(9);
		avlTree.addNode(5);
		avlTree.addNode(3);	
		assertEquals(3, avlTree.getRaiz().getHeight());
		
		// Añadimos otro nodo correctamente que no mantenga el árbol en equilibrio y tenga que hacer
		// una rotación simple a la izquierda.
		assertTrue(avlTree.addNode(1));
		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de añadir el nodo.
	}
	
	@Test
	public void testRSD(){
		avlTree.addNode(7); // Raíz.
		avlTree.addNode(9);
		avlTree.addNode(5);
		avlTree.addNode(10);	
		assertEquals(3, avlTree.getRaiz().getHeight());
		
		// Añadimos otro nodo correctamente que no mantenga el árbol en equilibrio y tenga que hacer
		// una rotación simple a la derecha.
		assertTrue(avlTree.addNode(15));
		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de añadir el nodo.
//		System.out.println(avlTree.toString());
	}
	
	
	@Test
	public void testRDI(){
		avlTree.addNode(6); // Raíz.
		avlTree.addNode(8);
		avlTree.addNode(4);
		avlTree.addNode(1);
		
		// Añadimos otro nodo correctamente que no mantenga el árbol en equilibrio y tenga que hacer
		// una rotación doble a la izquierda.
		assertTrue(avlTree.addNode(2));

		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de añadir el nodo.
//		System.out.println(avlTree.toString());
	}

	@Test
	public void testRDD(){
		avlTree.addNode(2); // Raíz.
		avlTree.addNode(0);
		avlTree.addNode(4);
		avlTree.addNode(8);
		
		// Añadimos otro nodo correctamente que no mantenga el árbol en equilibrio y tenga que hacer
		// una rotación doble a la derecha.
		assertTrue(avlTree.addNode(6));
		System.out.println(avlTree.toString());

		assertEquals(3, avlTree.getRaiz().getHeight()); // Vemos que tiene la misma altura que antes de añadir el nodo.
//		System.out.println(avlTree.toString());
	}

}
