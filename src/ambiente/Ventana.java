package ambiente;

import java.util.Random;

import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;

public class Ventana implements Actualizable {
	
	private int roto;
	private final boolean MOLDURA;
	private final boolean MACETERO;
	private final boolean HOJA_IZQ;
	private final boolean HOJA_DER;
	public boolean felixesta = false;
	protected final Tipo tipo;
	private int timerPastel = 0;

	public Ventana (Tipo tipo) {
		this.tipo = tipo;
		Random random = new Random();
		this.MOLDURA = tipo.arribaAbajo && random.nextBoolean();
		this.MACETERO = tipo.arribaAbajo && random.nextBoolean();
		this.HOJA_IZQ = tipo.izq && random.nextBoolean();
		this.HOJA_DER = tipo.der && random.nextBoolean();
		Contexto.ctx.agregarActualizable(this);
	}
	public void crearPastel() {
		this.timerPastel = 60;
	}
	public boolean comerPastel() {
		boolean hayPastel = timerPastel > 0;
		timerPastel = 0;
		return hayPastel;
	}
	public int getRoto(){
		return this.roto; 
	}
	public int arreglar(){
		this.roto--;
		if(this.roto==0)
			return 500;
		if((this.roto % 2)==0)
			return 100;
		return 0;
	}
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
	public void romper() {
		Random random = new Random();
		int r = random.nextInt(100);
		double sum = 0;
		for (int i = 0; i < tipo.paneles; i++) {
			sum += 100D / Math.pow(2, i + 1) * (1 - (Contexto.NIVEL - 1) * .15);
			if (r < sum) {
				this.roto = i * 2;
				break;
			}
		}
	}
	@Override
	public String toString(){
		int rotas = this.getRoto();
		String i = "N";
		String d = "N";
		if(felixesta){
			return "| FELIX |";
		}
		switch (tipo) {
		case DOSHOJAS:
			if(HOJA_IZQ){
				i = "I";
			}
			if(HOJA_DER){
				d = "D";
			}
			return "| "+i+"H"+d+" "+rotas+" |";

		case COMUN:
			if(MOLDURA){
				i = "M";
			}
			if(MACETERO){
				d = "M";
			}
			return "| C"+i+d+" "+rotas+" |";
			
		case PUERTA:
			return "| P   "+rotas+" |";
			
		case SEMICIRCULAR:
			return "| SC  "+rotas+" |";
			
		default:
			break;
		}
		return "| asda |";
	}
	
	enum Tipo {
		DOSHOJAS(0, true, true, true),
		COMUN(2, false, false, true),
		PUERTA(4, false, false, false),
		SEMICIRCULAR(8, false, false, false);
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
		if (timerPastel > 0) {
			timerPastel--;
		}
	}
}
