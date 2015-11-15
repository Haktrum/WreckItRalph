package juego;

import java.awt.event.KeyEvent;
import java.util.Random;




import personajes.Chocable;
import personajes.Felix;
import personajes.Ladrillo;
import personajes.Ralph;
import utils.Actualizable;
import utils.Direccion;
import utils.Evento;
import utils.Lista;
import utils.Posicion;
import utils.Utils;
import utils.Evento.EventoID;
/**
 * Modela el conjunto de circustancias
 * que rodean a los personajes
 * 
 */
public class Contexto implements Actualizable{

	/** Personaje principal */
	private Felix felix;
	/** Villano */
	private Ralph ralph;
	/** Puntaje inicial */
	private int puntaje = 0;
	/** Lista de objetos de car&acute;cter actualizable */
	
	private Lista<Chocable> chocables = new Lista<Chocable>(Utils.maxLista);
	
	/** Nivel */
	private Nivel nivel = null;
	
	public Contexto(int lvl){
		felix = new Felix();
		ralph = new Ralph();
		nivel = new Nivel(lvl);
		chocables.agregar(felix);
		chocables.agregar(ralph);
	}
	
	/**
	 * Finaliza el juego
	 * @throws Evento 
	 */
	public void terminarJuego() throws Evento{
		throw new Evento(EventoID.TERMINAJUEGO,new Integer(puntaje));
	}
	/**
	 * Suma puntos al puntaje inicial
	 * @param puntaje Cantidad de puntos a sumar
	 */
	public void agregarPuntos(int puntaje) {
		this.puntaje += puntaje;
	}
	
	
	/**
	 * Devuelve a F&eacute;lix
	 */
	public Felix getFelix() {
		return felix;
	}
	/**
	 * Devuelve a Ralph;
	 */
	public Ralph getRalph() {
		return ralph;
	}
	/**
	 * Devuelve el puntaje actual;
	 */
	public int getPuntaje() {
		return puntaje;
	}
	
	/**
	 * Recorre la lista de Actualizables y los actualiza.
	 * Revisa tambi&eacute; los posibles choques de F&eacute;lix
	 */
	public void martillar(){
		this.agregarPuntos(this.nivel.getSeccion().arreglarVentana(felix.getPos()));
	}
	public void moverFelix(Direccion dir){
		if(nivel.getSeccion().puedoIr(felix.getPos(), dir)){
			nivel.getSeccion().ventanaEn(felix.getPos()).felixEsta(false);
			felix.mover(dir);
		}
		nivel.getSeccion().ventanaEn(felix.getPos()).felixEsta(true);
	}
	//tira termina juego
	@Override
	public void actualizar() throws Evento{
		try {
			nivel.actualizar();
		} catch (Evento e) {
			if(e.getId()==EventoID.TERMINAJUEGO){
				this.terminarJuego();
			}else if(e.getId()==EventoID.GANANIVEL){
				this.nivel = new Nivel(nivel.getNro());
			}
		}
		for(Chocable chocable: chocables){
			try{
				chocable.actualizar();
			}catch(Evento e){
				switch(e.getId()){
				case CHAU_PASTEL:
					chocables.agregar((Chocable) e.getParam());
					break;
				case TERMINAJUEGO:
					this.terminarJuego();
					break;
				case OFF_SCREEN:
					chocables.eliminar((Chocable) e.getParam());
					break;
				case SALTA:
					Integer ladrillos = (Integer) e.getParam();
					while(ladrillos-- >0){
						chocables.agregar(new Ladrillo(ralph.getPos()));
					}
					break;
				default:
					break;
				}
			}
		}
	}
	public Lista<Chocable> getChocables(){
		return chocables;
	}
	public void setNivel(int n){
		this.nivel.setNivel(n);
	}
}
