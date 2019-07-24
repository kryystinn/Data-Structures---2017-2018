package p1Algoritmia;


import org.junit.Test;

public class AlgorithmsBenchmarkTest {
	
	AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
	

	
	@Test
	public void test0() {
		ab.test0("file.csv");
	}
	
	@Test
	public void testLinear() {
		ab.test1Linear("fileLinear.csv", 100, 200);
	}
	
	@Test
	public void testQuadratic() {
		ab.test2Quadratic("fileQuadratic.csv", 10, 100);
	}
	
	@Test
	public void testCubic() {
		ab.test3Cubic("fileCubic.csv", 10, 20);
	}
	
	@Test
	public void testFactorial() {
		ab.test4Factorial("fileFactorial.csv", 100, 200);
	}
	
	@Test
	public void test4Pow2() {
		ab.test5Pow2("filePow2Iter.csv", 0, 31);
	}
	
	@Test
	public void testFinalPow2Rec1() {
		ab.testFinal("filePow2Rec1", 0, 62, 5, "p1Algoritmia.Algorithms", "pow2Rec1");
	}
	
	@Test
	public void testFinalPow2Rec2() {
		ab.testFinal("filePow2Rec2", 0, 10, 5, "p1Algoritmia.Algorithms", "pow2Rec2");
	}
	
	@Test
	public void testFinalPow2Rec3() {
		ab.testFinal("filePow2Rec3", 0, 62, 5, "p1Algoritmia.Algorithms", "pow2Rec3");
	}
	
	@Test
	public void testFinalPow2Rec4() {
		ab.testFinal("filePow2Rec4", 0, 62, 5, "p1Algoritmia.Algorithms", "pow2Rec4");
	}


}


