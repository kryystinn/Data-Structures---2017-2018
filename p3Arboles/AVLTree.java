package p3Arboles;

/** 
 * Clase derivada de BSTree a�adiendo funcionalidad de AVL 
 * @author N�stor 
 * @version 2017-18 
 */ 
public class AVLTree <T extends Comparable<T> >extends BSTree <T>{ 
	private AVLNode<T> raiz; 


	/** 
	 * Constructor  
	 */ 
	public AVLTree() {
		super();
	} 


	
	/* (non-Javadoc) 
	 * @see BSTree#add(java.lang.Comparable) 
	 * Redefine inserci�n para funcionalidad AVL 
	 */ 
	public boolean addNode (T info){
		if (info == null)
			return false;
		
		
		else
			try {
				
			raiz = addRec((AVLNode<T>) raiz, info);
			
			}catch (Exception e) {
				return false;
			}
		
		
		return true;
	} 
	

	private AVLNode<T> addRec(AVLNode<T> theRoot, T element){
		// Si la ra�z es null.
		if (theRoot == null)
			return new AVLNode<T>(element);

		// Si la clave que queremos a�adir YA EXISTE en el �rbol.
		if (element.compareTo(theRoot.getInfo()) == 0)
			throw new RuntimeException("El elemento ya existe.");

		// Si la clave que queremos a�adir es menor que el nodo.
		if (element.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(addRec(theRoot.getLeft(), element));

		// Si la clave que queremos a�adir es mayor que el nodo.
		else
			theRoot.setRight(addRec(theRoot.getRight(), element));


		// Actualizar el nodo para saber si es necesario equilibrarlo.
		return(updateAndBalanceIfNecesary(theRoot));
	}

	
	
	/** 
	 * @param nodo el �rbol que se quiere actualizar Height, BF y balancear si fuese necesario 
	 * @return la ra�z del �rbol por si ha cambiado 
	 */ 
	private AVLNode<T> updateAndBalanceIfNecesary  (AVLNode<T> theRoot){
		// Se actualiza la altura de la ra�z.
		theRoot.updateHeight();

		// Cuando el sub�rbol izquierdo es mayor.
		if (theRoot.getBF() == -2){
			if (theRoot.getLeft().getBF() == 1)
				theRoot = doubleLeftRotation (theRoot);
			else
				theRoot = singleLeftRotation (theRoot);
		}

		// Cuando el sub�rbol derecho es mayor.
		else if (theRoot.getBF() == 2)
		{
			if (theRoot.getRight().getBF() == -1)
				theRoot = (doubleRightRotation (theRoot));
			else
				theRoot = (singleRightRotation (theRoot));
		}

		theRoot.updateHeight();
		return (theRoot);
	}
	
	
	
	
	/** 
	 * @param nodo la ra�z del �rbol a balancear con rotaci�n simple 
	 * @return la ra�z del nuevo �rbol que ha cambiado 
	 */ 
	private AVLNode<T> singleLeftRotation (AVLNode<T> nodo){
		AVLNode<T> aux = nodo.getLeft();

		nodo.setLeft(aux.getRight());
		nodo.updateHeight();
		aux.setRight(nodo);
		aux.updateHeight();

		return aux;
	} 



	/** 
	 * @param nodo la ra�z del �rbol a balancear con rotaci�n doble 
	 * @return la ra�z del nuevo �rbol que ha cambiado 
	 */ 
	private AVLNode<T> doubleLeftRotation(AVLNode<T> nodo) {		
		AVLNode<T> aux = nodo.getLeft().getRight();

		nodo.getLeft().setRight(aux.getLeft());
		aux.setLeft(nodo.getLeft());
		nodo.setLeft(aux.getRight());
		aux.setRight(nodo);

		updateAndBalanceIfNecesary(aux);
		aux.getRight().updateHeight();
		aux.getLeft().updateHeight();
		aux.updateHeight();
		return aux;
	} 



	/** 
	 * @param nodo la ra�z del �rbol a balancear con rotaci�n simple 
	 * @return la ra�z del nuevo �rbol que ha cambiado 
	 */ 
	private AVLNode<T> singleRightRotation (AVLNode<T> nodo){
		AVLNode<T> aux = nodo.getRight();

		nodo.setRight(aux.getLeft());
		nodo.updateHeight();
		aux.setLeft(nodo);
		aux.updateHeight();
		
		return aux;
	} 



	/** 
	 * @param nodo la ra�z del �rbol a balancear con rotaci�n doble 
	 * @return la ra�z del nuevo �rbol que ha cambiado 
	 */ 
	private AVLNode<T> doubleRightRotation(AVLNode<T> nodo) {
		AVLNode<T> aux = (AVLNode<T>) nodo.getRight().getLeft();

		nodo.getRight().setLeft(aux.getRight());
		aux.setRight(nodo.getRight());
		nodo.setRight(aux.getLeft());
		aux.setLeft(nodo);

		updateAndBalanceIfNecesary(aux);
		aux.getRight().updateHeight();
		aux.getLeft().updateHeight();
		aux.updateHeight();

		return aux;	
	} 



	/* (non-Javadoc) 
	 * @see BSTree#remove(java.lang.Comparable) 
	 * Redefinici�n para incluir caracter�sticas AVL 
	 */ 
	public boolean remove (T info){
		if (info == null)
			return false;


		else {
			try {

				raiz = removeRec((AVLNode<T>) raiz, info);

			}catch (RuntimeException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	private AVLNode<T> removeRec(AVLNode<T> theRoot, T element){
		// Si el nodo no existe.
		if (theRoot == null) {
			throw new RuntimeException("El nodo a eliminar no existe.");
		}

		// Si la clave que queremos eliminar es menor que el nodo.
		else if (element.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(removeRec(theRoot.getLeft(), element));

		// Si la clave que queremos eliminar es mayor que el nodo.
		else if (element.compareTo(theRoot.getInfo()) > 0)
			theRoot.setRight(removeRec(theRoot.getRight(), element));

		// Una vez encontramos el nodo a eliminar.
		else {
			// No tiene hijos.
			if (theRoot.getLeft() == null && theRoot.getRight() == null)
				return null;

			// Tiene un hijo por la izquierda.
			else if (theRoot.getLeft() != null && theRoot.getRight() == null)
				return theRoot.getLeft();

			// Tiene un hijo por la derecha.
			else if (theRoot.getRight() != null && theRoot.getLeft() == null)
				return theRoot.getRight();

			// Tiene dos hijos.
			else {
				theRoot.setInfo(getMax(theRoot.getLeft()));
				theRoot.setLeft(removeRec(theRoot.getLeft(), theRoot.getInfo()));
			}
		}
		return (updateAndBalanceIfNecesary(theRoot));
	}


	public AVLNode<T> getRaiz() {
		return raiz;
	}	
	
	
	public String toString()
	{
		return tumbarArbol(getRaiz(), 0);
	}
	
	/** 
	 * Genera un String con el �rbol "tumbado" (la ra�z a la izquierda y las ramas hacia la derecha) 
	 * Es un recorrido InOrden-derecha-izquierda, tabulando para mostrar los distintos niveles 
	 * Utiliza el toString de la informaci�n almacenada 
	 *   
	 * @param p La ra�z del �rbol a mostrar tumbado 
	 * @param esp El espaciado en n�mero de tabulaciones para indicar la profundidad 
	 * @return El String generado 
	 */ 
	protected String tumbarArbol(BSTNode<T> p, int esp) { 
		StringBuilder cadena = new StringBuilder(); 


		if (p != null) { 
			cadena.append(tumbarArbol(p.getRight(), esp + 1)); 
			for (int i = 0; i < esp; i++) 
				cadena.append("\t"); 
			cadena.append(p + "\n"); 
			cadena.append(tumbarArbol(p.getLeft(), esp + 1)); 
		} 
		return cadena.toString(); 
	} 

	}
