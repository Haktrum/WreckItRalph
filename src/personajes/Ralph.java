package personajes;


import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import juego.Contexto;
import juego.Nivel;

import utils.Actualizable;
import utils.Direccion;
import utils.Evento;
import utils.Evento.EventoID;
import utils.Posicion;
import utils.Utils;



/**
 * Modela al personaje Ralph del juego
 */
public class Ralph extends Chocable {
	
	/** Ladrillos iniciales que posee Ralph */
	private int ladrillosRestantes = Utils.ladrillosRalph;
	
	
	public Ralph(){
		super(new Posicion(0,Utils.numPisos),0);
		this.ladrillosRestantes = Utils.dificultar(this.ladrillosRestantes,true);
	}
	
	/**
	 *  Actualiza su posici&oacute;n y tira ladrillos
	 */
	@Override
	public void actualizar() throws Evento{
		if (Utils.randomBoolean(Utils.probTirar)) {
			mover();
		}
		if (Utils.randomBoolean(Utils.probTirar)) {
			super.setImage("res/img/ralph/slice167_@.png", "res/img/ralph/slice168_@.png");			
			throw new Evento (EventoID.SALTA,saltar());
		}
	}
	/**
	 * Tira ladrillos
	 */
	private int saltar() {
		Random rand = new Random();
		if (ladrillosRestantes > 0) {
			int ladrillos = rand.nextInt(Math.min(ladrillosRestantes,3))+1;
			ladrillosRestantes -= ladrillos;
			return ladrillos;
		}
		return 0;
	}
	/**
	 * Se mueve
	 */
	private void mover() {
		Random random = new Random();
		Direccion dir;
		if (random.nextBoolean()) {
			super.setImage("res/img/ralph/slice177_@.png", "res/img/ralph/slice177_@.png");
			dir = Direccion.IZQUIERDA;
		} else {
			super.setImage("res/img/ralph/slice147_@.png", "res/img/ralph/slice148_@.png");
			dir = Direccion.DERECHA;
		}
		Posicion nueva = super.pos.potencial(dir);
		int x = nueva.getX();
		int y = nueva.getY();
		if (x >= 0 && x < Utils.numCols && y >= 0 && y < Utils.numPisos) {
			super.pos.go(dir);
		}
	}
}
