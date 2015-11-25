package utils;

public class Evento extends Exception{

	private EventoID id;
	private Object param = null;
	public Evento(EventoID id) {
        super();
        this.id=id;
    }
	public Evento(EventoID id,Object param){
		this(id);
		this.param=param;
	}
	
	public enum EventoID{
		OFF_SCREEN,TERMINAJUEGO,GANASECCION,GANANIVEL,SALTA;
	}
	public EventoID getId(){
		return this.id;
	}
	public Object getParam(){
		return this.param;
	}
	
}