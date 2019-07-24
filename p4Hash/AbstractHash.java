package p4Hash; 

/** 
 * @author N�stor 
 * @version 2017-18 
 * 
 * @param <T> 
 */ 
public abstract class AbstractHash <T>{ 

	/** 
	 * Devuelve el n�mero de elementos que contiene la tabla Hash en el momento de la invocaci�n 
	 *  
	 * @return El n�mero de elementos. 
	 */ 
	abstract public int getNumOfElems(); 


	/** 
	 * Devuelve el tama�o de la tabla Hash 
	 *  
	 * @return El tama�o de la tabla, conveniente que sea un n�mero primo 
	 */ 
	abstract public int getSize(); 

	
	
	/** 
	 * Inserta un nuevo elemento en la tabla hash  
	 *   
	 * @param elem Elemento que se inserta 
	 * @return true si lo ha insertado o false en caso contrario 
	 */ 
	abstract public boolean  add(T elem); 

	
	/** 
	 * Busca y devuelve un elemento de la tabla hash  
	 *  
	 * @param elem La clave que se busca,  
	 *   
	 * @return El elemento encontrado si lo encuentra o null en caso contrario 
	 */ 
	abstract public T find(T elem); 
	
	

	/** 
	 * Borra un elemento que se encuentra en la tabla hash 
	 *  
	 * @param elem elemento para borrar 
	 * @return true si lo ha borrado o false en caso contrario 
	 */ 
	abstract public boolean remove(T elem); 

	
	
	/** 
	 * Realiza una redispersi�n (aumentando el tama�o) al n�mero primo mayor que el doble del tama�o actual, y recolocando los elementos 
	 *  
	 * @return true si la realiza, false en caso contrario 
	 */ 
	abstract protected boolean reDispersion (); 
	
	

	/** 
	 * Realiza una redispersi�n inversa (disminuyendo el tama�o) al n�mero primo menor que la mitad del tama�o actual, y recolocando los elementos 
	 * @return true si la realiza, false en caso contrario 
	 */ 
	abstract protected boolean inverseReDispersion(); 
	
	

	/** 
	 * Convierte la tabla a una String que se pueda mostrar de forma "visible" 
	 * 
	 * @return el String con la informaci�n de la tabla hash 
	 */ 
	abstract public String toString (); 
	
	

	/** 
	 * Calcula el resultado de aplicar la funci�n hash sobre el par�metro (versi�n 2017-18) 
	 * Utiliza hashCode() Convierte posibles negativos a positivos 
	 * 
	 * @return el resultado correspondiente 
	 */ 
	protected int fHash(T elem){ 
		int primo=getSize(); 

		return (elem.hashCode()%primo+primo)%primo; 
	} 

	
	
	/** 
	 *  
	 * Calcula si un n�mero positivo es un n�mero primo, los negativos devuelve que no 
	 *  
	 * @param n El n�mero que queremos comprobar 
	 * @return true si es primo, false en caso contrario 
	 */ 
	protected boolean isPositivePrime(int n){ 
		if (n<2 || (n>2 && n%2==0)) 
			return false; 
		if (n<=7) 
			return true; 
		for (int i=3;i*i<=n;i+=2) //impares 
			if (n%i==0) 
				return false; // no es primo 
		return true; 
	} 
	
	
	

	/** 
	 * @param n  El n�mero de partida 
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo encontrado MAYOR que el numero de partida 
	 */ 
	protected int nextPrimeNumber(int n){ 
		if (n<=2)  
			return 2; // No permite primos menores de 2 
		for (;!isPositivePrime(n);n++); 
		return n; 
	} 

	
	
	/** 
	 * @param n El n�mero de partida 
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo encontrado MENOR que el numero de partida 
	 */ 
	protected int previousPrimeNumber (int n){ 
		if (n<=2) // No permite primos menores de 2 
			return 2; 
		for(;!isPositivePrime(n);n--); 
		return n; 
	} 


} 