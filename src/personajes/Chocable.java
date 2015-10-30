package personajes;

import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;
import juego.Nivel;
import juego.Posicion;

/**
 * Modela los atributos y comportamientos
 * que requiere un objeto Chocable
 *
 */
public abstract class Chocable implements Actualizable {
	private Posicion pos;
	
	private int subPos = 1;
	private int subGrillas;
	public Chocable(Posicion pos,int v) {
		this.pos = pos;
		this.subGrillas = Nivel.dificultar(v, false);
		Contexto.getContexto().agregarActualizable(this);
	}
	/**
	 * Verifica el choque de dos elementos
	 * @param felix objeto a chocar
	 */
	public void chequearChoque(Felix felix) {
		if (pos.equals(felix.getPos())) {
			felix.chocar(this);
		}
	}
	/**
	 * Mueve el objeto en cierta direcci&oacute;n.
	 * Se divide cada posici&oacute; en subposiciones
	 * para variar la velocidad de los objetos
	 * @param dir Direcci&oacute;n a moverse
	 */
	protected void mover(Direccion dir) {
		if(subPos==subGrillas){
			pos.go(dir);
			if (dir == Direccion.ABAJO && pos.getY() < 0) {
				Contexto.getContexto().eliminarActualizable(this);
			}
			subPos=1;
		}else{
			subPos++;
		}
	}
	public Posicion getPos() {
		return pos;
	}
}
