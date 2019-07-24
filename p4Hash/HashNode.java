package p4Hash; 

/** 
 * @author Néstor 
 * @version 2017-18 
 * 
 * @param <T> 
 */ 
public class HashNode<T> { 
	private T info; 
	private byte status; 


	public static final byte BORRADO = -1; 
	public static final byte VACIO = 0; 
	public static final byte LLENO = 1; 

	
	public HashNode () { 
		info = null; 
		status=VACIO; 
	} 
	
	

	public T getInfo() { 
		return info; 
	} 
	
	

	public void remove (){ 
		status=BORRADO; 
	} 
	
	

	public void setInfo(T elem){ 
		info=elem; 
		status=LLENO; 
	} 
	
	

	public byte getStatus() { 
		return status; 
	} 

	public String toString (){ 
		StringBuilder cadena=new StringBuilder("{"); 
		switch (getStatus()){ 
		case LLENO: 
			cadena.append(info); 
			break; 
		case VACIO: 
			cadena.append("_E_"); 
			break; 
		case BORRADO: 
			cadena.append("_D_"); 
		} 
		cadena.append("}"); 
		return cadena.toString(); 
	} 
	

} 