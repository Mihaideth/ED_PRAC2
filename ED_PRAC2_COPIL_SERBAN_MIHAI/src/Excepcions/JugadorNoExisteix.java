package Excepcions;

public class JugadorNoExisteix extends Exception{
private static final long serialVersionUID=1L;
	
	public JugadorNoExisteix(){
		super("No hi ha cap jugador amb aquesta ID");
	}

}



