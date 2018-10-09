import Excepcions.EquipExisteix;
import Excepcions.EquipNoExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaBuida;
import Excepcions.LlistaPlena;


public class Dinamica implements InterficieCompeticio{
	private int n_equips;
	private Node primer, anterior;
	
	private class Node{
		Equip info;
		Node Nodeanterior, Nodeseguent;
	}
	
	/**
	 * Constructor de la classe Dinamica
	 */
	public Dinamica(){
		primer=null;
		anterior=null;
		n_equips = 0;
		
	}

	/**
	 * Metode que afegeix un nou equip a la llista
	 * @param equip equip a afegir
	 * @param ordenat si el volem afegir de forma ordenada
	 * @throws LlistaPlena si la llista esta plena
	 * @throws EquipExisteix si l'equip ja existeix a l'estructura
	 */
	public void afegirEquip(Equip equip, boolean ordenat) throws LlistaPlena, EquipExisteix {
		if(primer==null){
			//Aqui coloco el primer de tots
			primer= new Node();
			primer.info=equip;
			n_equips=n_equips+1;
			anterior=primer;
		}
		else{
			if(ordenat){
				//Ordenat
				Node Nanterior=primer, Nseguent=primer.Nodeseguent;
				boolean trobat=false;
				if(Nanterior.info.getNom().equalsIgnoreCase(equip.getNom())) throw new EquipExisteix();
				//Comprobo si ha d'anar el primer de tots
				if(equip.getNom().compareToIgnoreCase(primer.info.getNom())<0){
					Node aux = new Node();
					aux.info=equip;
					primer.Nodeanterior=aux;
					aux.Nodeseguent=primer;
					primer=aux;
					anterior=aux;
					n_equips=n_equips+1;
				}else{
					//Comprobo si existeix l'element a la llista
					while(!trobat && Nseguent!=null){
						if(Nseguent.info.getNom().equalsIgnoreCase(equip.getNom())) throw new EquipExisteix();
						if(equip.getNom().compareToIgnoreCase(Nseguent.info.getNom()) < 0) trobat=true;
						else{	Nanterior=Nseguent;
								Nseguent=Nseguent.Nodeseguent;
						}
					}
					Node aux = new Node();
					aux.info=equip;
					Nanterior.Nodeseguent=aux;
					aux.Nodeanterior=Nanterior;
					aux.Nodeseguent=Nseguent;
					if(Nseguent!=null) Nseguent.Nodeanterior=aux; 
					anterior=aux;
					n_equips=n_equips+1;
				}
			}
			
			else{
				//Desordenat
				Node Nseguent=primer;
				//Comprobo si existeix l'element a la llista
				while(Nseguent!=null){
					if(Nseguent.info.getNom().equalsIgnoreCase(equip.getNom())) throw new EquipExisteix();
					Nseguent=Nseguent.Nodeseguent;
				}
				Node aux3= new Node();
				aux3.info=equip;
				anterior.Nodeseguent=aux3;
				aux3.Nodeanterior=anterior;
				anterior=aux3;
				n_equips=n_equips+1;
			}
				
				
			}
	}
		

	/**
	 * Metode que elimina de la llista l’equip identificat pel nom que passem per paràmetre
	 * @param nom nom de l'equip
	 * @throws EquipNoExisteix si no existeix l'equip
	 * @throws LlistaBuida si la llista esta buida
	 */
	public void esborrarEquip(String nom) throws EquipNoExisteix, LlistaBuida {
		Node node=primer;
		boolean trobat=false;
		while((!trobat)&&(node!=null)){
			if(node.info.getNom().equalsIgnoreCase(nom)){
				if(node.Nodeanterior!=null){ 
					node.Nodeanterior.Nodeseguent=node.Nodeseguent;
				}
				if(node.Nodeseguent!=null){ 
					node.Nodeseguent.Nodeanterior=node.Nodeanterior;
				}
				if(node==anterior){
					anterior=null;
				}
				
				if(node==primer){
					primer=node.Nodeseguent;
				}
			
				trobat=true;
				n_equips=n_equips-1;
			}
			else{
				node=node.Nodeseguent;
			}
		}
		if(trobat==false) throw new EquipNoExisteix();
		
		if(primer==null) throw new LlistaBuida();
	
		
	}

	/**
	 * Metode que retorna l’objecte Equip identificat per la cadena que passem per paràmetre
	 * @param nom nom de l'equip
	 * @return l'instancia de l'equip
	 * @throws EquipNoExisteix si no existeix l'equip
	 */
	public Equip getEquip(String nom) throws EquipNoExisteix {
		
		Node node=primer;
		Equip e=null;
		boolean trobat=false;
		while((node!=null)&&(!trobat)){
			if(node.info.getNom().equalsIgnoreCase(nom)){ 
				e=node.info;
				trobat=true;
			}
			else{
				node=node.Nodeseguent;
				}
		}
			
		if(trobat==false) throw new EquipNoExisteix();
		return e;
		}
	
		

	/**
	 * Metode que retorna el numero d'equips de l'estructura
	 * @return el numero d'equips guardats dins l'estructura
	 */
	public int getNumEquips() {
		return (n_equips);
	}

	/**
	 * Metode que retorna un array amb tots els IDs dels jugadors que existeixin a l’equip identificat pel nom que passem per paràmetre
	 * @param nom nom de l'equip
	 * @return una llista amb els IDs dels jugadors
	 * @throws EquipNoExisteix si no existeix l'equip
	 */
	public int[] getJugadorsEquip(String nom) throws EquipNoExisteix {
		Node node=primer;
		boolean trobat=false;
		while((node!=null)&&(!trobat)){
			if(node.info.getNom().equalsIgnoreCase(nom)){
				trobat=true;
			}
			if(trobat==false)node=node.Nodeseguent;
		}
		if(trobat==false) throw new EquipNoExisteix();
		else{
			return node.info.getAllJugadors();
		}
		
	}

	/**
	 * Metode que retorna l’objecte Jugador identificat per l’enter que passem per paràmetre
	 * @param id identificador del jugador
	 * @return l'instancia del jugador
	 * @throws JugadorNoExisteix si no existeix el jugador dins l'estructura
	 */
	public Jugador getJugador(int id) throws JugadorNoExisteix {
		Node node=primer;
		boolean trobat=false;
		int i=0;
		Jugador j= null;
		while((n_equips>i) && (!trobat)){
			try{
				j=node.info.getJugador(id);
				trobat=true;
			}catch(JugadorNoExisteix e){
				node=node.Nodeseguent;
			}
			i=i+1;
		}
		if(trobat==false) throw new JugadorNoExisteix();
		else{
			return j;
		}
	}
	
		

	/**
	 * Metode que retorna l'ultim equip afegit a la llista
	 * @return l'equip en questio
	 * @throws EquipNoExisteix si no existeix l'equip
	 * @throws LlistaBuida si la llista esta buida
	 */
	public Equip getUltim() throws EquipNoExisteix, LlistaBuida {
		if(primer==null) throw new LlistaBuida();
		
		if(anterior==null) throw new EquipNoExisteix();
		return anterior.info; 
	}
	
	
	/**
	 * Metode que retorna un array amb els noms de tots els equips que hi ha guardats dins de l'estructura.
	 * @return noms de tots els equips que hi ha guardats dins de l'estructura
	 */
	public String[] getNomEquips(){
		String[] noms= new String[n_equips];
		Node aux = primer; 
		int i=0;
		while((aux!=null)&&(i<n_equips)) {
			noms[i] = aux.info.getNom();
			aux=aux.Nodeseguent;
			i++;
		}
		return noms;
	}
	
	
	/**
	 * Metode toString de la classe Dinamica
	 */
	public String toString() {
		return "Dinamica [n_equips=" + n_equips + ", primer=" + primer
				+ ", anterior=" + anterior + "]";
	}
	
	

	
	
}
