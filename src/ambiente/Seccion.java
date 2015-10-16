package ambiente;


import ambiente.Ventana.Tipo;
import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;
import juego.Posicion;

public class Seccion implements Actualizable{
	private final int COLS = 5;
	private final int ROWS = 3;
	private int ventRotas = 0;
	private int parte;
	//private int num;
	
	private Ventana[][] mapa;
	
	public Seccion(int parte){
		this.parte = parte;
		mapa = new Ventana[ROWS][COLS];
		for(int j = 0;j<ROWS;j++){
			for(int i = 0;i<COLS;i++){
				if (i == 2 && (j == 0 || j == 1)) continue;
				if(Contexto.randomBoolean(5)){
					mapa[j][i] = new Ventana(Tipo.DOSHOJAS);
				}else{
					mapa[j][i] = new Ventana(Tipo.COMUN);
				}
			}
		}
		if(parte==1){
			mapa[0][2] = new Ventana(Tipo.PUERTA);
			mapa[1][2] = new Ventana(Tipo.SEMICIRCULAR);
		}
	}
	public void imprimir(){
		for(int j = ROWS-1;j>=0;j--){
			System.out.println("---------------------------------------------");
			for(int i = COLS-1;i>=0;i--){
				System.out.print(VentanaEn(i, j).toString());
			}
			System.out.println();
		}
	}
	public int getParte() {
		return parte;
	}
	public int arreglarVentana(Posicion pos){
		return VentanaEn(pos).arreglar();
	}
	public Ventana VentanaEn(Posicion pos){
		return VentanaEn(pos.getX(), pos.getY());
	}
	private Ventana VentanaEn(int x, int y) {
		return mapa[y][x];
	}
	public boolean puedoIr(Posicion pos, Direccion dir) {
		Posicion nueva = pos.potencial(dir);
		Direccion opuesta = dir.opuesta();
		if(nueva.getX()<0 || nueva.getX()>COLS) return false;
		if(nueva.getY()<0 || nueva.getY()>ROWS) return false;
		return VentanaEn(pos).puedoMoverHacia(dir) &&
				VentanaEn(nueva).puedoMoverHacia(opuesta);
	}
	public void romperTodas(){
		for(int j = 0;j<ROWS;j++){
			for(int i = 0;i<COLS;i++){
				VentanaEn(i,j).romper();	
			}
		}
	}
	public int getVentanasRotas(){
		return ventRotas;
	}
	@Override
	public void actualizar(){
		int aux = 0;
		for(int j = 0;j<=ROWS;j++){
			for(int i = 0;i<=COLS;i++){
				if(VentanaEn(i,j).getRoto()>0) aux++;
			}
		}
		this.ventRotas = aux;
	}
}
