package personajes;


import java.util.Random;
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
		super(new Posicion(0,Utils.numPisos),Utils.vRalph);
		this.ladrillosRestantes = Utils.dificultar(this.ladrillosRestantes,true);
		//this.ladrillosRestantes = 30;
		super.setBaseImage("res/img/ralph/ralph.png");
	}
	
	/**
	 *  Actualiza su posici&oacute;n y tira ladrillos
	 */
	@Override
	public void actualizar() throws Evento{
		super.refresh();
		if (Utils.randomBoolean(80)) {
			mover();
		}else{
			super.setBaseImage("res/img/ralph/ralph.png");
			if (Utils.randomBoolean(Utils.probTirar)) {
				super.setAuxImage("res/img/ralph/ralph_salta.png");
				throw new Evento (EventoID.SALTA,saltar());
			}
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
		super.setBaseImage("res/img/ralph/ralph_izq1.png");
		if (random.nextBoolean()) {
			super.setAuxImage("res/img/ralph/ralph_izq2.png");
			dir = Direccion.IZQUIERDA;
		} else {
			super.setAuxImage("res/img/ralph/ralph_izq2.png");
			dir = Direccion.DERECHA;
		}
		Posicion nueva = super.pos.potencial(dir);
		int x = nueva.getX();
		int y = nueva.getY();
		if (x >= 0 && x < Utils.numCols && y >= 0 && y <= Utils.numPisos) {
			try {
				super.mover(dir);
			} catch (Evento e) {
				e.printStackTrace();
			}
		}
	}
}
