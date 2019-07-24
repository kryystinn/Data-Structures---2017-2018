package p3Arboles;

/** 
 * Clase derivada de BSTNode añadiendo funcionalidad de AVL 
 * @author Néstor 
 * @version 2017-18 
 *  
 */ 
public class AVLNode<T extends Comparable<T>> extends BSTNode<T>  {
	
	private int height;  // Para almacenar la altura del árbol.
	private byte balanceFactor; // Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a partir de la altura de los hijos.
	AVLTree<T> avlTree = new AVLTree<T>();
	
	
	/** 
	 * Llama al padre y añade la información propia 
	 * @param info la información que se mete en el nuevo nodo 
	 */ 
	public AVLNode(T info){
		super(info);
		height = 1;
	} 



	/** 
	 * @return devuelve la altura del árbol del cual es raíz el nodo en cuestión 
	 */ 
	public int getHeight() {
		return height;
	} 



	/** 
	 * @return Devuelve el factor de balance según equilibrio del árbol del cual es raíz 
	 */ 
	public byte getBF() {
		updateHeight();
		int hIzq = 0;
		int hDer = 0;


		if(getRight() != null)
		{
			hDer = getRight().getHeight();
		}

		if(getLeft() != null)
		{
			hIzq = getLeft().getHeight();
		}

		return (byte) (hDer - hIzq);
	}




	/** 
	 * Actualiza la altura del nodo en el árbol utilizando la altura de los hijos 
	 */ 
	protected void updateHeight() {
		if (getRight() == null && getLeft() == null) {
			height = 1;
			balanceFactor = 0;
		}
		
		else if (getRight() != null && getLeft() == null) {
			height = getRight().getHeight() + 1;
		}
		
		else if (getLeft() != null && getRight() == null) {
			height = getLeft().getHeight() + 1;
		}
		
		else {
			if (getRight().height < getLeft().height) {
				height = getLeft().getHeight() + 1;
			}
			else {
				height = Math.max(getRight().getHeight(), getLeft().height) + 1;
			}
		}
	} 


	/* (non-Javadoc) 
	 * @see BSTNode#getLeft() 
	 */ 
	public AVLNode<T> getLeft(){ 
		return (AVLNode<T>) super.getLeft(); 
	} 
	
	
	/* (non-Javadoc) 
	 * @see BSTNode#getRight() 
	 */ 
	public AVLNode<T> getRight() { 
		return (AVLNode<T>) super.getRight(); 
	} 
	
	
	// No se necesitan los setters, valen los heredados 


	/* (non-Javadoc) 
	 * @see BSTNode#toString() 
	 * Añade factor de balance 
	 */ 
	public String toString() { 
		return super.toString()+":FB="+ getBF(); 
	} 
} 