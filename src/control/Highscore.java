package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import utils.Utils;

/**
 * Maneja los 5 puntajes m&aacute;s altos obtenidos en el juego
 */
public class Highscore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7016790427904503366L;
	private static String archivo = "res/top5.bin";
	/** Arreglo de jugadores con puntaje */
	private TreeSet<Jugador> jugadores;
	/** Cantidad de jugadores inicial */

	public Highscore(){
		jugadores = leer();
	}

	/**
	 * Agrega un jugador al arreglo, ordenado por su puntaje
	 * 
	 * @param jugador
	 *            Jugador con alg&uacute;n puntaje
	 */
	public void agregarJugador(Jugador jugador) {
		if(jugadores.contains(jugador))
			jugadores.remove(jugador);
		jugadores.add(jugador);
		if (jugadores.size() > Utils.maxJugadores) {
			jugadores.remove(jugadores.last());
		}
		escribir();
	}

	public TreeSet<Jugador> getJugadores() {
		return jugadores;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Jugador> iterator = jugadores.iterator();
		for (int i = 0; i < Utils.maxJugadores; i++) {
			stringBuilder.append(i+1 + " ");
			if(i==0)
				stringBuilder.append(" ");
			if (iterator.hasNext()) {
				stringBuilder.append(iterator.next().toString() + "\n\n");
			} else {
				stringBuilder.append("*****\n\n");
			}
		}
		return stringBuilder.toString();
	}

	@SuppressWarnings("unchecked")
	private TreeSet<Jugador> leer() {
		ObjectInputStream objectInputStream = null;
		jugadores = null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(archivo));
			jugadores = (TreeSet<Jugador>) objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			jugadores = new TreeSet<Jugador>();
			escribir();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objectInputStream != null) {
					objectInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jugadores;
	}
	public boolean yaEsta(Jugador jugador){
		return jugadores.contains(jugador);
	}
	public boolean hayLugar(int puntos){
		if(jugadores.size()<Utils.maxJugadores)
			return true;
		return jugadores.last().getPuntaje()<puntos;
	}
	
	private void escribir() {
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivo));
			objectOutputStream.writeObject(jugadores);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objectOutputStream != null) {
					objectOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
