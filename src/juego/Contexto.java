package juego;

import java.util.Random;

import personajes.Chocable;
import personajes.Felix;
import personajes.Ralph;
import ambiente.Seccion;

public class Contexto {

	private Seccion seccionActual;
	private Felix felix;
	private int puntaje = 0;
	private Lista<Actualizable> actualizables = new Lista<>(80);
	private int cantActualizables = 0;
	private Ralph ralph;
	public static int NIVEL = 1;
	public static Contexto ctx;
	
	public Contexto(){
		ctx = this;
	}
	public void empezarJuego() {
		reiniciarNivel();
		felix = new Felix();
		ralph = new Ralph();
	}
	public void ganarSeccion() {
		int parte = this.seccionActual.getParte();
		if (parte < 2) {
			System.out.println("Ganaste la seccion!");
			this.seccionActual = new Seccion(parte + 1);
		} else {
			System.out.println("Ganaste el nivel!");
			ganarNivel();
		}
	}
	private void ganarNivel() {
		
	}
	public void reiniciarNivel() {
		seccionActual = new Seccion(1);
	}
	public Seccion getSeccionActual() {
		return seccionActual;
	}
	public void setSeccionActual(Seccion seccion_actual) {
		this.seccionActual = seccion_actual;
	}
	public void agregarPuntos(int puntaje) {
		this.puntaje += puntaje;
		if (puntaje > 0) {
			System.out.println("Puntaje: " + this.puntaje);
		}
	}
	public static boolean randomBoolean(int p) { //probabilidad de true
		Random gen = new Random();
		int t = gen.nextInt(100);
		return t <= (p * (1 + (NIVEL - 1) * .15));
	}
	public static int ponderar(int p1, int p2) {
		Random gen = new Random();
		int t = gen.nextInt(100);
		p1 *= (1 + (NIVEL - 1) * .15);
		p2 *= (1 + (NIVEL - 1) * .15);
		if(t <= p1) return 0;
		if(t <= p1 + p2) return 2;
		return 4;
	}
	public void romper() {
		seccionActual.romperTodas();
	}
	public static int randomInt(int paneles) {
		Random random = new Random();
		int r = random.nextInt(100);
		double sum = 0;
		for (int i = 0; i < paneles; i++) {
			sum += 100D / Math.pow(2, i + 1) * (1 - (NIVEL - 1) * .15);
			if (r < sum) {
				return i;
			}
		}
		return paneles;
	}
	public Felix getFelix() {
		return felix;
	}
	public Ralph getRalph() {
		return ralph;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void agregarActualizable(Actualizable a) {
		actualizables.agregar(a);
	}
	public void eliminarActualizable(Actualizable a) {
		actualizables.eliminar(a);
	}
	public void actualizar() {
		for (Actualizable a : actualizables) {
			if (a == null) continue;
			a.actualizar();
			if (a instanceof Chocable) {
				((Chocable) a).chequearChoque(felix);
			}
		}
	}
}
