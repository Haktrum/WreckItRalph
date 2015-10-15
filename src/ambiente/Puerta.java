package ambiente;

public class Puerta extends Ventana{
	public Puerta() {
		super();
		super.setHojaDer(false);
		super.setHojaIzq(false);
		super.setMoldura(false);
		super.setMacetero(false);
	}
	
	@Override
	public void romper(){
		super.romper();
		super.setRoto(super.getRoto()*2);
	}
}
