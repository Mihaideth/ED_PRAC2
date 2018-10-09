import Excepcions.EquipExisteix;
import Excepcions.EquipNoExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaBuida;
import Excepcions.LlistaPlena;


public class Fantasma implements InterficieCompeticio {
	private int n_equips;
	private Node fantasma, anterior;
	
	private class Node{
		Equip info;
		Node Nodeanterior, Nodeseguent;
	}
	
	/**
	 * Constructor de la classe Fantasma
	 */
	public Fantasma(){
		fantasma=new Node();
		fantasma.Nodeseguent=null;
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
		if(fantasma.Nodeseguent==null){
			//Aqui coloco el primer de tots
			fantasma.Nodeseguent= new Node();
			fantasma.Nodeseguent.info=equip;
			n_equips=n_equips+1;
			anterior=fantasma.Nodeseguent;
		}
		else{
			if(ordenat){
				//Ordenat
				Node Nanterior=fantasma.Nodeseguent, Nseguent=fantasma.Nodeseguent.Nodeseguent;
				boolean trobat=false;
				if(Nanterior.info.getNom().equalsIgnoreCase(equip.getNom())) throw new EquipExisteix();
				//Comprobo si ha d'anar el primer de tots
				if(equip.getNom().compareToIgnoreCase(fantasma.Nodeseguent.info.getNom())<0){
					Node aux = new Node();
					aux.info=equip;
					fantasma.Nodeseguent.Nodeanterior=aux;
					aux.Nodeseguent=fantasma.Nodeseguent;
					fantasma.Nodeseguent=aux;
					anterior=aux;
					n_equips++;
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
					n_equips++;
				}
			}
			
			else{
				//Desordenat
				Node Nseguent=fantasma.Nodeseguent;
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
	 */
	public void esborrarEquip(String nom) throws EquipNoExisteix, LlistaBuida {
		Node node=fantasma.Nodeseguent;
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
				if(node==fantasma.Nodeseguent){
					fantasma.Nodeseguent=node.Nodeseguent;
				}
				
				trobat=true;
				n_equips=n_equips-1;
			}
			else{
				node=node.Nodeseguent;
			}
		}
		if(trobat==false) throw new EquipNoExisteix();
		
		if(fantasma.Nodeseguent==null) throw new LlistaBuida();
		
	}

	/**
	 * Metode que retorna l’objecte Equip identificat per la cadena que passem per paràmetre
	 * @param nom nom de l'equip
	 * @return l'instancia de l'equip
	 * @throws EquipNoExisteix si no existeix l'equip
	 */
	public Equip getEquip(String nom) throws EquipNoExisteix {
		
		Node node=fantasma.Nodeseguent;
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
		Node node=fantasma.Nodeseguent;
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
		Node node=fantasma.Nodeseguent;
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
	 */
	public Equip getUltim() throws EquipNoExisteix, LlistaBuida {
		if(fantasma.Nodeseguent==null) throw new LlistaBuida();

		if(anterior==null) throw new EquipNoExisteix();
		return anterior.info;
	}
	
	/**
	 * Metode que retorna un array amb els noms de tots els equips que hi ha guardats dins de l'estructura.
	 * @return noms de tots els equips que hi ha guardats dins de l'estructura
	 */
	public String[] getNomEquips(){
		String[] noms= new String[n_equips];
		Node nodo = fantasma.Nodeseguent; 
		int i=0;
		while(nodo!=null){
			noms[i] = nodo.info.getNom();
			nodo=nodo.Nodeseguent;
			i++;
		}
		return noms;
	}
	

	
	/**
	 * Metode toString de la classe Fantasma
	 */
	public String toString() {
		return "Fantasma [n_equips=" + n_equips + ", primer=" + fantasma.Nodeseguent
				+ ", anterior=" + anterior + "]";
	}

	
	
	

	
	
}
