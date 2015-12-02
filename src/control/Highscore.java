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
	
	private static final long serialVersionUID = -7016790427904503366L;
	/** Ruta del archivo donde se guardan los puntajes */
	private static String archivo = "res/top5.bin";
	/** Arreglo de jugadores con puntaje */
	private TreeSet<Jugador> jugadores;

	public Highscore(){
		jugadores = leer();
	}

	/**
	 * Agrega un jugador al arreglo, ordenado por su puntaje
	 * 
	 * @param jugador Jugador con alg&uacute;n puntaje
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
	/**
	 * 
	 * @return lista de Jugadores
	 */
	public TreeSet<Jugador> getJugadores() {
		return jugadores;
	}
	/**
	 * Vuelca la lista de jugadores a un string legible
	 */
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

	/**
	 * Lee los jugadores y sus puntajes desde el archivo
	 * @return la lista de jugadores
	 */
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
	/**
	 * Indica si el jugador (por nombre) ya se encuentra en la lista
	 * @param jugador jugador a buscar
	 * @return true si ya est&aacute;
	 */
	public boolean yaEsta(Jugador jugador){
		return jugadores.contains(jugador);
	}
	/** 
	 * Se fija si hay lugar en la lista para un nuevo jugador
	 * @param puntos puntaje del jugador a ingresar
	 * @return true si hay lugar
	 */
	public boolean hayLugar(int puntos){
		if(jugadores.size()<Utils.maxJugadores)
			return true;
		return jugadores.last().getPuntaje()<puntos;
	}
	/**
	 * Guarda la lista de jugadores en el archivo
	 */
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
