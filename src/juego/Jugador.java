package juego;

import java.util.Scanner;

public class Jugador implements Comparable<Jugador> {
	private final String nombre;
	private int puntaje;
	
	Jugador() {
		System.out.print("Ingrese su nombre: ");
		Scanner scanner = new Scanner(System.in);
		nombre = scanner.nextLine();
		scanner.close();
	}

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
