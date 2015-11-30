package personajes;

import java.awt.Rectangle;
import java.util.Arrays;

import utils.Direccion;
import utils.Posicion;
import utils.Utils;
import utils.eventos.Evento;
import utils.eventos.EventoRalphSalta;

/**
 * Modela al personaje Ralph del juego
 */
public class Ralph extends Chocable {

	/** Ladrillos iniciales que posee Ralph */
	private int ladrillosRestantes;

	private final REQ RALPH = new REQ(0, 5);
	private final REQ RALPH_IZQ1 = new REQ(1, 5);
	private final REQ RALPH_IZQ2 = new REQ(2, 5);
	private final REQ RALPH_DER1 = new REQ(3,5);
	private final REQ RALPH_DER2 = new REQ(4,5);
	private final REQ RALPH_SALTA1 = new REQ(5, 5);
	private final REQ RALPH_SALTA2 = new REQ(6, 5);
	private final REQ RALPH_SUBE1 = new REQ(7,5);
	private final REQ RALPH_SUBE2 = new REQ(8,5);
	private int timerAccion = 0;
	private int movs = 0;
	

	public Ralph() {
		super(new Posicion(0, Utils.numPisos), Utils.vRalph, new Rectangle(69, 82));
		this.ladrillosRestantes = Utils.dificultar(Utils.ladrillosRalph, true);
		this.ladrillosRestantes = Utils.ladrillosRalph;
		super.agregarImagen("res/img/ralph/ralph.png");
		super.agregarImagen("res/img/ralph/ralph_izq1.png");
		super.agregarImagen("res/img/ralph/ralph_izq2.png");
		super.agregarImagen("res/img/ralph/ralph_der1.png");
		super.agregarImagen("res/img/ralph/ralph_der2.png");
		super.agregarImagen("res/img/ralph/ralph_salta1.png");
		super.agregarImagen("res/img/ralph/ralph_salta2.png");
		super.agregarImagen("res/img/ralph/ralph_sube1.png");
		super.agregarImagen("res/img/ralph/ralph_sube2.png");
	}

	/**
	 * Actualiza su posici&oacute;n y tira ladrillos
	 * @throws EventoRalphSalta 
	 */
	@Override
	public void actualizar() throws EventoRalphSalta {
		super.refresh();
		if (movs > 0) {
			if (caminar(Direccion.DERECHA)) {
				super.requests.add(RALPH_DER1);
				super.requests.add(RALPH_DER2);
				movs--;
			} else {
				super.requests.removeAll(requests);
				movs = 0;
			}
		} else if (movs < 0) {
			if (caminar(Direccion.IZQUIERDA)) {
				super.requests.add(RALPH_IZQ1);
				super.requests.add(RALPH_IZQ2);
				movs--;
			} else {
				super.requests.clear();
				movs = 0;
			}
		} else {
			super.requests.removeAll(Arrays.asList(RALPH,RALPH_IZQ1,RALPH_IZQ2,RALPH_DER1,RALPH_DER2));
			
			if (timerAccion == 0) {
				if (Utils.randomBoolean(100-Utils.probTirar)) {
					movs = Math.min(Utils.RANDOM.nextInt(Utils.cellWidth), 30);
					if (Utils.randomBoolean(50)) {
						movs *= -1;
					}
				} else {
					// super.setBaseImage("res/img/ralph/ralph.png");
					if (Utils.randomBoolean(Utils.dificultar(Utils.probTirar, true))) {
						int lad = saltar();
						try{
							throw new EventoRalphSalta(lad);
						}finally{
							for(int i = 0;i<lad;i++){
								super.requests.add(RALPH_SALTA1);
								super.requests.add(RALPH_SALTA2);
							}
							timerAccion = 50;
						}
					}
				}
				timerAccion = 50;
			} else {
				timerAccion--;
			}
		}
	}

	/**
	 * Tira ladrillos
	 */
	private int saltar() {
		if (ladrillosRestantes > 0) {
			int ladrillos = Utils.RANDOM.nextInt(Math.min(ladrillosRestantes, 3)) + 1;
			ladrillosRestantes -= ladrillos;
			return ladrillos;
		}
		return 0;
	}

	/**
	 * Se mueve
	 */
	private boolean caminar(Direccion dir) {
		Posicion nueva = this.getPos().potencial(dir);
		int x = nueva.inPx().getX();
		int limite = (new Posicion(Utils.numCols - 1, 3)).inPx().getX();
		if (x >= Utils.margenIzq && x < limite) {
			try {
				super.mover(dir);
				return true;
			} catch (Evento e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public void pasarSeccion(){
		this.requests.clear();
		this.requests.add(RALPH_SUBE1);
		this.requests.add(RALPH_SUBE2);
		this.requests.add(RALPH_SUBE1);
		this.requests.add(RALPH_SUBE2);
		this.requests.add(RALPH_SUBE1);
		this.requests.add(RALPH);
		this.requests.add(RALPH_SALTA1);
		this.requests.add(RALPH_SALTA2);
	}
}
