package Excepcions;

public class LlistaPlena extends Exception{
	
	private static final long serialVersionUID=1L;
	
	public LlistaPlena(){
		super("La llista esta plena");
	}

}
