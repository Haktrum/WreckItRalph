package juego;

import java.util.Random;

import personajes.Felix;
import personajes.Ralph;
import ambiente.Seccion;

public class Contexto {

	private Seccion seccionActual;
	private Felix felix;
	private int puntaje = 0;
	private Actualizable[] actualizables = new Actualizable[50];
	private int cantActualizables = 0;
	private Ralph ralph;
	public static int NIVEL = 10;
	public static Contexto ctx;
	
	public Contexto(){
		ctx = this;
		felix = new Felix(this);
		ralph = new Ralph(this);
		reiniciarNivel();
	}
	public void ganarSeccion() {
		int parte = this.seccionActual.getParte();
		if (parte < 2) {
			this.seccionActual = new Seccion(parte + 1);
		} else {
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
		if (cantActualizables < actualizables.length) {
			actualizables[cantActualizables++] = a;
		}
	}
	public void eliminarActualizable(Actualizable a) {
		int i = 0;
		while (i < cantActualizables && i < actualizables.length) {
			if (actualizables[i] == a) {
				break;
			}
		}
		while (i < cantActualizables && i < actualizables.length) {
			actualizables[i] = actualizables[i + 1];
			i++;
		}
		if (i == actualizables.length) {
			actualizables[i] = null;
		}
	}
	public void actualizar() {
		for (int i = 0; i < cantActualizables; i++) {
			actualizables[i].actualizar();
			if (actualizables[i] instanceof Chocable) {
				((Chocable) actualizables[i]).chequearChoque(felix);
			}
		}
	}
}
