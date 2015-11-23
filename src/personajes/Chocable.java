package personajes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
	protected int subPosX = 0;
	protected int subPosY = 0;
	private int velocidad;
	private int timerImagen = 0;
	
	protected ArrayList<BufferedImage> imagenes = new ArrayList<BufferedImage>();
	protected Queue<REQ> requests = new LinkedList<REQ>();
	private int imagenActual = 0;
	public Chocable(Posicion pos) {
		this.pos = pos;
		this.velocidad = 0;
	}
	public Chocable(Posicion pos,int v) {
		this.pos = pos;
		this.velocidad = v;
	}
	protected void agregarImagen(String url){
		imagenes.add(setImage(url));
	}
	/**
	 * Mueve el objeto en cierta direcci&oacute;n.
	 * Se divide cada posici&oacute; en subposiciones
	 * para variar la velocidad de los objetos
	 * @param dir Direcci&oacute;n a moverse
	 */
	protected void mover(Direccion dir) throws Evento{
		if(dir==Direccion.DERECHA){
			if(subPosX>=Utils.cellWidth){
				pos.go(dir);
				subPosX= subPosX-Utils.cellWidth;
			}else{
				subPosX+=velocidad;
			}
		}else if(dir==Direccion.IZQUIERDA){
			if(subPosX<=0){
				pos.go(dir);
				subPosX=Utils.cellWidth+subPosX;
			}else{
				subPosX-=velocidad;
			}
		}else if(dir==Direccion.ABAJO){
			if(subPosY>=Utils.cellHeight){
				pos.go(dir);
				subPosY=subPosY-Utils.cellHeight;
			}else{
				subPosY+=velocidad;
			}
			if(pos.getY()<0){
				this.requests.removeAll(requests);
				throw new Evento(EventoID.OFF_SCREEN,this);
			}
		}
	}
	public Posicion getPos() {
		return pos;
	}
	public void setPos(Posicion pos){
		this.pos = new Posicion(pos);
	}
	private BufferedImage setImage(String url){
		try {
			BufferedImage i = ImageIO.read(new File(url));
			return i;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public BufferedImage getImage(){
		return imagenes.get(imagenActual);
	}
	
	protected void toggleREQ(REQ r1, REQ r2){
		if(!requests.isEmpty()){
			if(requests.element().equals(r1)){
				requests.add(r2);
			}else{
				requests.add(r1);
			}
		}
	}
	
	protected void refresh(){
		if(timerImagen==0){
			REQ r = requests.poll();
			if(r!=null){
				imagenActual = r.getP();
				timerImagen = r.getT();
			}else{
				imagenActual=0;
			}
		}else{
			timerImagen--;
		}
	}
	public int getSubX(){
		return this.subPosX;
	}
	public int getSubY(){
		return this.subPosY;
	}
	protected class REQ{
		private int pos;
		private int tiempo;
		
		public REQ(int pos,int tiempo){
			this.pos=pos;
			this.tiempo=tiempo;
		}
		public int getT(){
			return this.tiempo;
		}
		public int getP(){
			return this.pos;
		}
		@Override
		public boolean equals(Object otro){
			if(otro instanceof REQ)
				return this.pos==((REQ) otro).pos;
			return false;
		}
	}
}
