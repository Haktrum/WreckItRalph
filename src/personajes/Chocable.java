package personajes;

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
 * Modela los atributos y comportamientos que requiere un objeto Chocable
 *
 */
public abstract class Chocable implements Actualizable {
	protected Posicion pos;
	private int velocidad;
	private int timerImagen = 0;

	protected ArrayList<BufferedImage> imagenes = new ArrayList<BufferedImage>();
	protected Queue<REQ> requests = new LinkedList<REQ>();
	private int imagenActual = 0;

	public Chocable(Posicion pos) {
		this.pos = pos;
		this.velocidad = 0;
	}

	public Chocable(Posicion pos, int v) {
		this.pos = pos;
		this.velocidad = Utils.dificultar(v, true);
	}

	protected void agregarImagen(String url) {
		imagenes.add(setImage(url));
	}

	protected void agregarImagen(String url, int pos) {
		imagenes.remove(pos);
		imagenes.add(pos, setImage(url));
	}

	/**
	 * Mueve el objeto en cierta direcci&oacute;n. Se divide cada posici&oacute;
	 * en subposiciones para variar la velocidad de los objetos
	 * 
	 * @param dir
	 *            Direcci&oacute;n a moverse
	 */
	protected void mover(Direccion dir) throws Evento {
		pos.subGo(dir, velocidad);
		if (pos.getY() < -1) {
			this.requests.clear();
			throw new Evento(EventoID.OFF_SCREEN, this);
		}
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		this.pos = new Posicion(pos);
	}

	private BufferedImage setImage(String url) {
		try {
			BufferedImage i = ImageIO.read(new File(url));
			return i;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BufferedImage getImage() {
		return imagenes.get(imagenActual);
	}

	protected void refresh() {
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
