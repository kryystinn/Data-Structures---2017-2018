package p3Arboles;

/** 
 * Clase derivada de BSTree añadiendo funcionalidad de AVL 
 * @author Néstor 
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
	 * Redefine inserción para funcionalidad AVL 
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
		// Si la raíz es null.
		if (theRoot == null)
			return new AVLNode<T>(element);

		// Si la clave que queremos añadir YA EXISTE en el árbol.
		if (element.compareTo(theRoot.getInfo()) == 0)
			throw new RuntimeException("El elemento ya existe.");

		// Si la clave que queremos añadir es menor que el nodo.
		if (element.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(addRec(theRoot.getLeft(), element));

		// Si la clave que queremos añadir es mayor que el nodo.
		else
			theRoot.setRight(addRec(theRoot.getRight(), element));


		// Actualizar el nodo para saber si es necesario equilibrarlo.
		return(updateAndBalanceIfNecesary(theRoot));
	}

	
	
	/** 
	 * @param nodo el árbol que se quiere actualizar Height, BF y balancear si fuese necesario 
	 * @return la raíz del árbol por si ha cambiado 
	 */ 
	private AVLNode<T> updateAndBalanceIfNecesary  (AVLNode<T> theRoot){
		// Se actualiza la altura de la raíz.
		theRoot.updateHeight();

		// Cuando el subárbol izquierdo es mayor.
		if (theRoot.getBF() == -2){
			if (theRoot.getLeft().getBF() == 1)
				theRoot = doubleLeftRotation (theRoot);
			else
				theRoot = singleLeftRotation (theRoot);
		}

		// Cuando el subárbol derecho es mayor.
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
	 * @param nodo la raíz del árbol a balancear con rotación simple 
	 * @return la raíz del nuevo árbol que ha cambiado 
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
	 * @param nodo la raíz del árbol a balancear con rotación doble 
	 * @return la raíz del nuevo árbol que ha cambiado 
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
	 * @param nodo la raíz del árbol a balancear con rotación simple 
	 * @return la raíz del nuevo árbol que ha cambiado 
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
	 * @param nodo la raíz del árbol a balancear con rotación doble 
	 * @return la raíz del nuevo árbol que ha cambiado 
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
	 * Redefinición para incluir características AVL 
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
	 * Genera un String con el árbol "tumbado" (la raíz a la izquierda y las ramas hacia la derecha) 
	 * Es un recorrido InOrden-derecha-izquierda, tabulando para mostrar los distintos niveles 
	 * Utiliza el toString de la información almacenada 
	 *   
	 * @param p La raíz del árbol a mostrar tumbado 
	 * @param esp El espaciado en número de tabulaciones para indicar la profundidad 
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
