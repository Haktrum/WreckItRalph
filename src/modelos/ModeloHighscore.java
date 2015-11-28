package modelos;

import java.io.Serializable;
import java.util.TreeSet;

import utils.exceptions.BadInput;
import utils.exceptions.NombreCorto;
import utils.exceptions.NombreConEspacios;
import utils.exceptions.NombreYaExiste;

import control.Highscore;
import control.Jugador;


public class ModeloHighscore extends Modelo{
	
	private static ModeloHighscore instancia;
	private Highscore highscore = new Highscore();
	private boolean sobreEscribir = false;
	
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
