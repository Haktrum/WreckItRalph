package ambiente;

import juego.Contexto;

public class DobleHoja extends Ventana{
	
	public DobleHoja (){
		super();
		if(!(super.tieneMacetero() && super.tieneMoldura())){
			boolean b = Contexto.randomBoolean(20);
			super.setHojaIzq(b);
			if(b){
				super.setHojaDer(!b);
			}else{
				super.setHojaDer(Contexto.randomBoolean(20));
			}
			
		}
	}
	@Override
	public void romper(){
		super.setRoto(0);
	}
}
