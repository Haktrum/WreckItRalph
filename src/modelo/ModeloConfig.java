package modelo;

import utils.Modelo;
import utils.Utils;

public class ModeloConfig implements Modelo {

	private int nivel = Utils.nivelActual;

	public int getNivel() {
		return this.nivel;
	}

	public void disminuirNivel() {
		nivel = Math.max(nivel - 1, 1);
	}
	
	public void aumentarNivel() {
		nivel = Math.min(nivel + 1, 10);
	}

}
