package p3Arboles;

/** 
 * Clase derivada de BSTNode a�adiendo funcionalidad de AVL 
 * @author N�stor 
 * @version 2017-18 
 *  
 */ 
public class AVLNode<T extends Comparable<T>> extends BSTNode<T>  {
	
	private int height;  // Para almacenar la altura del �rbol.
	private byte balanceFactor; // Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a partir de la altura de los hijos.
	AVLTree<T> avlTree = new AVLTree<T>();
	
	
	/** 
	 * Llama al padre y a�ade la informaci�n propia 
	 * @param info la informaci�n que se mete en el nuevo nodo 
	 */ 
	public AVLNode(T info){
		super(info);
		height = 1;
	} 



	/** 
	 * @return devuelve la altura del �rbol del cual es ra�z el nodo en cuesti�n 
	 */ 
	public int getHeight() {
		return height;
	} 



	/** 
	 * @return Devuelve el factor de balance seg�n equilibrio del �rbol del cual es ra�z 
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
	 * Actualiza la altura del nodo en el �rbol utilizando la altura de los hijos 
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
	 * A�ade factor de balance 
	 */ 
	public String toString() { 
		return super.toString()+":FB="+ getBF(); 
	} 
} 