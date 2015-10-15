package ambiente;

import juego.Contexto;

public class Ventana {
	
	private int roto;
	private boolean MOLDURA;
	private boolean MACETERO;
	private boolean HOJA_IZQ;
	private boolean HOJA_DER;
	public boolean felixesta = false;

	public Ventana (){
		this.MOLDURA = Contexto.randomBoolean(5);
		this.MACETERO = Contexto.randomBoolean(5);
	}
	protected void setRoto(int roto){
		this.roto = roto;
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
	public void romper(){
		this.setRoto(Contexto.randomInt());
	}
	public boolean tieneMoldura(){
		return MOLDURA;
	}
	public boolean tieneMacetero(){
		return MACETERO;
	}
	public boolean tieneHojaIzq(){
		return HOJA_IZQ;
	}
	public boolean tieneHojaDer(){
		return HOJA_DER;
	}
	protected void setHojaDer(boolean b){
		this.HOJA_DER = b;
	}
	protected void setHojaIzq(boolean b){
		this.HOJA_IZQ = b;
	}
	protected void setMoldura(boolean b) {
		MOLDURA = b;
	}
	protected void setMacetero(boolean b) {
		MACETERO = b;
	}
	@Override
	public String toString(){
		int rotas = this.getRoto();
		String i = "N";
		String d = "N";
		if(felixesta){
			return "| FELIX |";
		}
		if(this.getClass().equals(DobleHoja.class)){
			if(this.tieneHojaDer()){
				i = "I";
			}
			if(this.tieneHojaIzq()){
				d = "D";
			}
			return "| "+i+"H"+d+" "+rotas+" |";
		}
		if(this.getClass().equals(Comun.class)){
			if(this.tieneMoldura()){
				i = "M";
			}
			if(this.tieneMacetero()){
				d = "M";
			}
			return "| C"+i+d+" "+rotas+" |";
		}
		if(this.getClass().equals(Puerta.class)){
			return "| P   "+rotas+" |";
		}
		if(this.getClass().equals(SemiCircular.class)){
			return "| SC  "+rotas+" |";
		}
		return "| asda |";
	}
	
}
