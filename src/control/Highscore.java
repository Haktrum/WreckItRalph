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

/**
 * Maneja los 5 puntajes m&aacute;s altos obtenidos en el juego
 */
public class Highscore implements Serializable {
	private static final long serialVersionUID = 9032587643779204001L;
	private static String archivo = "res/top5.bin";
	/** Arreglo de jugadores con puntaje */
	private TreeSet<Jugador> jugadores;
	/** Cantidad de jugadores inicial */
	private final int cantMaxJugadores = 5;

	public Highscore() {
		jugadores = new TreeSet<>();
	}

	/**
	 * Agrega un jugador al arreglo, ordenado por su puntaje
	 * 
	 * @param jugador
	 *            Jugador con alg&uacute;n puntaje
	 */
	public void agregarJugador(Jugador jugador) {
		if (jugadores.size() == cantMaxJugadores) {
			jugadores.remove(jugadores.last());
		}
		jugadores.add(jugador);
		escribir(this);
	}

	public TreeSet<Jugador> getJugadores() {
		return jugadores;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Jugador> iterator = jugadores.iterator();
		for (int i = 0; i < cantMaxJugadores; i++) {
			stringBuilder.append(i + " ");
			if (iterator.hasNext()) {
				stringBuilder.append("- " + iterator.next() + "\n");
			} else {
				stringBuilder.append("-----\n");
			}
		}
		return stringBuilder.toString();
	}

	public static Highscore leer() {
		ObjectInputStream objectInputStream = null;
		Highscore highscore = null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(archivo));
			highscore = (Highscore) objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			highscore = new Highscore();
			escribir(highscore);
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
		return highscore;
	}

	public static void escribir(Highscore highscore) {
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivo));
			objectOutputStream.writeObject(highscore);
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
