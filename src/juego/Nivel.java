package juego;

import java.util.Random;

import utils.Actualizable;
import utils.Evento;
import utils.Utils;
import utils.Evento.EventoID;


/**
 * Modela el nivel actual del juego
 *
 */
public class Nivel{
	

	/** Secci&oacute;n del nivel */
	private Seccion seccion;
	/** N&uacute;mero de nivel */
	private int nroNivel;
	
	public Nivel(int lvl){
		this.setSeccion(new Seccion(1));
		this.seccion.puertaYBalcon();
		this.nroNivel = lvl;
		Utils.nivelActual = lvl;
	}
	
	/**
	 * Devuelve el n&uacute;mero de nivel
	 * @return N&uacute;mero del nivel acutal
	 */
	public int getNro(){
		return this.nroNivel;
	}
	/**
	 * Reinicia el nivel
	 */
	public void reiniciar() {
		this.seccion = new Seccion(1);
	}
	public Seccion getSeccion() {
		return seccion;
	}
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	/**
	 * Gana el nivel y pasa al siguiente. En caso de ser el &uacute;ltimo nivel,
	 * termina el juego.
	 */
	public void ganarNivel() throws Evento{
		if(this.nroNivel==10){
			throw new Evento(EventoID.TERMINAJUEGO);
		}else{
			this.nroNivel++;
			throw new Evento(EventoID.GANANIVEL);
		}
	}
	/**
	 * Incrementa/decrementa un valor seg&uacute; el nivel
	 * @param n valor a incrementar
	 * @param inc true si incrementa, false si decrementa
	 * @return valor incrementado/decrementado
	 */
	protected void setNivel(int n){
		this.nroNivel = n;
	}

	public void actualizar() throws Evento{
		if(this.seccion.getVentanasRotas()==0){
			try {
				this.seccion.ganarSeccion();
			} catch (Evento e) {
				if(e.getId()==EventoID.GANANIVEL){
					this.ganarNivel();
				}else if(e.getId()==EventoID.GANASECCION){
					this.seccion = new Seccion(seccion.getNro());
				}
			}
		}
	}
	

}
