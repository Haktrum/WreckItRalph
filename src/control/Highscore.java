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
	 * Maneja los 10 puntajes m&aacute;s
	 * altos obtenidos en el juego
	 */
public class Highscore implements Serializable {
	private static final long serialVersionUID = 9032587643779204001L;
	private static String archivo = "res/top5.bin";
	/** Arreglo de jugadores con puntaje */
	private TreeSet<Jugador> jugadores;
	/** Cantidad de jugadores inicial*/	
	private int cantJugadores = 0;	
	/** Agrega un jugador al arreglo, ordenado
	 * por su puntaje
	 * @param jugador Jugador con alg&uacute;n puntaje
	 */
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
	public Highscore() {
		jugadores = new TreeSet<>();
	}
	public void agregarJugador(Jugador jugador) {
		if (jugadores.size() == 10) {
			jugadores.remove(jugadores.last());
		}
		jugadores.add(jugador);
		escribir(this);;
	}
	public TreeSet<Jugador> getJugadores(){
		return jugadores;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Puntajes");
		Iterator<Jugador> iterator = jugadores.iterator();
		for (int i = 0; i < 10; i++) {
			stringBuilder.append("\n" + i + " ");
			if (iterator.hasNext()) {
				stringBuilder.append("- " + iterator.next());
			} else {
				stringBuilder.append("-----");
			}
		}
		return stringBuilder.toString();
	}

}
