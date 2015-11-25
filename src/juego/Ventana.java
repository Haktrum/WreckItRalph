package juego;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import utils.Actualizable;
import utils.Direccion;
import utils.Utils;

/**
 * Modela una Ventana del juego
 * 
 */
public class Ventana implements Actualizable {

	/** N&uacute;mero de golpes restantes para arreglar la ventana */
	private int roto;
	/** Indica si la ventana tiene moldura */
	private final boolean MOLDURA;
	/** Indica si la ventana tiene macetero */
	private final boolean MACETERO;
	/** Indica si la ventana tiene hoja izquierda */
	private final boolean HOJA_IZQ;
	/** Indica si la ventana tiene hoja derecha */
	private final boolean HOJA_DER;
	/** Indica si esta ventana coinicide con la posici&oacute;n de Felix */
	private boolean felixesta = false;
	/** Indica el tipo de ventana */
	protected final Tipo tipo;
	/** Tiempo que estar&aacute; el pastel sobre la ventana */
	private int timerPastel = 0;

	private BufferedImage img;

	/**
	 * Crea una ventana del Tipo tipo
	 * 
	 * @param tipo
	 *            Tipo de Ventana
	 */
	public Ventana(Tipo tipo) {
		this.tipo = tipo;
		this.MOLDURA = tipo.arribaAbajo && Utils.randomBoolean(10);
		this.MACETERO = tipo.arribaAbajo && Utils.randomBoolean(10);
		this.HOJA_IZQ = tipo.izq && Utils.randomBoolean(60);
		this.HOJA_DER = tipo.der && Utils.randomBoolean(60);
		this.actualizar();
	}

	/**
	 * Pone un pastel sobre la ventana
	 */
	public void crearPastel() {
		this.timerPastel = Utils.tiempoPastel;
	}

	/**
	 * Saca el pastel de la ventana si hay uno e informa si hab&iacute;a
	 * 
	 * @return true si habia un pastel, false en caso contrario
	 */
	public boolean comerPastel() {
		boolean hayPastel = timerPastel > 0;
		timerPastel = 0;
		return hayPastel;
	}

	/**
	 * Devuelve la cantidad de martillazos que son necesarios para arreglar la
	 * ventana
	 * 
	 * @return cantidad de martillazos faltantes para arreglar la ventana
	 */
	public int getRoto() {
		return this.roto;
	}

	public boolean felixEsta() {
		return this.felixesta;
	}

	public void felixEsta(boolean b) {
		this.felixesta = b;
	}

	private void setImage(String url) {
		Image moldura = null;
		Image macetero = null;
		try {
			img = ImageIO.read(new File(url));
			moldura = ImageIO.read(new File("res/img/obstaculos/moldura.png"));
			macetero = ImageIO.read(new File("res/img/obstaculos/macetero.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.drawImage(img, 0, 0, null);
		if (this.MOLDURA)
			g.drawImage(moldura, 0, 0, null);
		if (this.MACETERO)
			g.drawImage(macetero, 0, img.getWidth(), null);

	}

	public BufferedImage getImage() {
		return img;
	}

	/**
	 * Simula un martillazo a la ventana
	 * 
	 * @return el puntaje obtenido por ese martillazo
	 */
	public int arreglar() {
		if (this.roto == 0)
			return 0;
		this.roto--;
		if (this.roto == 0) {
			return Utils.puntajeArreglar;
		}
		if ((this.roto % 2) == 0) {
			return Utils.puntajeArreglarPanel;
		}
		return 0;
	}

	/**
	 * Determina si se puede mover en dada direcci&oacute;n a partir de esta
	 * ventana
	 * 
	 * @param dir
	 *            Direcci&oacute;n deseada
	 * @return true si se puede mover, false en caso contrario
	 */
	public boolean puedoMoverHacia(Direccion dir) {
		switch (dir) {
		case ABAJO:
			return !MACETERO;
		case ARRIBA:
			return !MOLDURA;
		case DERECHA:
			return !HOJA_DER;
		case IZQUIERDA:
			return !HOJA_IZQ;
		}
		return false;
	}

	/**
	 * Rompe al azar la ventana
	 * 
	 * @return devuelve 1 si la ventana se rompe, 0 en caso contrario
	 */
	public int romper() {
		Random random = new Random();
		int r = random.nextInt(100);
		double sum = 0;
		int i;
		for (i = 0; i <= tipo.paneles; i++) {
			sum += 100D / Math.pow(2, i + 1) * (Math.min(Utils.nivelActual, Utils.maxNivel * 0.7) * Utils.incDif);
			if (r < sum) {
				this.roto = i * 2;
				break;
			}
		}
		if (i == tipo.paneles)
			roto = i * 2;
		this.actualizar();
		if (tipo == Tipo.SEMICIRCULAR) {
			roto = 0;
		}
		return roto > 0 ? 1 : 0;
	}

	/**
	 * Determina el tipo de ventana. Com&uacute;n, con dos hojas, puerta-ventana
	 * o semicircular
	 * 
	 *
	 */
	enum Tipo {
		DOSHOJAS(0, true, true, false), COMUN(2, false, false, true), PUERTA(4, false, false, false), SEMICIRCULAR(8,
				false, false, false);
		private int paneles;
		private boolean izq;
		private boolean der;
		private boolean arribaAbajo;

		Tipo(int paneles, boolean izq, boolean der, boolean arribaAbajo) {
			this.paneles = paneles;
			this.izq = izq;
			this.der = der;
			this.arribaAbajo = arribaAbajo;
		}
	}

	@Override
	public void actualizar() {
		String url = "";
		switch (tipo) {
		case COMUN:
			url = Utils.urlVentanaComun(roto);
			break;
		case DOSHOJAS:
			url = Utils.urlVentanaDobleHoja(HOJA_IZQ, HOJA_DER);
			break;
		case PUERTA:
			url = Utils.urlPuerta(roto);
			break;
		case SEMICIRCULAR:
			url = "res/img/semicirculares/semicircular.png";
			break;
		default:
			url = Utils.urlPuerta(roto);
			break;
		}
		this.setImage(url);
	}

}
