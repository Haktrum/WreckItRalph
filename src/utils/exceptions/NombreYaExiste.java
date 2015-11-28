package utils.exceptions;

public class NombreYaExiste extends BadInput{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2512560002840726207L;

	public NombreYaExiste() {
		super("<html><center>El nombre ya existe <br><br> ENTER para sobreescribir<center></html>");
	}

}
