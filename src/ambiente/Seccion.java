package ambiente;


import ambiente.Ventana.Tipo;
import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;
import juego.Nivel;
import juego.Posicion;
import juego.Utils;

/**
 * Modela una secci&oacute;n del juego
 *
 */
public class Seccion implements Actualizable{
	
	/** N&uacute;mero de ventanas en un piso */
	private final int COLS = Utils.numCols;
	
	/** N&uacute;mero de pisos en una seccion */
	private final int ROWS = Utils.numPisos;
	
	/** Cantidad de ventanas rotas en la secci&oacute;n */
	private int ventRotas = 0;
	
	/** Mapa de las ventanas en la secci&oacute;n. (Piso,Ventana)  */
	private Ventana[][] mapa;
	
	/**
	 * Crea una secci&oacute;n de ROWS pisos y COLS ventanas por piso
	 */
	public Seccion(){
		mapa = new Ventana[ROWS][COLS];
		int medio = (int) ((COLS-1) / 2);
		for(int j = 0;j<ROWS;j++){
			for(int i = 0;i<COLS;i++){
				if (i == medio && (j == 0 || j == 1)) continue;
				if(Contexto.getContexto().randomBoolean(Utils.probDosHojas)){
					mapa[j][i] = new Ventana(Tipo.DOSHOJAS);
				}else{
					mapa[j][i] = new Ventana(Tipo.COMUN);
				}
			}
		}
	}
	/**
	 * Crea ventanas semicirculares en​
	 * en la entrada de planta baja y ​
	 * del primer piso
	 */
	public void puertaYBalcon(){
		mapa[0][2] = new Ventana(Tipo.PUERTA);
		mapa[1][2] = new Ventana(Tipo.SEMICIRCULAR);
	}
	public void imprimir(){
		for(int j = ROWS-1;j>=0;j--){
			System.out.println("---------------------------------------------");
			for(int i = COLS-1;i>=0;i--){
				System.out.print(ventanaEn(i, j).toString());
			}
			System.out.println();
		}
	}
	/**
	 * Martilla la ventana para arreglar sus paneles.
	 *
	 * @param pos posici&oacute;n de la ventana
	 * @return puntaje obtenido por martillar la ventana
	 */
	public int arreglarVentana(Posicion pos){
		int puntos = ventanaEn(pos).arreglar();
		if (puntos == Utils.puntajeArreglar) {
			ventRotas--;
		}
		return puntos;
	}
	/**
	 * Devuelve la ventana en la Posicion pos
	 * @param pos Posicion v&aacute;lida dentro de la secci&oacute;n
	 * @return La ventana ubicada en pos
	 */
	public Ventana ventanaEn(Posicion pos){
		return ventanaEn(pos.getX(), pos.getY());
	}
	/**
	 * Devuelve la ventana en x en el piso y
	 * @param x Punto x v&aacute;lido dentro de la secci&oacute;n
	 * @param y Punto y v&aacute;lida dentro de la secci&oacute;n
	 * @return La ventana ubicada en el punto(x,y)
	 */
	private Ventana ventanaEn(int x, int y) {
		return mapa[y][x];
	}
	/**
	 * Determina si se puede mover en una direcci&oacute;n dada
	 * desde una posici&oacute;n dada
	 * 
	 * @param pos Posici&oacute;n actual en la que se encuentra
	 * @param dir Direcci&oacute;n de movimiento
	 * @return True si se puede mover, false en caso contrario
	 */
	public boolean puedoIr(Posicion pos, Direccion dir) {
		Posicion nueva = pos.potencial(dir);
		Direccion opuesta = dir.opuesta();
		if(nueva.getX()<0 || nueva.getX()>COLS) return false;
		if(nueva.getY()<0 || nueva.getY()>ROWS) return false;
		return ventanaEn(pos).puedoMoverHacia(dir) &&
				ventanaEn(nueva).puedoMoverHacia(opuesta);
	}
	/**
	 * Rompe o no, los paneles de cada ventana de la secci&oacute;n 
	 */
	public void romperTodas(){
		for(int j = 0;j<ROWS;j++){
			for(int i = 0;i<COLS;i++){
				ventRotas += ventanaEn(i,j).romper();	
			}
		}
	}
	/**
	 * Devuelve la cantidad de ventanas rotas en la secci&oacute;n
	 * 
	 * @return cantidad de ventanas rotas en la secci&oacute;n
	 */
	public int getVentanasRotas(){
		return ventRotas;
	}
	/**
	 * Gana la secci&oacute;n en caso de que est&eacute;n
	 * todas las ventanas arregladas
	 */
	@Override
	public void actualizar(){
		if (ventRotas == 0) {
			Nivel.getNivel().ganarSeccion();
		}
	}
}
