package personajes;

import juego.Actualizable;
import juego.Direccion;
import juego.Nivel;
import juego.Posicion;
import juego.Contexto;
import juego.Utils;

/**
 * Modela al personaje F&eacute;lix del juego
 *
 */
public class Felix implements Actualizable {
	/** Tiempo de invulnerabilidad restante */
	private int timerInvulnerable;
	/** Posici&oacute; de F&eacute;lix */
	private Posicion pos;
	/** Vidas restantes */
	private int vidas = Utils.vidasPorNivel;
	
	public Felix(){
		this.pos = new Posicion(0,0);
	}
	/**
	 * Se mueve dentro de la secci&oacute;n si es posible
	 * @param dir Direcci&oacute; de movimiento
	 */
	public void mover(Direccion dir){
		if(Nivel.getNivel().getSeccion().puedoIr(pos, dir)){
			Nivel.getNivel().getSeccion().ventanaEn(pos).felixEsta(false);
			pos.go(dir);
			System.out.println("Felix se mueve hacia " + dir.getNombre());
		} else {
			System.out.println("Camino bloqueado hacia " + dir.getNombre());
		}
		Nivel.getNivel().getSeccion().ventanaEn(pos).felixEsta(true);
	}
	/**
	 * Martilla la ventana
	 */
	public void arreglarVentana(){
		System.out.println("Felix martilla la ventana");
		int p = Nivel.getNivel().getSeccion().arreglarVentana(pos);
		Contexto.getContexto().agregarPuntos(p);
	}
	/**
	 * Actualiza el tiempo de invulnerabilidad
	 */
	@Override
	public void actualizar() {
		if (timerInvulnerable > 0) {
			timerInvulnerable--;
		}
		if (Nivel.getNivel().getSeccion().ventanaEn(pos).comerPastel()) {
			hacerInvulnerable();
		}
	}
	/**
	 * Lo hace invulnerable
	 */
	public void hacerInvulnerable() {
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
	public void chocar(Chocable c) {
		if (c.getClass() == Ladrillo.class) {
			Nivel.getNivel().reiniciar();		
			pos = new Posicion(0, 0);
			System.out.println("A Felix le cae un ladrillo");
		}
		if (c.getClass() == Pajaro.class) {
			//un pajaro
		}
		perderVida();
	}
	/**
	 * Pierde una vida. En caso de ser la &uacute;ltima, termina el juego
	 */
	private void perderVida(){
		vidas--;
		if(vidas==0){
			Contexto.getContexto().terminarJuego();
		}
	}
	public Posicion getPos() {
		return pos;
	}
}
