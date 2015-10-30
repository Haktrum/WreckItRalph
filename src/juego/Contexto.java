package juego;

import java.util.Random;

import personajes.Chocable;
import personajes.Felix;
import personajes.Ralph;
import ambiente.Seccion;
/**
 * Modela el conjunto de circustancias
 * que rodean a los personajes
 * 
 */
public class Contexto {

	/** Personaje principal */
	private Felix felix;
	/** Villano */
	private Ralph ralph;
	/** Puntaje inicial */
	private int puntaje = 0;
	/** Lista de objetos de car&acute;cter actualizable */
	private Lista<Actualizable> actualizables = new Lista<>(Utils.maxLista);
	/** Nivel */
	private Nivel nivel = Nivel.getNivel();
	/** Instancia de la clase (patr&oacute;n Singleton) */
	private static Contexto ctx = new Contexto();
	
	private Contexto(){
		//ctx = this;
		this.empezarJuego();
	}
	/**
	 * Inicializa el juego
	 */
	public void empezarJuego() {
		felix = new Felix();
		ralph = new Ralph();
		//reiniciarnivelActual();
	}
	/**
	 * Finaliza el juego
	 */
	public void terminarJuego(){
		WreckItRalph.guardarPuntaje(puntaje);
	}
	/**
	 * Devuelve el contexto
	 * @return Contexto actual
	 */
	public static Contexto getContexto(){
		return ctx;
	}
	/**
	 * Suma puntos al puntaje inicial
	 * @param puntaje Cantidad de puntos a sumar
	 */
	public void agregarPuntos(int puntaje) {
		this.puntaje += puntaje;
		if (puntaje > 0) {
			System.out.println("Puntaje: " + this.puntaje);
		}
	}
	/**
	 * Devuelve un boolean  al azar
	 * con cierta probabilidad de true,
	 * esta probabilidad var&iacute; con el nivel.
	 * @param p probabilidad de true en el nivel 1
	 * @return boolean al azar
	 */
	public boolean randomBoolean(int p) {
		Random gen = new Random();
		int t = gen.nextInt(100); 
		return t <= (p * (1 + (nivel.getNroNivel() - 1) * Utils.incDif));
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
		p1 = Nivel.dificultar(p1,true);
		p2 = Nivel.dificultar(p2,true);
		//p1 *= (1 + (this.nivelActual() - 1) * .15);
		//p2 *= (1 + (this.nivelActual() - 1) * .15);
		if(t <= p1) return 0;
		if(t <= p1 + p2) return 2;
		return 4;
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
			sum += 100D / Math.pow(2, i + 1) * (1 - (nivel.getNroNivel() - 1) * Utils.incDif);
			if (r < sum) {
				return i;
			}
		}
		return paneles;
	}
	/**
	 * Devuelve a F&eacute;lix
	 */
	public Felix getFelix() {
		return felix;
	}
	/**
	 * Devuelve a Ralph;
	 */
	public Ralph getRalph() {
		return ralph;
	}
	/**
	 * Devuelve el puntaje actual;
	 */
	public int getPuntaje() {
		return puntaje;
	}
	/**
	 * Agrega a la lista de Actualizables
	 * @param a objeto a agregar
	 */
	public void agregarActualizable(Actualizable a) {
		actualizables.agregar(a);
	}
	/**
	 * Elimina de la lista de Actualizables
	 * @param a objeto a eliminar
	 */
	public void eliminarActualizable(Actualizable a) {
		actualizables.eliminar(a);
	}
	/**
	 * Recorre la lista de Actualizables y los actualiza.
	 * Revisa tambi&eacute; los posibles choques de F&eacute;lix
	 */
	public void actualizar() {
		for (Actualizable a : actualizables) {
			if (a == null) continue;
			a.actualizar();
			if (a instanceof Chocable) {
				((Chocable) a).chequearChoque(felix);
			}
		}
	}
}
