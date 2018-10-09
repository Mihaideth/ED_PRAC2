package Excepcions;

public class EquipExisteix extends Exception{

	private static final long serialVersionUID=1L;
	
	public EquipExisteix(){
		super("L'equip ja existeix a la llista");
	}

}

	

