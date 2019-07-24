package p3Arboles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BSTreeTest {
	BSTree<Integer> bstTree;
	
	@Before
	public void setUp() throws Exception{
		bstTree = new BSTree<Integer>();
		
	}

	@Test
	public void testAddNode() {
		
		bstTree.addNode(4);
		bstTree.addNode(6);
		bstTree.addNode(8);
		bstTree.addNode(1);
		bstTree.addNode(2);
		bstTree.addNode(0);
		
		// Añadimos un nodo correctamente:
		assertEquals(true, bstTree.addNode(3));
		
		// Intentamos añadir un nodo ya existente:
		assertEquals(false, bstTree.addNode(2));
		assertEquals(false, bstTree.addNode(0));
		
		// Intentamos añadir un nodo null:
		assertEquals(false, bstTree.addNode(null));
		
	}
	
	@Test
	public void testAddNode2() {
		// Probamos con el nodo raíz únicamente:
		assertEquals(true, bstTree.addNode(9));
		
		// Probamos a volver a añadir el nodo raíz:
		assertEquals(false, bstTree.addNode(9));
	}
	
	@Test
	public void testSearchNode() {
		bstTree.addNode(4);
		bstTree.addNode(6);
		bstTree.addNode(8);
		bstTree.addNode(1);
		bstTree.addNode(2);
		bstTree.addNode(0);
		
		// Probamos con nodos sí existentes:
		assertTrue(bstTree.searchNode(4).equals(4));
		assertTrue(bstTree.searchNode(0).equals(0));
		
		// Probamos con nodos no existentes:
		assertEquals(null, bstTree.searchNode(10));
		assertEquals(null, bstTree.searchNode(-5));
		
		// Probamos con el nodo null:
		assertEquals(null, bstTree.searchNode(null));
		
	}
	
	@Test
	public void testPostOrden() {
		// Preparamos un árbol:
		bstTree.addNode(7);
		bstTree.addNode(4);
		bstTree.addNode(9);
		bstTree.addNode(2);	
		bstTree.addNode(6);	
		bstTree.addNode(1);
		bstTree.addNode(3);
		bstTree.addNode(5);
		bstTree.addNode(8);
		bstTree.addNode(10);			
		
		System.out.println(bstTree.toString());
		
		// Comprobamos:
		assertEquals(new String ("1	3	2	5	6	4	8	10	9	7	"), bstTree.postOrder());
	}
	
	@Test
	public void testPreOrden() {
		// Preparamos un árbol:
		bstTree.addNode(7);
		bstTree.addNode(4);
		bstTree.addNode(9);
		bstTree.addNode(2);	
		bstTree.addNode(6);	
		bstTree.addNode(1);
		bstTree.addNode(3);
		bstTree.addNode(5);
		bstTree.addNode(8);
		bstTree.addNode(10);			
		
		System.out.println(bstTree.toString());
		
		// Comprobamos:
		assertEquals(new String ("7	4	2	1	3	6	5	9	8	10	"), bstTree.preOrder());
	}
	
	@Test
	public void testInOrden() {
		// Preparamos un árbol:
		bstTree.addNode(7);
		bstTree.addNode(4);
		bstTree.addNode(9);
		bstTree.addNode(2);	
		bstTree.addNode(6);	
		bstTree.addNode(1);
		bstTree.addNode(3);
		bstTree.addNode(5);
		bstTree.addNode(8);
		bstTree.addNode(10);			
		
		System.out.println(bstTree.toString());
		
		// Comprobamos:
		assertEquals(new String ("1	2	3	4	5	6	7	8	9	10	"), bstTree.inOrder());
	}
	
	@Test
	public void testRemoveNode() {
		// Intentamos borrar un nodo inexistente de un árbol vacío.
		assertFalse(bstTree.removeNode(5));
		
		bstTree.addNode(4);
		bstTree.addNode(5);
		bstTree.addNode(6);
		bstTree.addNode(8);
		bstTree.addNode(1);
		bstTree.addNode(2);
		bstTree.addNode(0);
		bstTree.addNode(8);
		bstTree.addNode(7);
		bstTree.addNode(10);
		
		// Borramos un nodo correctamente:
		assertEquals(true, bstTree.removeNode(10));

		// Borramos un nodo con un hijo a la izquierda.
		assertTrue(bstTree.removeNode(8));

		// Borramos un nodo con un hijo a la derecha.
		assertTrue(bstTree.removeNode(6));
		System.out.println(bstTree.toString());

		// Borramos un nodo con dos hijos:
		assertEquals(true, bstTree.removeNode(1));
		
		System.out.println(bstTree.toString());
		
		
		// Intentamos borrar un nodo inexistente:
		assertEquals(false, bstTree.removeNode(10));
		assertEquals(false, bstTree.removeNode(3));
		
		// Intentamos borrar un nodo ya eliminado:
		assertEquals(false, bstTree.removeNode(6));
		
		// Intentamos borrar un nodo null:
		assertEquals(false, bstTree.removeNode(null));
	}
	
	@Test
	public void testRemoveNode2() {
		// Probamos con el nodo raíz únicamente:
		bstTree.addNode(7);
		assertEquals(true, bstTree.removeNode(7));
		
		
		// Probamos a volver a eliminar el nodo raíz:
		assertEquals(false, bstTree.removeNode(7));
	}

}
