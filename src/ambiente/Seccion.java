package ambiente;


import juego.Actualizable;
import juego.Contexto;
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
				if(Contexto.randomBoolean(5)){
					mapa[j][i] = new DobleHoja();
				}else{
					mapa[j][i] = new Comun();
				}
			}
		}
		if(parte==1){
			mapa[0][2] = new Puerta();	
			mapa[1][2] = new SemiCircular();
		}
	}
	public void imprimir(){
		for(int j = ROWS-1;j>=0;j--){
			System.out.println("---------------------------------------------");
			for(int i = COLS-1;i>=0;i--){
				System.out.print(mapa[j][i].toString());
			}
			System.out.println();
		}
	}
	public int arreglarVentana(Posicion pos){
		return VentanaEn(pos).arreglar();
	}
	public Ventana VentanaEn(Posicion pos){
		return mapa[pos.getY()][pos.getX()];
	}
	public boolean puedoIr(Posicion pos, Posicion nueva){
		if(nueva.getX()<0 || nueva.getX()>COLS) return false;
		if(nueva.getY()<0 || nueva.getY()>ROWS) return false;
		int dir_y = nueva.getY()-pos.getY();
		if(dir_y==1 && VentanaEn(pos).tieneMoldura()) return false;
		if(dir_y==-1 && VentanaEn(pos).tieneMacetero()) return false;
		int dir_x = nueva.getX() - pos.getX();
		if(dir_x==1 && (VentanaEn(pos).tieneHojaDer() || VentanaEn(nueva).tieneHojaIzq())) return false;
		if(dir_x==-1 && (VentanaEn(pos).tieneHojaIzq() || VentanaEn(nueva).tieneHojaDer())) return false;
		
		return true;
	}
	public void romperTodas(){
		for(int j = 0;j<ROWS;j++){
			for(int i = 0;i<COLS;i++){
				VentanaEn(new Posicion(i,j)).romper();	
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
				if(VentanaEn(new Posicion(i,j)).getRoto()>0) aux++;
			}
		}
		this.ventRotas = aux;
	}
}
