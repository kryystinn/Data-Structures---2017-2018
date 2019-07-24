package p1Algoritmia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AlgorithmsBenchmark {


	/*
	 * Ejecución de un método que crea un archivo de texto "file" y escribe en él
	 * dos datos separados por una coma.
	 * @param output, de tipo String.
	 */
	public void test0 (String output) {
		FileWriter file = null;
		PrintWriter pw;

		try
		{
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			pw.println("1, 10");
			pw.println("2, 20");
			pw.println("3, 30");
			pw.println("4, 40");


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} 
	}

	public void test1Linear (String output, int StartN, int EndN) {
		FileWriter file = null;
		PrintWriter pw;
		Algorithms alg = new Algorithms();
		long tInicial=0, tFinal=0;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			
			for (int n = StartN; n <= EndN; n++) {
				tInicial = System.currentTimeMillis();
				alg.linear(n); // n varía desde StartN hasta EndN
				tFinal = System.currentTimeMillis();
				pw.println(n + ", " + (tFinal - tInicial));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} 
	}
	
	public void test2Quadratic (String output, int StartN, int EndN) {
		FileWriter file = null;
		PrintWriter pw;
		Algorithms alg = new Algorithms();
		long tInicial=0, tFinal=0;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			
			for (int n = StartN; n <= EndN; n++) {
				tInicial = System.currentTimeMillis();
				alg.quadratic(n); // n varía desde StartN hasta EndN
				tFinal = System.currentTimeMillis();
				pw.println(n + ", " + (tFinal - tInicial));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void test3Cubic (String output, int StartN, int EndN) {
		FileWriter file = null;
		PrintWriter pw;
		Algorithms alg = new Algorithms();
		long tInicial=0, tFinal=0;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			
			for (int n = StartN; n <= EndN; n++) {
				tInicial = System.currentTimeMillis();
				alg.cubic(n); // n varía desde StartN hasta EndN
				tFinal = System.currentTimeMillis();
				pw.println(n + ", " + (tFinal - tInicial));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void test4Factorial (String output, int StartN, int EndN) {
		FileWriter file = null;
		PrintWriter pw;
		Algorithms alg = new Algorithms();
		long tInicial=0, tFinal=0;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			
			for (int n = StartN; n <= EndN; n++) {
				tInicial = System.currentTimeMillis();
				alg.factorial(n); // n varía desde StartN hasta EndN
				tFinal = System.currentTimeMillis();
				pw.println(n + ", " + (tFinal - tInicial));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void test5Pow2 (String output, int StartN, int EndN) {
		FileWriter file = null;
		PrintWriter pw;
		Algorithms alg = new Algorithms();
		long tInicial=0, tFinal=0;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			
			for (int n = StartN; n <= EndN; n++) {
				tInicial = System.currentTimeMillis();
				alg.pow2Iter(n); // n varía desde StartN hasta EndN
				tFinal = System.currentTimeMillis();
				pw.println(n + ", " + (tFinal - tInicial));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}	
	
	public long testAlgorithm(String className, String methodName, int n) {
		long tInicial=0, tFinal=0;

		Class<?> cl;
		try {
			cl = Class.forName(className);
			Object o = cl.newInstance();
			Method m = cl.getMethod(methodName, int.class); 

			tInicial = System.currentTimeMillis();
			m.invoke(o, n);
			tFinal = System.currentTimeMillis();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tFinal-tInicial;
	}
	
	public void testFinal(String output, int startN, int endN, int times, String nombreClase, String nombreMetodo){
		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			long tiempo;
			
			for (int i = startN; i <= endN; i++) {
				tiempo = 0;
				for (int j = 0; j < times; j++) {
					tiempo = tiempo + testAlgorithm(nombreClase, nombreMetodo, i);
				}
			pw.println(i + ", " + (tiempo/times));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

