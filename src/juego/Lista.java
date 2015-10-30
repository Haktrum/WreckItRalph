package juego;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Modela una lista iterable
 *  
 * @param <T> Tipo de objeto de la lista
 */
public class Lista<T> implements Iterable<T> {
	/** Arreglo de elementos */
	private T[] arr;
	/** Cantidad de elementos */
	private int cant;
	
	/**
	 * Crea una lista
	 * @param max m&aacute;xima cantidad de elementos
	 */
	@SuppressWarnings("unchecked")
	Lista(int max) {
		arr = (T[]) new Object[max];
		cant = 0;
	}
	/**
	 * Agrega un objeto a la lista
	 * @param t Objeto a agregar
	 */
	public void agregar(T t) {
		if (cant < arr.length) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == null) {
					arr[i] = t;
					cant++;
					break;
				}
			}
		} else {
			//System.err.println("Overflow en Lista");
		}
	}
	/**
	 * Elimina un objeto de la lista
	 * @param t Objeto a eleminar
	 */
	public void eliminar(T t) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null && arr[i].equals(t)) {
				arr[i] = null;
				break;
			}
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return Arrays.asList(arr).iterator();
	}

}
