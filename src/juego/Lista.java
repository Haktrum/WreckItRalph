package juego;

import java.util.Arrays;
import java.util.Iterator;

public class Lista<T> implements Iterable<T> {
	private T[] arr;
	private int cant;
	
	@SuppressWarnings("unchecked")
	Lista(int max) {
		arr = (T[]) new Object[max];
		cant = 0;
	}
	
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
