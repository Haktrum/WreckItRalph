package juego;

import ambiente.Seccion;

/**
 * Modela el nivel actual del juego
 *
 */
public class Nivel {
	
	/** Instancia de la clase (patr&oacute;n Singleton) */
	private static Nivel nivel = new Nivel();
	/** Secci&oacute;n del nivel */
	private Seccion seccion;
	/** N&uacute;mero de secci&oacute;n del nivel */
	private int seccionActual = 1;
	/** N&uacute;mero de nivel */
	private int nivelActual = 1;
	
	private Nivel(){
		this.setSeccion(new Seccion());
		this.seccion.puertaYBalcon();
	}
	/**
	 * Devuelve el nivel
	 * @return Nivel acutal
	 */
	public static Nivel getNivel(){
		return nivel;
	}
	/**
	 * Devuelve el n&uacute;mero de nivel
	 * @return N&uacute;mero del nivel acutal
	 */
	public int getNroNivel(){
		return this.nivelActual;
	}
	/**
	 * Reinicia el nivel
	 */
	public void reiniciar() {
		this.seccionActual = 1;
		this.seccion = new Seccion();
		Contexto.getContexto().empezarJuego();
	}
	public Seccion getSeccion() {
		return seccion;
	}
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	/**
	 * Devuelve el n&uacute;mero de la secci&oacute;n
	 * @return N&uacute;mero de la secci&oacute;n acutal
	 */
	public int getNroSeccion(){ // no se uso
		return seccionActual;
	}
	/**
	 * Gana el nivel y pasa al siguiente. En caso de ser el &uacute;ltimo nivel,
	 * termina el juego.
	 */
	private void ganarNivel(){
		if(this.nivelActual==Utils.maxNivel){
			Contexto.getContexto().terminarJuego();
		}else{
			this.nivelActual++;
			this.reiniciar();
		}
	}
	/**
	 * Gana la secci&oacute; y pasa a la siguiente.
	 * En caso de ser la tercera, gana el nivel
	 */
	public void ganarSeccion(){
		if(this.seccionActual<Utils.maxSeccion){
			this.seccionActual++;
			this.setSeccion(new Seccion());
		}else{
			this.ganarNivel();
		}
		this.setSeccion(new Seccion());
	}
	/**
	 * Incrementa/decrementa un valor seg&uacute; el nivel
	 * @param n valor a incrementar
	 * @param inc true si incrementa, false si decrementa
	 * @return valor incrementado/decrementado
	 */
	public static int dificultar(int n,boolean inc){
		if(inc)
			return (int) (n*Math.floor(Math.pow(1+Utils.incDif,nivel.nivelActual-1)));
		return (int) (n*Math.floor(Math.pow(1-Utils.incDif,nivel.nivelActual-1)));
	}

}
