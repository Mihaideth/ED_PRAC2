package Excepcions;

public class LlistaBuida extends Exception{
	
	private static final long serialVersionUID=1L;
	
	public LlistaBuida(){
		super("La llista esta buida");
	}

}
