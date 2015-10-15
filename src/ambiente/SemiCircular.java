package ambiente;


public class SemiCircular extends Ventana{

	public SemiCircular() {
		super();
		super.setHojaDer(false);
		super.setHojaIzq(false);
		super.setMoldura(false);
		super.setMacetero(false);
	}

	@Override
	public void romper(){
		super.romper();
		super.setRoto(super.getRoto()*4);
	}

}
