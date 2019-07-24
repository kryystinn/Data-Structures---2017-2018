package p1Algoritmia;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlgorithmsTest {
	
	Algorithms al = new Algorithms();

	@Test
	public void testLinear() {
		al.linear(10);
	}
	
	@Test
	public void testQuadratic() {
		al.quadratic(10);
	}
	
	@Test
	public void testCubic() {
		al.cubic(10);
	}
	
	@Test
	public void testFactorial() {
		assertEquals(6, al.factorial(3));
		assertEquals(362880, al.factorial(9));
	}
	
	@Test
	public void testPow2Iter() {
		assertEquals(1, al.pow2Iter(0));
		assertEquals(128, al.pow2Iter(7));
		assertEquals(256, al.pow2Iter(8));
		assertEquals(1024, al.pow2Iter(10));
	}
	
	@Test
	public void testPow2Rec1() {
		assertEquals(1, al.pow2Rec1(0));
		assertEquals(128, al.pow2Rec1(7));
		assertEquals(256, al.pow2Rec1(8));
		assertEquals(1024, al.pow2Rec1(10));
	}
	
	@Test
	public void testPow2Rec2() {
		assertEquals(1, al.pow2Rec2(0));
		assertEquals(128, al.pow2Rec2(7));
		assertEquals(256, al.pow2Rec2(8));
		assertEquals(1024, al.pow2Rec2(10));
	}
	
	@Test
	public void testPow2Rec3() {
		assertEquals(1, al.pow2Rec2(0));
		assertEquals(128, al.pow2Rec2(7));
		assertEquals(256, al.pow2Rec2(8));
		assertEquals(1024, al.pow2Rec2(10));
	}
	
	@Test
	public void testPow2Rec4() {
		assertEquals(1, al.pow2Rec2(0));
		assertEquals(128, al.pow2Rec2(7));
		assertEquals(256, al.pow2Rec2(8));
		assertEquals(1024, al.pow2Rec2(10));
	}

}
