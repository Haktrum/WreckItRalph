package utils;

import java.util.Random;



public class Utils {
	/** Veolocidad inicial del pajaro */
	public static final int vPajaro = 5;
	/** Veolocidad inicial del ladrillo */
	public static final int vLadrillo = 2;
	/** Veolocidad inicial del ladrillo */
	public static final int vRalph = 5;
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
	/** Cantidad de ventanas por piso. Preferentemente impar*/
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
	/** Cantidad de jugadores en el HighScore */
	public static final int maxJugadores = 10;
	/** Cantidad de vidas que tiene F&eacute;lix por nivel */
	public static final int vidasPorNivel = 3;
	/** Tiempo de invulnerabilidad de F&eacute;lix */
	public static final int tiempoInvulnerable = 100;
	/** Probabilidad de que Ralph tire ladrillos */
	public static final int probTirar = 2;
	
	public static final String BASE_IMG = "res/img/";
	
	public static int nivelActual = 1;
	
	/**
	 * Devuelve un boolean  al azar
	 * con cierta probabilidad de true,
	 * esta probabilidad var&iacute; con el nivel.
	 * @param p probabilidad de true en el nivel 1
	 * @return boolean al azar
	 */
	
	public static int dificultar(int n,boolean inc){
		if(inc)
			return (int) (n*Math.floor(Math.pow(1+Utils.incDif,nivelActual-1)));
		return (int) (n*Math.floor(Math.pow(1-Utils.incDif,nivelActual-1)));
	}
	/**
	 * Sabra dios que carajo hace esto
	 * @param paneles
	 * @return
	 */
	public int randomInt(int paneles) {
		Random random = new Random();
		int r = random.nextInt(100);
		double sum = 0;
		for (int i = 0; i < paneles; i++) {
			sum += 100D / Math.pow(2, i + 1) * (1 - (Utils.nivelActual - 1) * Utils.incDif);
			if (r < sum) {
				return i;
			}
		}
		return paneles;
	}
	/**
	 * Devuelve 0, 2 o 4, segun las
	 * probabilidades dadas. Estas probabilidades var&iacute;n con el nivel.
	 * 
	 * @param p1 probabilidad de 0 en el nivel 1
	 * @param p2 probabilidad de 2 en el nivel 1
	 * @return 0, 2 o 4. Al azar
	 */
	public int ponderar(int p1, int p2) {
		Random gen = new Random();
		int t = gen.nextInt(100);
		p1 = Utils.dificultar(p1,true);
		p2 = Utils.dificultar(p2,true);
		//p1 *= (1 + (this.nivelActual() - 1) * .15);
		//p2 *= (1 + (this.nivelActual() - 1) * .15);
		if(t <= p1) return 0;
		if(t <= p1 + p2) return 2;
		return 4;
	}
	// p = probabiliadad de true
	public static boolean randomBoolean(int p) {
		Random gen = new Random();
		int t = gen.nextInt(100); 
		return t <= (p * (1 + (Utils.nivelActual - 1) * Utils.incDif));
	}
	
	public static String urlVentanaComun(int i){
		return BASE_IMG+"ventanas_y_panel/roto"+i+".png";
	}
	public static String urlVentanaDobleHoja(boolean izq,boolean der){
		if(izq & der) return BASE_IMG+"ventanas_y_panel/doblehoja_abierta.png";
		if(izq) return BASE_IMG+"ventanas_y_panel/doblehoja_izq.png";
		if(der) return BASE_IMG+"ventanas_y_panel/doblehoja_der.png";
		return BASE_IMG+"ventanas_y_panel/doblehoja_cerrada.png";	
	}
	public static String urlPuerta(int i){
		return BASE_IMG+"semicirculares/puerta_roto"+i+".png";
	}

}

