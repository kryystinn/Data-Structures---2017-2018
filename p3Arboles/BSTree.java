package p3Arboles;

/** 
 * @author Cristina 
 * @version 2017-18 
 */ 
public class BSTree <T extends Comparable<T>>{ 


	private BSTNode<T> raiz; 
	private T searchedNode;
	

	/** 
	 * @param node 
	 * El objeto comparable que tiene que insertar 
	 * 
	 * @return verdadero cuando lo inserta, falso cuando no lo hace (ya existía u otra causa) 
	 */ 
	public boolean addNode(T node) {
		if (node == null)
			return false;
		
		if (searchNode(node) != null)
			return false;
		
		if (raiz == null)
			raiz = new BSTNode<T>(node);

		else
			addNodeRec(raiz, node);

		return true;
	}
	
	
	private BSTNode<T> addNodeRec(BSTNode<T> raiz, T nodo){
		if (raiz == null)
			raiz = new BSTNode<T>(nodo);
		
		else if (nodo.compareTo(raiz.getInfo()) < 0)
			raiz.setLeft (addNodeRec(raiz.getLeft(), nodo));

		else if (nodo.compareTo(raiz.getInfo()) > 0)
			raiz.setRight(addNodeRec(raiz.getRight(), nodo));
		
		else
			raiz.setInfo(nodo);		
			
		return raiz;
	}



	/** 
	 * @param node 
	 * El objeto comparable que se busca 
	 * @return El objeto que se busca (completo) si lo encuentra. (null) si no 
	 * lo encuentra 
	 */ 
	public T searchNode(T node) {
		if (node == null)
			return null;
		
		else if (searchNodeRec(raiz, node) != null) {
			searchedNode = node;
			return searchedNode;
		}
		
		return null;
	}
	
	private T searchNodeRec(BSTNode<T> raiz, T nodo) {
		if (raiz == null)
			return null;
		
		else {
			if (nodo.compareTo(raiz.getInfo()) < 0)
				searchedNode = searchNodeRec(raiz.getLeft(), nodo);
			else if (nodo.compareTo(raiz.getInfo()) > 0)
				searchedNode = searchNodeRec(raiz.getRight(), nodo);
			else
				searchedNode = raiz.getInfo();
		}
		return searchedNode;
	}
	
	
	
	/** 
	 * Muestra por pantalla el recorrido en pre-orden (izquierda-derecha) y lo devuelve en un String (separados por tabuladores) 
	 */ 
	public String preOrder() {
		return recorridoPreOrder(raiz);
	} 
	
	private String recorridoPreOrder(BSTNode<T> raiz) {
		String cad = "";
		if (raiz != null) {
			cad += raiz.toString() + "\t";
			cad += recorridoPreOrder(raiz.getLeft());
			cad += recorridoPreOrder(raiz.getRight());
		}		
		return cad;
	}
	
	
	/** 
	 * Muestra por pantalla el recorrido en post-orden (izquierda-derecha) y lo devuelve en un String (separados por tabuladores) 
	 */ 
	public String postOrder() {
		return recorridoPostOrden(raiz);
	} 
	
	private String recorridoPostOrden(BSTNode<T> raiz) {
		String cad = "";
		if (raiz != null) {
			cad += recorridoPostOrden(raiz.getLeft());
			cad += recorridoPostOrden(raiz.getRight());
			cad += raiz.toString() + "\t";
		}
		
		
		return cad;
	}
	
	
	/** 
	 * Muestra por pantalla el recorrido en in-orden (izquierda-derecha) y lo devuelve en un String (separados por tabuladores) 
	 */ 
	public String inOrder() {
		return recorridoInOrder(raiz);
	} 
	
	
	private String recorridoInOrder(BSTNode<T> raiz) {
		String cad = "";
		if (raiz != null) {
			cad += recorridoInOrder(raiz.getLeft());
			cad += raiz.toString() + "\t";
			cad += recorridoInOrder(raiz.getRight());
		}
		
		return cad;
	}
	
	
	/** 
	 * @param node El objeto que se quiere borrar 
	 * @return true si lo ha borrado, false en caso contrario (no existía) 
	 */ 
	public boolean removeNode (T node){
		if (raiz == null)
			return false;
		
		else
			try {
				raiz = removeNodeRec(raiz, node);
			}catch (RuntimeException e) {
				return false;
			}
		return true;
	} 

	private BSTNode<T> removeNodeRec(BSTNode<T> raiz, T nodo){
		if (raiz == null)
			throw new RuntimeException("El nodo no existe.");
		
		// Si la clave que queremos eliminar es menor que el nodo.
		else if (nodo.compareTo(raiz.getInfo()) < 0)
			raiz.setLeft(removeNodeRec(raiz.getLeft(), nodo));
		
		// Si la clave que queremos eliminar es mayor que el nodo.
		else if (nodo.compareTo(raiz.getInfo()) > 0)
			raiz.setRight(removeNodeRec(raiz.getRight(), nodo));
		
		// Una vez encontramos el nodo a eliminar.
		else {
			// No tiene hijos.
			if (raiz.getLeft() == null && raiz.getRight() == null)
				return null;
			
			// Tiene un hijo por la derecha.
			if (raiz.getRight() != null && raiz.getLeft() == null)
				return raiz.getRight();
			
			// Tiene un hijo por la izquierda.
			else if (raiz.getRight() == null && raiz.getLeft() != null)
				return raiz.getLeft();
			
			// Tiene dos hijos.
			else {
				raiz.setInfo(getMax(raiz.getLeft()));
				raiz.setLeft(removeNodeRec(raiz.getLeft(), raiz.getInfo()));
			}
		}
		return raiz;
	}
	
	protected T getMax(BSTNode<T> raiz) {
		if (raiz == null)
			return null;
		
		else if (raiz.getRight() == null)
			return raiz.getInfo();
		else
			return getMax(raiz.getRight());
	}

	

	public String toString() { 
		return tumbarArbol(raiz, 0); 
	} 
	
	protected BSTNode<T> getRaiz(){
		return raiz;
	}
	
	protected void setRoot(BSTNode<T> nodo) {
		raiz = nodo;
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
