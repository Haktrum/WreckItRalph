package utils;

import java.util.Random;

public class Utils {
	/** Veolocidad inicial del pajaro */
	public static final int vPajaro = 2;
	/** Veolocidad inicial del ladrillo */
	public static final int vLadrillo = 4;
	/** Veolocidad inicial del ladrillo */
	public static final int vRalph = 4;
	/** Cantidad inicial de ladrillos que tiene Ralph */
	public static final int ladrillosRalph = 40;
	/** Tama&ntilde;o de la lista de actualizables */
	public static final int maxLista = 80;
	/** Porcentaje de incremento/decremento por nivel */
	public static final double incDif = 0.15;
	/** Cantidad de niveles */
	public static final int maxNivel = 10;
	/** Cantidad de secciones */
	public static final int maxSeccion = 3;
	/** Cantidad de ventanas por piso. Preferentemente impar */
	public static final int numCols = 5;
	/** Cantidad de pisos por secci&oacute;n */
	public static final int numPisos = 3;
	/** Probabilidad de que exista una ventana tipo doble hoja */
	public static final int probDosHojas = 5;
	/** Puntaje obtenido por arreglar un panel */
	public static final int puntajeArreglarPanel = 100;
	/** Puntaje obtenido por arreglar una ventana */
	public static final int puntajeArreglar = 500;
	/** Tiempo que estar&aacute; un pastel sobre la ventana */
	public static final int tiempoPastel = 60;
	/** Tiempo m&iacute;nimo entre pastel y pastel */
	public static final int proximoPastel = 300;
	/** Cantidad de jugadores en el HighScore */
	public static final int maxJugadores = 5;
	/** Cantidad de vidas que tiene F&eacute;lix por nivel */
	public static final int vidasPorNivel = 3;
	/** Tiempo de invulnerabilidad de F&eacute;lix */
	public static final int tiempoInvulnerable = 100;
	/** Probabilidad de que Ralph tire ladrillos */
	public static final int probTirar = 60;
	/** Probabilidad de que aparezca un pastel */
	public static final int probPastel = 5;
	/** Probabilidad de que aparezca un pajaro */
	public static final int probPajaro = 1;
	
	/** Directorio base de las imagenes */
	public static final String BASE_IMG = "res/img/";

	/** Ancho de una celda donde se encuentran las ventanas */
	public static final int cellWidth = 62;
	/** Alto de una celda donde se encuentran las ventanas */
	public static final int cellHeight = 84;
	
	/** Ancho de la ventana del juego*/
	public static final int juegoWidth = 634;
	/** Alto de la ventana del juego*/
	public static final int juegoHeight = 430;
	/** Espacio entre el extremo izquierdo y la grilla de ventanas */
	public static final int margenIzq = (Utils.juegoWidth - Utils.numCols*Utils.cellWidth) / 2;
	
	/** Nivel de dificultad en el que se encuentra el juego */
	public static int nivelActual = 1;
	
	
	public static Random RANDOM = new Random();
	
	/**
	 * Incrementa/decrementa un numero segun el nivel de dificultad, 15% por cada nivel.
	 * @param n numero a modificar
	 * @param inc incrementa si true, decrementa si false
	 * @return valor modificado
	 */
	public static int dificultar(int n, boolean inc) {
		if (inc)
			return (int) Math.round( n* Math.pow(1 + Utils.incDif, nivelActual - 1));
		return (int) Math.round(n * Math.pow(1 - Utils.incDif, nivelActual - 1));
	}
	
	/**
	 * Devuelve un boolean al azar con cierta probabilidad de true, esta
	 * probabilidad var&iacute; con el nivel.
	 * 
	 * @param p
	 *            probabilidad de true en el nivel 1
	 * @return boolean al azar
	 */
	public static boolean randomBoolean(int p) {
		int t = RANDOM.nextInt(100);
		return t <= (p * (1 + (Utils.nivelActual - 1) * Utils.incDif));
	}
	
	/**
	 * Devuele la url de una ventana com&uacute;n rota
	 * @param i numero de paneles rotos
	 * @return url
	 */
	public static String urlVentanaComun(int i) {
		return BASE_IMG + "ventanas_y_panel/roto" + i + ".png";
	}

	/**
	 * Devuele la url de una ventana doble hoja
	 * @param izq determina si tiene panel izquierdo
	 * @param der determina si tiene panel derecho
	 * @return url
	 */
	public static String urlVentanaDobleHoja(boolean izq, boolean der) {
		if (izq & der)
			return BASE_IMG + "ventanas_y_panel/doblehoja_abierta.png";
		if (izq)
			return BASE_IMG + "ventanas_y_panel/doblehoja_izq.png";
		if (der)
			return BASE_IMG + "ventanas_y_panel/doblehoja_der.png";
		return BASE_IMG + "ventanas_y_panel/doblehoja_cerrada.png";
	}

	/**
	 * Devuele la url de una puerta rota
	 * @param i numero de paneles rotos
	 * @return url
	 */
	public static String urlPuerta(int i) {
		return BASE_IMG + "semicirculares/puerta_roto" + i + ".png";
	}
	/**
	 * Devuele la url de una ventana semicircular rota
	 * @param i numero de paneles rotos
	 * @return url
	 */
	public static String urlSemicirc(int roto) {
		if(roto>2){
			roto = 2;
		}
		return BASE_IMG + "semicirculares/semicircular_roto" + roto + ".png";
	}

}
