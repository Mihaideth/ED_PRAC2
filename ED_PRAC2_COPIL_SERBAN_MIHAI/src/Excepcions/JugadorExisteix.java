package Excepcions;

public class JugadorExisteix extends Exception{

	private static final long serialVersionUID=1L;
	
	public JugadorExisteix(){
		super("El jugador ja existeix a la llista");
	}

}

	

