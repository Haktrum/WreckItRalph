package personajes;

import juego.Posicion;

import java.util.Random;


import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;
import juego.Nivel;
import juego.Utils;
/**
 * Modela al personaje Ralph del juego
 */
public class Ralph implements Actualizable {
	
	/** Posici&oacute;n de Ralph */
	private Posicion pos;
	/** Ladrillos iniciales que posee Ralph */
	private int ladrillosRestantes = Utils.ladrillosRalph;
	
	public Ralph(){
		this.ladrillosRestantes = Nivel.dificultar(this.ladrillosRestantes,true);
		Contexto.getContexto().agregarActualizable(this);
		empezar();
	}
	/** Posiciona a Ralph y rompe las ventanas */
	private void empezar() {
		pos = new Posicion(0, Utils.numPisos);
		romperVentanas();
	}
	/**
	 * Rompe las ventanas
	 */
	public void romperVentanas(){
		Nivel.getNivel().getSeccion().romperTodas();
		//Contexto.getContexto().romper();
	}
	/**
	 *  Actualiza su posici&oacute;n y tira ladrillos
	 */
	@Override
	public void actualizar() {
		if (Contexto.getContexto().randomBoolean(Utils.probTirar)) {
			mover();
		}
		if (Contexto.getContexto().randomBoolean(Utils.probTirar)) {
			saltar();
		}
	}
	/**
	 * Tira ladrillos
	 */
	private void saltar() {
		System.out.print("Ralph salta... ");
		Random rand = new Random();
		if (ladrillosRestantes > 0) {
			int ladrillos = rand.nextInt(ladrillosRestantes)+1;
			ladrillosRestantes -= ladrillos;
			System.out.println("y tira " + ladrillos + " ladrillos");
			while (ladrillos-- > 0) {
				Contexto.getContexto().agregarActualizable(new Ladrillo(pos));
			}
		} else {
			System.out.println("pero no tiene mas ladrillos");
		}
	}
	/**
	 * Se mueve
	 */
	private void mover() {
		Random random = new Random();
		Direccion dir;
		if (random.nextBoolean()) {
			dir = Direccion.IZQUIERDA;
		} else {
			dir = Direccion.DERECHA;
		}
		Posicion nueva = pos.potencial(dir);
		int x = nueva.getX();
		int y = nueva.getY();
		if (x >= 0 && x < Utils.numCols && y >= 0 && y < Utils.numPisos) {
			pos.go(dir);
		}
	}
}
