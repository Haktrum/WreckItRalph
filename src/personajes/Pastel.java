package personajes;

import utils.Evento;
import utils.Evento.EventoID;
import utils.Posicion;
import utils.Utils;

public class Pastel extends Chocable{

	private int timer = Utils.tiempoPastel;
	public Pastel(Posicion pos) {
		super(pos, 0);
	}

	@Override
	public void actualizar() throws Evento {
		this.timer--;
		if(timer==0){
			throw new Evento(EventoID.CHAU_PASTEL,this);
		}
	}
	
}
