package p1Algoritmia;

public class Algorithms {
	private static final long SLEEP_TIME = 1;

	public static void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 *  Ejecuci�n de un algoritmo de complejidad lineal.
	 *  @param n, de tipo int.
	 */
	public void linear (int n) {
		for (int i=0; i<n; i++){
			doNothing();
		}
	}

	/*
	 *  Ejecuci�n de un algoritmo de prueba de complejidad cuadr�tica.
	 *  @param n, de tipo int.
	 */
	public void quadratic (int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				doNothing();
			}
		}
	}

	/*
	 * Ejecuci�n de un algoritmo de prueba de complejidad c�bica.
	 * @param n, de tipo int.
	 */
	public void cubic (int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++){
					doNothing();
				}
			}
		}
	}
	
	/*
	 * Ejecuci�n de un algoritmo que calcula el factorial de un n�mero dado.
	 * @param n, de tipo int.
	 */
	public int factorial (int n) {
		doNothing();
		  if (n == 0 || n<0 || n ==1)
		    return 1;
		  else
			  return n * factorial(n-1);
		}

	/*
	 * Ejecuci�n de un algoritmo que calcula la potencia de 2 para un exponente dado
	 * a trav�s de los par�metros, de forma iterativa.
	 * @param n, de tipo int.
	 */
	public long pow2Iter (int n) {
		long x = 0;
			for (int i = 0; i <= n; i++) {
				x = (long) Math.pow(2, n);
			}
		return x;
	}
	
	
	/*
	 * Ejecuci�n de un algoritmo que calcula la potencia de 2 para un exponente dado
	 * a trav�s de los par�metros, de forma recursiva.
	 * @param n, de tipo int.
	 */
	public long pow2Rec1 (int n) {
		doNothing();
		if (n == 0)
	        return 1;
	    else
	        return 2 * pow2Rec1(n-1);
		
	}

	/*
	 * Ejecuci�n de un algoritmo que calcula la potencia de 2 para un exponente dado
	 * a trav�s de los par�metros, de otra forma recursiva diferente.
	 * @param n, de tipo int.
	 */
	public long pow2Rec2 (int n) {
		doNothing();
		if (n == 0)
			return 1;
		else {
			return pow2Rec2(n-1) + pow2Rec2(n-1);
		}

	}
	
	/*
	 * Ejecuci�n de un algoritmo que calcula la potencia de 2 para un exponente dado
	 * a trav�s de los par�metros, de otra forma recursiva diferente.
	 * @param n, de tipo int.
	 */
	public long pow2Rec3(int n) {
		doNothing();
	    if (n == 0)
	        return 1;
	    else if (n%2 == 0)
	        return pow2Rec3(n / 2) * pow2Rec3(n / 2);
	    else
	        return (2 * pow2Rec3(n / 2) * pow2Rec3(n / 2));
	}
	
	/*
	 * Ejecuci�n de un algoritmo que calcula la potencia de 2 para un exponente dado
	 * a trav�s de los par�metros, de otra forma recursiva diferente.
	 * @param n, de tipo int.
	 */
	public long pow2Rec4(int n) {
		doNothing();
		if (n == 0)
			return 1;
		long pow = pow2Rec4(n/2);
		if (n%2 == 0)
			return (pow * pow);
		else
			return (2 * pow * pow);
	}


}



