package control;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import utils.Modelo;
import utils.Utils;
import view.MenuItem.NombreBoton;

/**
 * Maneja los 10 puntajes m&aacute;s altos obtenidos en el juego
 */
public class Highscore implements Serializable,Modelo {
	private static final long serialVersionUID = 9032587643779204001L;
	private static String archivo = "res/top5.bin";
	/** Arreglo de jugadores con puntaje */
	private TreeSet<Jugador> jugadores;
	/** Cantidad de jugadores inicial */
	private NombreBoton dest = null;
	

	/**
	 * Agrega un jugador al arreglo, ordenado por su puntaje
	 * 
	 * @param jugador
	 *            Jugador con alg&uacute;n puntaje
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

	public Highscore(){
		jugadores = leer();
	}
	public Highscore(Jugador nuevo) {
		jugadores = leer();
		this.agregarJugador(nuevo);
	}

	private void agregarJugador(Jugador jugador) {
		if(yaEsta(jugador.getNombre()))
			eliminar(jugador);
		jugadores.add(jugador);
		if (jugadores.size() > Utils.maxJugadores) {
			jugadores.remove(jugadores.last());
		}
		escribir();
	}
	private void eliminar(Jugador jugador){
		Iterator<Jugador> iterator = jugadores.iterator();
		while(iterator.hasNext()){
			Jugador j = iterator.next();
			if(j.equals(jugador)){
				iterator.remove();
				return;
			}
		}
	}
	public boolean yaEsta(String nombre){
		Iterator<Jugador> iterator = jugadores.iterator();
		while(iterator.hasNext()){
			if(iterator.next().equals(new Jugador(nombre)))
				return true;
		}
		return false;
	}
	public boolean hayLugar(int puntaje){
		return puntaje>jugadores.last().getPuntaje() || jugadores.size()<Utils.maxJugadores;
	}
	
	@Override
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

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			dest = NombreBoton.MENU;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public Object[] getInfo() {
		Object[] res = {this.toString()};
		return res;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public NombreBoton getDestino() {
		return dest;
	}

}
