package utils.eventos;

@SuppressWarnings("serial")
public class Evento extends Exception {

	private Object param = null;

	public Evento() {
		
	}
	
	public Evento(Object param) {
		this.param = param;
	}

	public Object getParam() {
		return this.param;
	}

}