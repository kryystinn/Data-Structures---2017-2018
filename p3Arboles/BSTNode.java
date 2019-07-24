package p3Arboles;


/** 
 * @author Cristina
 * @version 2017-18 
 */ 


public class BSTNode <T extends Comparable<T>>{ 

	private T info; 
	private BSTNode<T> left; 
	private BSTNode<T> right; 


	/** 
	 * @param info Un objeto comparable 
	 */ 
	public BSTNode (T info) {
		this.info = info;
		this.left = null;
		this.right = null;
	} 


	/** 
	 * @param info La información que se quiere meter en el nodo 
	 */ 
	protected void setInfo(T info) {
		this.info = info;
	} 


	/** 
	 * @return La información que almacena el nodo 
	 */ 
	public T getInfo() {
		return info;
	} 


	/** 
	 * @param x El nodo que se quiere enlazar en el subárbol izquierdo 
	 */ 
	protected void setLeft(BSTNode<T> x){
		this.left = x;

	} 


	/** 
	 * @param x El nodo que se quiere enlazar en el subárbol derecho 
	 */ 
	protected void setRight(BSTNode<T> x){
		this.right = x;
	} 


	/** 
	 * @return El subárbol izquierdo 
	 */ 
	protected BSTNode<T> getLeft () {
		return left;
	} 


	/** 
	 * @return El subárbol derecho 
	 */ 
	protected BSTNode<T> getRight () {
		return right;
	} 


	/* (non-Javadoc) 
	 * @see java.lang.Object#toString() 
	 */ 
	public String toString() { 
		return info.toString(); 
	}  
} 
