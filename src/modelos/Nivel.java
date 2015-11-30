package modelos;

import utils.Loader;
import utils.Utils;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import utils.eventos.EventoSeccionGanada;

/**
 * Modela el nivel actual del juego
 *
 */
public class Nivel {

	/** Secci&oacute;n del nivel */
	// private Seccion seccion;
	/** N&uacute;mero de nivel */
	private int nroNivel;
	/** La secci&oacute;n pr&oacute;xima a la actual */
	private Seccion[] secciones = new Seccion[Utils.maxSeccion];

	private int pasandoSeccion = 0;
	
	private int nroSeccion = 0;

	public Nivel(int lvl) {
		Utils.nivelActual = lvl;
		for (int i = 0; i < secciones.length; i++) {
			secciones[i] = new Seccion(i + 1);
		}
		this.secciones[nroSeccion].puertaYBalcon();
		this.romperVentanas();
		this.nroNivel = lvl;
	}

	/**
	 * Devuelve el n&uacute;mero de nivel
	 * 
	 * @return N&uacute;mero del nivel acutal
	 */
	public int getNro() {
		return this.nroNivel;
	}

	public Seccion getSeccion() {
		return secciones[nroSeccion];
	}

	public void setSeccion(int nro) {
		this.nroSeccion = nro;
	}
	public void pasarSeccion(){
		this.pasandoSeccion = 41;
	}
	public boolean estaPasando(){
		return pasandoSeccion>0;
	}

	/**
	 * Gana el nivel y pasa al siguiente. En caso de ser el &uacute;ltimo nivel,
	 * termina el juego.
	 */
	public void ganarNivel() throws EventoJuegoTerminado, EventoNivelGanado {
		if (this.nroNivel == 10) {
			throw new EventoJuegoTerminado();
		} else {
			this.nroNivel++;
			throw new EventoNivelGanado();
		}
	}

	/**
	 * Incrementa/decrementa un valor seg&uacute; el nivel
	 * 
	 * @param n
	 *            valor a incrementar
	 * @param inc
	 *            true si incrementa, false si decrementa
	 * @return valor incrementado/decrementado
	 */
	protected void setNivel(int n) {
		this.nroNivel = n;
	}

	public void actualizar() throws EventoNivelGanado, EventoSeccionGanada {
		if(this.pasandoSeccion>0){
			if(this.pasandoSeccion==1){
				this.romperVentanas();
				Loader.getSonido("ralph/ralph_rompe.wav").start();
			}
			this.pasandoSeccion--;
		}
		try{
			if(!this.estaPasando()){
				secciones[nroSeccion].actualizar();
			}
		}catch(EventoSeccionGanada e){
			this.pasarSeccion();
			if(this.nroSeccion==Utils.maxSeccion - 1){
				throw new EventoNivelGanado();
			}else{
				this.nroSeccion++;
			}
			throw new EventoSeccionGanada();
		}
		this.secciones[nroSeccion].decProximoPastel();
	}

	private void romperVentanas(){
		while (this.secciones[nroSeccion].getVentanasRotas() == 0) {
			this.secciones[nroSeccion].romperTodas();
		}
	}
	public Ventana[][][] getMapas() {
		Ventana[][][] mapas = new Ventana[Utils.maxSeccion][][];
		for (int i = 0; i < mapas.length; i++) {
			mapas[i] = secciones[i].getMapa();
		}
		return mapas;
	}

}
