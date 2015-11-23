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
	private int ladrillosRestantes = 30;
	
	private final REQ RALPH = new REQ(0,5);
	private final REQ RALPH_IZQ1 = new REQ(1,5);
	private final REQ RALPH_IZQ2 = new REQ(2,5);
	private final REQ RALPH_SALTA = new REQ(3,10);
	private int timerAccion = 50;
	private int movs=0;
	public Ralph(){
		super(new Posicion(0,Utils.numPisos),Utils.vRalph);
		this.ladrillosRestantes = Utils.dificultar(this.ladrillosRestantes,true);
		super.agregarImagen("res/img/ralph/ralph.png");
		super.agregarImagen("res/img/ralph/ralph_izq1.png");
		super.agregarImagen("res/img/ralph/ralph_izq2.png");
		super.agregarImagen("res/img/ralph/ralph_salta.png");
	}
	
	/**
	 *  Actualiza su posici&oacute;n y tira ladrillos
	 */
	@Override
	public void actualizar() throws Evento{
		super.refresh();
		if(movs>0){
			if(caminar(Direccion.DERECHA)){
				super.requests.add(RALPH_IZQ1);
				super.requests.add(RALPH_IZQ2);
				movs--;
			}else{
				super.requests.removeAll(requests);
				movs=0;
			}
		}else if(movs<0){
			if(caminar(Direccion.IZQUIERDA)){
				super.requests.add(RALPH_IZQ1);
				super.requests.add(RALPH_IZQ2);
				movs--;
			}else{
				super.requests.removeAll(requests);
				movs=0;
			}
		}else{
			super.requests.removeAll(requests);
			if(timerAccion==0){
				if (Utils.randomBoolean(50)) {
					movs = Math.min(Utils.RANDOM.nextInt(Utils.cellWidth),30);
					if(Utils.randomBoolean(50)){
						movs *= -1;
					}
				}else{
					//super.setBaseImage("res/img/ralph/ralph.png");
					if (Utils.randomBoolean(Utils.dificultar(Utils.probTirar,true))) {
						super.requests.add(RALPH_SALTA);
						//throw new Evento (EventoID.SALTA,saltar());
					}
				}
				timerAccion=50;
			}else{
				timerAccion--;
			}
		}
	}
	/**
	 * Tira ladrillos
	 */
	private int saltar() {
		if (ladrillosRestantes > 0) {
			int ladrillos = Utils.RANDOM.nextInt(Math.min(ladrillosRestantes,3))+1;
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
		int limite = (new Posicion(Utils.numCols-1,3)).inPx().getX();
		System.out.println("nueva = "+x+", limite = "+limite);
		if (x >= 0 && x < limite) {
			try {
				super.mover(dir);
				return true;
			} catch (Evento e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
