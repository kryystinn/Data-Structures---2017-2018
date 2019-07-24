package p4Hash;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClosedHashTableTest {
	ClosedHashTable<Integer> tablelineal = new ClosedHashTable<Integer>(10, 0.5, 0.16, (byte) 0);
	ClosedHashTable<Integer> tablecuadratic = new ClosedHashTable<Integer>(10, 0.5, 0.16, (byte) 1);

	@Test
	public void testAddSinRedispersiones() {
		// LINEAL:
		
		// Añadimos un elemento
			// Comprobamos el tamaño inicial de la tabla y el número inicial de elementos
		assertEquals(11, tablelineal.getSize());
		assertEquals(0, tablelineal.getNumOfElems());
		
		assertTrue(tablelineal.add(2));
		assertEquals(1, tablelineal.getNumOfElems());
		
		// Añadimos normalmente otro elemento
		assertTrue(tablelineal.add(6));
		assertEquals(2, tablelineal.getNumOfElems());
		
		// Intentamos añadir null
		assertFalse(tablelineal.add(null));
		assertEquals(2, tablelineal.getNumOfElems());
		
		// Intentamos añadir elemento repetido
		assertFalse(tablelineal.add(6));
		assertEquals(2, tablelineal.getNumOfElems());

		// CUADRÁTICA:

		// Añadimos un elemento
		// Comprobamos el tamaño inicial de la tabla y el número inicial de elementos
		assertEquals(11, tablecuadratic.getSize());
		assertEquals(0, tablecuadratic.getNumOfElems());

		assertTrue(tablecuadratic.add(2));
		assertEquals(1, tablecuadratic.getNumOfElems());

		// Añadimos normalmente otro elemento
		assertTrue(tablecuadratic.add(6));
		assertEquals(2, tablecuadratic.getNumOfElems());

		// Intentamos añadir null
		assertFalse(tablecuadratic.add(null));
		assertEquals(2, tablecuadratic.getNumOfElems());

		// Intentamos añadir elemento repetido
		assertFalse(tablecuadratic.add(6));
		assertEquals(2, tablecuadratic.getNumOfElems());

//		System.out.println(tablelineal.toString());
//		System.out.println(tablecuadratic.toString());

	}

	@Test
	public void testAddConRedispersiones() {
		// LINEAL:
		assertTrue(tablelineal.add(2));
		assertTrue(tablelineal.add(13));
		assertTrue(tablelineal.add(24));
//		System.out.println(tablelineal.toString());
		
		// CUADRÁTICA:
		assertTrue(tablecuadratic.add(3));
		assertTrue(tablecuadratic.add(14));
		assertTrue(tablecuadratic.add(25));
//		System.out.println(tablecuadratic.toString());
	}

	@Test
	public void testFind() {
		assertTrue(tablelineal.add(2));
		assertTrue(tablelineal.add(3));
		assertTrue(tablelineal.add(5));
		assertTrue(tablelineal.add(9));
		
		// Comprobamos un elemento que sí está en la tabla
		assertEquals(2, tablelineal.find(2), 0.1);
		assertEquals(5, tablelineal.find(5), 0.1);		
		
		// Comprobamos un elemento que no está en la tabla
		assertNull(tablelineal.find(8));
		
		// Intentamos comprobar null
		assertNull(tablelineal.find(null));
	}
	
	@Test
	public void testRemove() {
		assertTrue(tablelineal.add(2));
		assertTrue(tablelineal.add(3));
		assertTrue(tablelineal.add(4));
		assertTrue(tablelineal.add(5));
		assertTrue(tablelineal.add(8));
		assertTrue(tablelineal.add(9));
		
		assertEquals(6, tablelineal.getNumOfElems());
		
		// Borramos un elemento correctamente
		assertTrue(tablelineal.remove(4));
		assertTrue(tablelineal.remove(9));
		
		assertEquals(4, tablelineal.getNumOfElems());
		
		// Intentamos borrar un elemento que no existe
		assertFalse(tablelineal.remove(1));
		assertEquals(4, tablelineal.getNumOfElems());
		
		// Intentamos borrar null
		assertFalse(tablelineal.remove(null));
		assertEquals(4, tablelineal.getNumOfElems());
	}

}
