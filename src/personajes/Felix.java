package personajes;

import juego.Contexto;
import juego.Nivel;
import utils.Actualizable;
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
		super(new Posicion(0,0),0);
		//super.setImage("res/img/felix/slice_65_65.png", "res/img/felix/slice_67_67.png");
	}
	/**
	 * Se mueve dentro de la secci&oacute;n si es posible
	 * @param dir Direcci&oacute; de movimiento
	 */
	@Override
	public void mover(Direccion dir){
		super.pos.go(dir);
	}
	/**
	 * Actualiza el tiempo de invulnerabilidad
	 */
	@Override
	public void actualizar(){
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
