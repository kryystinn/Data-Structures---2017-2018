package p4Hash; 

/** 
 * @author N�stor 
 * @version 2017-18 
 * 
 */ 
public class ClosedHashTable<T> extends AbstractHash<T> { 
	// IMPORTANTE 
	// No cambiar el nombre ni visibilidad de los atributos protected 

	protected HashNode<T> associativeArray[];  

	private int hashSize; // Tama�o de la tabla, debe ser un n�mero primo (B de teor�a) 
	protected int numElems; // N�mero de elementos en la tabla en cada momento. 
	
	private double fcUP;
	private double fcDOWN;
	
	static final byte LINEAL = 0; // Tipo de exploraci�n en caso de colisi�n, por defecto ser� LINEAL 
	static final byte CUADRATICA = 1; 
	static final byte DOBLEHASH = 2; 

	private byte exploracion; //Exploraci�n que se realizar� en caso de colisi�n (LINEAL por defecto) 


	/** 
	 * Constructor para fijar el tama�o al n�mero primo >= que el par�metro 
	 * @param tam tama�o del Hash, si no es un n�mero primo lo ajusta al primo superior 
	 * @param fcUP Factor de carga l�mite, por encima del cual hay que redispersar (directa) 
	 * @param fcDOWN Factor de carga l�mite, por debajo del cual hay que redispersar (inversa) 
	 * @param expl tipo de exploraci�n (LINEAL=0, CUADRATICA=1, ...), si inv�lido LINEAL 
	 */ 
	@SuppressWarnings("unchecked") 
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, byte expl) { 
		hashSize=nextPrimeNumber(tam); // Establece un tama�o v�lido si tam no es primo 
		this.exploracion = expl; 
		this.fcUP = fcUP;
		this.fcDOWN = fcDOWN;
  		numElems = 0;
		
		associativeArray = (HashNode<T>[]) new HashNode[hashSize]; 
		for(int i = 0; i < hashSize; i++)
		{
			associativeArray[i] = new HashNode<T>();
		}

        
	} 
	

	@Override 
	public int getNumOfElems() { 
		return numElems;
	} 

	
	@Override 
	public int getSize() { 
		return hashSize;
	} 

	
	@Override 
	public boolean add(T elem) { 
		// Si el elemento no es null
		if (elem != null && find(elem) == null) {

			int pos = fHash(elem);
 			int iter = 1;

			while (associativeArray[pos].getStatus() == HashNode.LLENO){
				pos = fExploracion(iter, elem);
				iter++;
				System.out.println("Se ha producido una colisi�n al a�adir " + elem + ".");
			}
			
			associativeArray[pos].setInfo(elem);
			numElems++;
			
			reDispersion();
			return true;					
			}
					
		return false;
	} 
	
	
	  protected int fExploracion(int iter, T elem) {
		// Exploraci�n lineal
		  if(exploracion == LINEAL)
			  return (fHash(elem) + iter) % hashSize;
		  
		  // Exploraci�n cuadr�tica
		  else if(exploracion == CUADRATICA)
			  return (fHash(elem) + iter*iter) % hashSize;
		  
		  // Exploraci�n doble
		  else {
			  int r = previousPrimeNumber(hashSize - 1);
			  int h = (r - Math.abs(elem.hashCode()) % r);
			  return (fHash(elem) + iter * h) % hashSize;
		  }
	  }
	


	  @Override 
	  public T find(T elem) {
		  if(elem != null){

			  int pos = fHash(elem);
			  int iter = 1;
			  int isize = 0;
			  int estado = associativeArray[pos].getStatus();

			  // Mientras la posici�n sea positiva, no nos salgamos de la tabla hash al buscar y el estado no sea vac�o:
			  while(pos >= 0 && isize <= getSize() && estado != HashNode.VACIO){
				// Si la posici�n est� llena y el valor coincide con el que buscamos:
				  if(associativeArray[pos].getStatus() == HashNode.LLENO && associativeArray[pos].getInfo().equals(elem)){
					  return associativeArray[pos].getInfo();
				  }
				  // Sino:
//				  if (associativeArray[pos].getStatus() == HashNode.BORRADO) {
//					  System.out.println("Se ha producido una colisi�n al buscar en borrado el " + elem + ".");
//				  }
//				  if (associativeArray[pos].getStatus() == HashNode.LLENO) {
//					  System.out.println("Se ha producido una colisi�n al buscar en lleno el " + elem + ".");
//				  }
				  pos = fExploracion(iter, elem);
				  iter++;
				  isize++;
			  }
			  return null;
		  }
		  else
			  return null;
	  }


	  @Override 
	  public boolean remove(T elem) {		  
		  if(elem != null && find(elem) != null){
			  int pos = fHash(elem);
			  int iter = 1;

			  // Mientras no est� vac�a la posici�n:
			  while(associativeArray[pos].getStatus() != HashNode.VACIO){

				  // Si la posici�n est� llena y el valor coincide con el que queremos eliminar:
				  if(associativeArray[pos].getStatus() == HashNode.LLENO && associativeArray[pos].getInfo().equals(elem)){
					  associativeArray[pos].remove();
					  numElems--;
					  
					  if(numElems != 0)
						  inverseReDispersion();
					  
					  return true;
				  }

				  else {
					  if (associativeArray[pos].getStatus() == HashNode.BORRADO) {
						  System.out.println("Se ha producido una colisi�n al eliminar en borrado el " + elem + ".");
					  }
					  if (associativeArray[pos].getStatus() == HashNode.LLENO) {
						  System.out.println("Se ha producido una colisi�n al eliminar en lleno el " + elem + ".");
					  }
					  pos = fExploracion(iter, elem);
					  iter++;
					  
				  }
			  }

		  }
		  
		  
		  return false;
	  }


	@Override 
	public String toString (){ 
		StringBuilder cadena=new StringBuilder(); 
		for (int i=0;i< getSize();i++){ 
			cadena.append(associativeArray[i]); 
			cadena.append(";"); 
		} 
		cadena.append("[Size: "); 
		cadena.append(getSize()); 
		cadena.append(" Num.Elems.: "); 
		cadena.append(getNumOfElems()); 
		cadena.append("]"); 
		return cadena.toString(); 
	} 
	
	
	private double getLF() {
		return (double)numElems/hashSize;
	}
	
	private double getfcUP() {
		return fcUP;
	}
	
	private double getfcDOWN() {
		return fcDOWN;
	}

	
	@Override 
	protected boolean reDispersion () {
		// Si el factor de carga es mayor que el factor de l�mite superior de la tabla:
		if (getLF() >= getfcUP()) {
			// Tabla auxiliar similar a la que tenemos:
			HashNode<T>[] aux = associativeArray;
			int sizeAux = hashSize;
			
			// Se actualizan los valores
			int newSize = nextPrimeNumber(sizeAux * 2);
			hashSize = newSize;
			numElems = 0;

			// Se crea la nueva tabla y se rellena con los elementos de la anterior
			associativeArray = (HashNode<T>[]) new HashNode[newSize];
			for (int i = 0; i < newSize; i++) {
				associativeArray[i] = new HashNode<T>();
			}
				for (int i = 0; i < sizeAux; i++) {
					if (aux[i].getStatus() == HashNode.LLENO) {
						add(aux[i].getInfo());
					}
				}
			return true;
			
		}
		return false;

	} 


	@Override 
	protected boolean inverseReDispersion() {
		// Si el factor de carga es menor que el factor de l�mite inferior de la tabla
		if (getLF() < getfcDOWN()) {
			// Tabla auxiliar similar a la que tenemos:
			HashNode<T>[] aux = associativeArray;
			int sizeAux = hashSize;

			// Se actualizan los valores
			int newSize = previousPrimeNumber(sizeAux / 2);
			hashSize = newSize;
			numElems = 0;

			//  Se crea la nueva tabla y se rellena con los elementos de la anterior
			associativeArray = new  HashNode[newSize];
			for (int i = 0; i < newSize; i++) {
				associativeArray[i] = new HashNode<T>();
			}
			for (int i = 0; i < sizeAux; i++) {
				if (aux[i].getStatus() == HashNode.LLENO) {
					add(aux[i].getInfo());
				}
			}

			return true;
		}
		return false;

	} 
} 
