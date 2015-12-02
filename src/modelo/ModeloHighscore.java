package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeSet;

import utils.Modelo;
import utils.Utils;

public class ModeloHighscore implements Modelo {

	private boolean sobreEscribir = false;
	private TreeSet<Jugador> jugadores;
	private final String archivo = "res/top5.bin";
	private int puntaje;
	private String nombre;

	public ModeloHighscore() {
		leer();
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
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
			jugadores = new TreeSet<Jugador>();
			escribir();
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

	public void agregarJugador(Jugador jugador) {
		for (Iterator<Jugador> iterator = jugadores.iterator(); iterator.hasNext();) {
			Jugador jugadorIt = (Jugador) iterator.next();
			if (jugador.equals(jugadorIt)) {
				iterator.remove();
			}
		}
		jugadores.add(jugador);
		if (jugadores.size() > Utils.maxJugadores) {
			jugadores.remove(jugadores.last());
		}
		escribir();
	}

	public boolean yaEsta(Jugador jugador) {
		return jugadores.contains(jugador);
	}

	public boolean hayLugar(int puntaje) {
		if (jugadores.size() < Utils.maxJugadores)
			return true;
		return puntaje > jugadores.last().getPuntaje();
	}

	public void submit(String nombre) throws MalInput {
		this.nombre = nombre;
		if (this.nombre.length() == 0) {
			throw new MalInput(ERR.CORTO);
		}
		if (this.nombre.contains(" ")) {
			throw new MalInput(ERR.ESPACIOS);
		}
		if (this.nombre.length() > 20)
			this.nombre.substring(0, 19);
		Jugador jugador = new Jugador(this.nombre);
		if (yaEsta(jugador) && !sobreEscribir) {
			sobreEscribir = true;
			throw new MalInput(ERR.YA_EXISTE);
		}
		jugador.setPuntaje(puntaje);
		agregarJugador(jugador);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Jugador> iterator = jugadores.iterator();
		for (int i = 0; i < Utils.maxJugadores; i++) {
			stringBuilder.append(i + 1 + " ");
			if (i == 0)
				stringBuilder.append(" ");
			if (iterator.hasNext()) {
				stringBuilder.append(iterator.next().toString() + "\n\n");
			} else {
				stringBuilder.append("*****\n\n");
			}
		}
		return stringBuilder.toString();
	}

	public Jugador getJugador(String nombre) {
		return new Jugador(nombre.toString());
	}

	enum ERR {
		CORTO("Minimo dos caracteres"),
		YA_EXISTE("El nombre ya existe * ENTER para sobreescribir"),
		ESPACIOS("El nombre no puede conteres espacios");
		private final String msj;

		ERR(String msj) {
			this.msj = msj;
		}
		
		public String getMsj() {
			return msj;
		}
	}

	@SuppressWarnings("serial")
	public class MalInput extends Exception {
		private ERR id;

		public MalInput(ERR id) {
			super(id.msj);
			this.id = id;
		}

		public ERR getID() {
			return this.id;
		}
	}

}
