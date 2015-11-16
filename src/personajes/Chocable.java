package personajes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import utils.Actualizable;
import utils.Direccion;
import utils.Evento;
import utils.Evento.EventoID;
import utils.Posicion;
import utils.Utils;

/**
 * Modela los atributos y comportamientos
 * que requiere un objeto Chocable
 *
 */
public abstract class Chocable implements Actualizable {
	protected Posicion pos;
	
	private int subPos = 1;
	private int subGrillas;
	
	private Image img;
	private boolean toogle = false;
	
	public Chocable(Posicion pos,int v) {
		this.pos = pos;
		this.subGrillas = Utils.dificultar(v, false);
	}
	/**
	 * Verifica el choque de dos elementos
	 * @param felix objeto a chocar
	 */
	public void chequearChoque(Felix felix) throws Evento{
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
	protected void mover(Direccion dir) throws Evento{
		if(subPos==subGrillas){
			pos.go(dir);
			if (dir == Direccion.ABAJO && pos.getY() < 0) {
				throw new Evento(EventoID.OFF_SCREEN,this);
			}
			subPos=1;
		}else{
			subPos++;
		}
	}
	public Posicion getPos() {
		return pos;
	}
	public void setPos(Posicion pos){
		this.pos = new Posicion(pos);
	}
	protected void setImage(String url1,String url2){
		String res;
		if(toogle){
			res =url1;
		}else{
			res=url2;
		}
		toogle = !toogle;
		try {
			img = ImageIO.read(new File(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Image getImage(){
		return this.img;
	}
}
