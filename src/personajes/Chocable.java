package personajes;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import utils.Actualizable;
import utils.Direccion;
import utils.Loader;
import utils.Posicion;
import utils.Utils;
import utils.eventos.EventoOffScreen;

/**
 * Modela los atributos y comportamientos que requiere un objeto Chocable
 *
 */
public abstract class Chocable implements Actualizable {
	protected Posicion pos;
	private final Rectangle size;
	private int velocidad;
	private int timerImagen = 0;

	protected ArrayList<BufferedImage> imagenes = new ArrayList<>();
	protected Queue<REQ> requests = new LinkedList<REQ>();
	protected int imagenActual = 0;

	public Chocable(Posicion pos, Rectangle size) {
		this(pos, 0, size);
	}

	public Chocable(Posicion pos, int v, Rectangle size) {
		this.pos = pos;
		this.velocidad = Utils.dificultar(v, true);
		this.velocidad = v;
		this.size = size;
	}

	protected void agregarImagen(String url) {
		imagenes.add(Loader.getImage(url));
	}

	protected void agregarImagen(String url, int pos) {
		imagenes.remove(pos);
		imagenes.add(pos, Loader.getImage(url));
	}

	/**
	 * Mueve el objeto en cierta direcci&oacute;n. 	 * 
	 * @param dir
	 *            Direcci&oacute;n a moverse
	 */
	protected void mover(Direccion dir) throws EventoOffScreen {
		pos.subGo(dir, velocidad);
		if (pos.getY() < -1) {
			this.requests.clear();
			throw new EventoOffScreen();
		}
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		this.pos = new Posicion(pos);
	}

	public BufferedImage getImage() {
		return imagenes.get(imagenActual);
	}
	public Insets getMargenes(){
		int top = (Utils.cellHeight-imagenes.get(imagenActual).getHeight())/2;
		int left = (Utils.cellWidth-imagenes.get(imagenActual).getWidth())/2;
		return new Insets(top,left,0,0);
	}
	
	protected boolean estaChocando(Chocable c) {
		return size.intersects(c.size);
	}
	
	protected void refresh() {
		Posicion posGrafica = pos.inPx();
		size.setLocation(posGrafica.getX(), posGrafica.getY());
		if (timerImagen == 0) {
			REQ r = requests.poll();
			if (r != null) {
				imagenActual = r.getP();
				timerImagen = r.getT();
			} else {
				imagenActual = 0;
			}
		} else {
			timerImagen--;
		}
	}
	public void paintComponent(Graphics g) {
		Posicion posGrafica = pos.inPx();
		g.drawImage(getImage(), posGrafica.getX()+this.getMargenes().left, posGrafica.getY()+this.getMargenes().top, null);
	}
	
	protected class REQ {
		private int pos;
		private int tiempo;

		public REQ(int pos, int tiempo) {
			this.pos = pos;
			this.tiempo = tiempo;
		}

		public int getT() {
			return this.tiempo;
		}

		public int getP() {
			return this.pos;
		}

		@Override
		public boolean equals(Object otro) {
			if (otro instanceof REQ)
				return this.pos == ((REQ) otro).pos;
			return false;
		}
	}

	public int getVelocidad() {
		return this.velocidad;
	}
}
