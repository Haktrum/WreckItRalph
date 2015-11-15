package control;

import java.util.Scanner;
/**
 * Modela un jugador del juego
 *
 */
public class Jugador implements Comparable<Jugador> {
	private final String nombre;
	private int puntaje;
	
	public Jugador() {
		System.out.print("Ingrese su nombre: ");
		Scanner scanner = new Scanner(System.in);
		nombre = scanner.nextLine();
		scanner.close();
	}
	/**
	 * Compara dos jugadores por su puntaje
	 */
	@Override
	public int compareTo(Jugador o) {
		return puntaje - o.puntaje;
	}
	
	@Override
	public String toString() {
		return nombre + " - " + puntaje;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
}
