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
	
	public Felix(){
		super(new Posicion(0,0));
		super.setBaseImage("res/img/felix/felix.png");
	}
	/**
	 * Se mueve dentro de la secci&oacute;n si es posible
	 * @param dir Direcci&oacute; de movimiento
	 */
	@Override
	public void mover(Direccion dir){
		if(dir==Direccion.IZQUIERDA || dir==Direccion.ABAJO){
			super.setAuxImage("res/img/felix/felix_izq.png");
		}
		if(dir==Direccion.DERECHA || dir==Direccion.ARRIBA){
			super.setAuxImage("res/img/felix/felix_der.png");
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
		super.setAuxImage("res/img/felix/felix_martilla.png");
	}
	/**
	 * Simula un choque con un objeto
	 * @param c Objeto a chocar
	 */
	public void chocar(Chocable c) throws Evento{
		if(!this.esInvulnerable()){
			if (c.getClass() == Ladrillo.class) {
				vidas--;
				if(vidas==0){
					throw new Evento(EventoID.TERMINAJUEGO);
				}
				this.setPos(new Posicion(0,0));
			}
			if (c.getClass() == Pajaro.class) {
				vidas--;
				if(vidas==0){
					throw new Evento(EventoID.TERMINAJUEGO);
				}
			}
		}
		if(c.getClass() == Pastel.class){
			this.hacerInvulnerable();
		}
	}
	
}
