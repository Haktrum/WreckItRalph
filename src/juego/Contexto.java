package juego;

import java.util.Random;

import personajes.Felix;

import ambiente.Seccion;

public class Contexto {

	private Seccion seccion_actual;
	private Felix felix;
	private int puntaje = 0;
	public static int NIVEL = 10;
	
	public Contexto(){
		seccion_actual = new Seccion(1);
		felix = new Felix(this);
		//ralph = new Ralph(this);
	}
	public Seccion getSeccion_actual() {
		return seccion_actual;
	}
	public void setSeccion_actual(Seccion seccion_actual) {
		this.seccion_actual = seccion_actual;
	}
	public void agregarPuntos(int puntaje){
		this.puntaje += puntaje;
	}
	public static boolean randomBoolean(int p){ //probabilidad de true
		Random gen = new Random();
		int t = gen.nextInt(101);
		return t<=(p+(NIVEL-1)*5);
	}
	public static int randomInt(){
		switch(Contexto.NIVEL){
		case 1:
			return ponderar(20,5);
		case 2:
			return ponderar(20,10);
		case 3:
			return ponderar(30,20);
		case 4:
			return ponderar(30,30);
		case 5:
			return ponderar(50,30);
		case 6:
			return ponderar(50,40);
		default:
			return ponderar(50,45);
		}
	}
	private static int ponderar(int p1, int p2){
		Random gen = new Random();
		int t = gen.nextInt(101);
		if(t<=p1) return 2;
		if(t>p1 && t<=(p1+p2)) return 4;
		return 0;
	}
	public enum Direccion{
		UP,LEFT,DOWN,RIGHT;
	}

	public void romper(){
		seccion_actual.romperTodas();
	}

}
