package personajes;

import utils.Evento;
import utils.Evento.EventoID;
import utils.Posicion;
import utils.Utils;

public class Pastel extends Chocable{

	private int timer = Utils.tiempoPastel;
	private REQ PASTEL1 = new REQ(0,2);
	private REQ PASTEL2 = new REQ(1,2);
	public Pastel(Posicion pos) {
		super(pos);
		super.agregarImagen("res/img/pastel/pastel1.png");
		super.agregarImagen("res/img/pastel/pastel2.png");
	}

	@Override
	public void actualizar() throws Evento {
		super.refresh();
		this.timer--;
		super.requests.add(PASTEL1);
		super.requests.add(PASTEL2);
		if(timer==0){
			super.requests.clear();
			throw new Evento(EventoID.OFF_SCREEN,this);
		}
	}
	
}
