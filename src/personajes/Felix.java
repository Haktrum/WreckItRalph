package personajes;

import utils.Direccion;
import utils.Evento;
import utils.Evento.EventoID;
import utils.Posicion;
import utils.Utils;

/**
 * Modela al personaje F&eacute;lix del juego
 *
 */
public class Felix extends Chocable{
	/** Tiempo de invulnerabilidad restante */
	private int timerInvulnerable;
	/** Posici&oacute; de F&eacute;lix */
	/** Vidas restantes */
	private int vidas = Utils.vidasPorNivel;
	
	private final int FELIX = 0;
	private final int FELIX_IZQ = 1;
	private final int FELIX_DER = 2;
	private final int FELIX_MARTILLA = 3;
	public Felix(){
		super(new Posicion(0,0));
		super.agregarImagen("res/img/felix/felix.png");
		super.agregarImagen("res/img/felix/felix_izq.png");
		super.agregarImagen("res/img/felix/felix_der.png");
		super.agregarImagen("res/img/felix/felix_martilla.png");
	}
	/**
	 * Se mueve dentro de la secci&oacute;n si es posible
	 * @param dir Direcci&oacute; de movimiento
	 */
	@Override
	public void mover(Direccion dir){
		if(dir==Direccion.IZQUIERDA || dir==Direccion.ABAJO){
			super.requests.add(new REQ(FELIX_IZQ,3));
		}
		if(dir==Direccion.DERECHA || dir==Direccion.ARRIBA){
			super.requests.add(new REQ(FELIX_DER,3));
		}
		super.pos.go(dir);
	}
	/**
	 * Actualiza el tiempo de invulnerabilidad
	 */
	@Override
	public void actualizar(){
		super.refresh();
		if (timerInvulnerable > 0) {
			timerInvulnerable--;
		}
	}
	/**
	 * Lo hace invulnerable
	 */
	private void hacerInvulnerable() {
		timerInvulnerable = Utils.tiempoInvulnerable;
	}
	/**
	 * Invulnerabilidad
	 * @return  True si es invulnerable, false en caso contrario
	 */
	public boolean esInvulnerable() {
		return timerInvulnerable > 0;
	}
	public void martillar(){
		super.requests.add(new REQ(FELIX_MARTILLA,3));
		super.requests.add(new REQ(FELIX,1));
	}
	/**
	 * Verifica el choque de dos elementos
	 * @param felix objeto a chocar
	 */
	public void chequearChoque(Chocable c) throws Evento{
		if (pos.equals(c.getPos())) {
			this.chocar(c);
		}
	}
	/**
	 * Simula un choque con un objeto
	 * @param c Objeto a chocar
	 */
	private void chocar(Chocable c) throws Evento{
		if(!this.esInvulnerable()){
			if (c.getClass() == Ladrillo.class) {
				vidas--;
				if(vidas==0){
					throw new Evento(EventoID.TERMINAJUEGO);
				}
				this.setPos(new Posicion(0,0));
				timerInvulnerable = 20;
				throw new Evento(EventoID.OFF_SCREEN,c);
			}
			if (c.getClass() == Pajaro.class) {
				vidas--;
				if(vidas==0){
					throw new Evento(EventoID.TERMINAJUEGO);
				}
				timerInvulnerable = 20;
				throw new Evento(EventoID.OFF_SCREEN,c);
			}
		}
		if(c.getClass() == Pastel.class){
			this.hacerInvulnerable();
		}
	}
	
}
