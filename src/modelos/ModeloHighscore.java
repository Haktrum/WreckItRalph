package modelos;


import utils.exceptions.BadInput;
import utils.exceptions.NombreCorto;
import utils.exceptions.NombreConEspacios;
import utils.exceptions.NombreYaExiste;

import control.Highscore;
import control.Jugador;


public class ModeloHighscore extends Modelo{
	
	private static ModeloHighscore instancia;
	private Highscore highscore = new Highscore();
	/** Indica si hay o no que sobreescribir al jugador anterior */
	private boolean sobreEscribir = false;
	
	/**
	 * Agrega el jugador al top 5 si cumple con los requisitos
	 * @param nombre nombre del jugador
	 * @param puntos puntos del jugador
	 * @throws BadInput NombreCorto si tiene menos de 2 caracteres, NombreConEspacios si tiene espacios, 
	 * NombreYaExiste si el nombre ya existe y sobreEscribir esta en false
	 */
	public void submit(String nombre,int puntos) throws BadInput{
		if(nombre.length()<2)
			throw new NombreCorto();
		if(nombre.contains(" ")){
			throw new NombreConEspacios();
		}
		if(highscore.yaEsta(new Jugador(nombre.toString())) && !sobreEscribir){
			sobreEscribir = true;
			throw new NombreYaExiste();
		}
		if(nombre.length()>20)
			nombre.substring(0, 19);
		highscore.agregarJugador(new Jugador(nombre,puntos));
	}
	
	public void noSobreescribir(){
		this.sobreEscribir = false;
	}
	public static ModeloHighscore getInstancia() {
		if (instancia == null) {
			instancia = new ModeloHighscore();
		}
		return instancia;
	}
	@Override
	public void actualizar() {
		
	}


}
