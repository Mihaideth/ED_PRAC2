import Excepcions.EquipExisteix;
import Excepcions.EquipNoExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaBuida;
import Excepcions.LlistaPlena;


public class Estatica implements InterficieCompeticio{
	private int n_equips;
	private int primer;
	private int anterior;
	private Node[] llista;
	private int primerBuit;
	
	
	private class Node{
		Equip info;
		int Nodeseguent;
		int Nodeanterior;
		private Node(){
			Nodeanterior=-1;
			Nodeseguent=-1;
			info=null;
		}
	}
	
	/**
	 * Constructor de la classe Estatica
	 * @param max capacitat de la llista
	 */
	public Estatica(int max){
		llista= new Node[max];
		for(int cont=0; cont<max; cont++){
			llista[cont]= new Node();
			if(cont!=max-1){
				llista[cont].Nodeseguent=cont+1;
			}
		}
		primer=-1;
		anterior=-1;
		n_equips=0;
		primerBuit=0;
	}

	/**
	 * Metode que afegeix un nou equip a la llista
	 * @param equip equip a afegir
	 * @param ordenat si el volem afegir de forma ordenada
	 * @throws LlistaPlena si la llista esta plena
	 * @throws EquipExisteix si l'equip ja existeix a l'estructura
	 */
	public void afegirEquip(Equip equip, boolean ordenat) throws LlistaPlena, EquipExisteix {
		//Miro si la llista esta plena
		if(n_equips>=llista.length) throw new LlistaPlena();
		if(primer==-1){
			//Aqui coloco el primer de tots
			primer=primerBuit;
			llista[primer].info=equip;
			primerBuit=llista[primerBuit].Nodeseguent;
			llista[primer].Nodeseguent=-1;
			anterior=primer;
			n_equips=n_equips+1;
		}
		else{
			int actual=primer;
			int aux=primer;
			boolean trobat=false;
			if(ordenat){
				//Ordenat
				//Comprobo si existeix l'element a la llista
				if(llista[aux].info.getNom().equalsIgnoreCase(equip.getNom())) throw new EquipExisteix();
				if(llista[primer].info.getNom().compareToIgnoreCase(equip.getNom())>0){
					//Colocar l'element al principi
					actual=primerBuit;
					primerBuit=llista[primerBuit].Nodeseguent;
					llista[actual].info=equip;
					llista[actual].Nodeseguent=primer;
					llista[actual].Nodeanterior=-1;
					llista[primer].Nodeanterior=actual;
					primer=actual;
					anterior=actual;
					n_equips=n_equips+1;
				
				}
				else{	
					aux=primer;
					while((aux!=-1)&&(!trobat)){
						//Comprobo si existeix l'element a la llista
						if(llista[aux].info.getNom().equalsIgnoreCase(equip.getNom())) throw new EquipExisteix();
						if(llista[aux].info.getNom().compareToIgnoreCase(equip.getNom())>0){
							//Coloco l'element entremig
							llista[primerBuit].info=equip;
							actual=primerBuit;
							primerBuit=llista[primerBuit].Nodeseguent;
							llista[actual].Nodeseguent=aux;
							llista[actual].Nodeanterior=llista[aux].Nodeanterior;
							llista[llista[aux].Nodeanterior].Nodeseguent=actual;
							llista[aux].Nodeanterior=actual;
							anterior=actual;
							n_equips=n_equips+1;
							trobat=true;
						}
						if((llista[aux].info.getNom().compareToIgnoreCase(equip.getNom())<0)&&(llista[aux].Nodeseguent==-1)){
							//Colocar l'element al final
							actual=primerBuit;
							primerBuit=llista[primerBuit].Nodeseguent;
							llista[actual].info=equip;
							llista[actual].Nodeanterior=aux;
							llista[actual].Nodeseguent=-1;
							llista[aux].Nodeseguent=actual;
							anterior=actual;
							n_equips=n_equips+1;
							trobat=true;
						}
					aux=llista[aux].Nodeseguent;
					}
				}
			}
			
			else{
				//Desordenat
				//Comprobo si existeix l'element a la llista
				while(actual!=-1){
					if(llista[actual].info.getNom().equalsIgnoreCase(equip.getNom())) throw new EquipExisteix();
					actual=llista[actual].Nodeseguent;
				}
				aux=primerBuit;
				llista[aux].info=equip;
				llista[anterior].Nodeseguent=aux;
				llista[aux].Nodeanterior=anterior;
				anterior=aux;
				primerBuit=llista[primerBuit].Nodeseguent;
				llista[aux].Nodeseguent=-1;
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
		int aux=primer;
		boolean trobat=false;
		while((aux!=-1)&&(!trobat)){
			if(llista[aux].info.getNom().equalsIgnoreCase(nom)){
				
				if(llista[aux].Nodeanterior!=-1){
					llista[llista[aux].Nodeanterior].Nodeseguent=llista[aux].Nodeseguent;
				}
				if(llista[aux].Nodeseguent!=-1){
					llista[llista[aux].Nodeseguent].Nodeanterior=llista[aux].Nodeanterior;
				}
				if(aux==anterior){
					anterior=-1;
				}
				if(aux==primer){
					primer=llista[aux].Nodeseguent;
				}
				trobat=true;
				n_equips=n_equips-1;
				llista[aux].Nodeseguent=primerBuit;
				primerBuit=aux;
			}
			else{
				aux=llista[aux].Nodeseguent;
			}
			
		}
		if(!trobat) throw new EquipNoExisteix();
		if(primer==-1) throw new LlistaBuida();
	}

	/**
	 * Metode que retorna l’objecte Equip identificat per la cadena que passem per paràmetre
	 * @param nom nom de l'equip
	 * @return l'instancia de l'equip
	 * @throws EquipNoExisteix si no existeix l'equip
	 */
	public Equip getEquip(String nom) throws EquipNoExisteix {
		int aux=primer;
		Equip e=null;
		boolean trobat=false;
		while((aux!=-1)&&(!trobat)){
			if(llista[aux].info.getNom().equalsIgnoreCase(nom)){ 
				e=llista[aux].info;
				trobat=true;
			}	
			else{	
				aux=llista[aux].Nodeseguent;
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
		int aux=primer;
		boolean trobat=false;
		while((aux!=-1)&&(!trobat)){
			if(llista[aux].info.getNom().equalsIgnoreCase(nom)){
				trobat=true;
			}
			if(trobat==false)aux=llista[aux].Nodeseguent;
		}
		if(trobat==false) throw new EquipNoExisteix();
		else{
			return llista[aux].info.getAllJugadors();
		}
	}

	/**
	 * Metode que retorna l’objecte Jugador identificat per l’enter que passem per paràmetre
	 * @param id identificador del jugador
	 * @return l'instancia del jugador
	 * @throws JugadorNoExisteix si no existeix el jugador dins l'estructura
	 */
	public Jugador getJugador(int id) throws JugadorNoExisteix {
		int aux=primer;
		boolean trobat=false;
		Jugador j= null;
		int i=0;
		while((i<n_equips) && (!trobat)){
			try{
				j=llista[aux].info.getJugador(id);
				trobat=true;
			}catch(JugadorNoExisteix e){
				aux=llista[aux].Nodeseguent;
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
		if(primer==-1) throw new LlistaBuida();
		
		if(anterior==-1) throw new EquipNoExisteix();
		return llista[anterior].info;
	}
	
	/**
	 * Metode que retorna un array amb els noms de tots els equips que hi ha guardats dins de l'estructura.
	 * @return noms de tots els equips que hi ha guardats dins de l'estructura
	 */
	public String[] getNomEquips(){
		String[] noms= new String[n_equips];
		int aux = primer; 
		int i=0;
		while(aux!=-1){
			noms[i] = llista[aux].info.getNom();
			aux=llista[aux].Nodeseguent;
			i++;
		}
		return noms;
	}

	
	

	/**
	 * Metode toString de la classe Estatica
	 */
	public String toString() {
			
		
		String retorna="";
		for(int i=0;i<n_equips;i++){
			retorna=retorna+llista[i].info.toString();
			retorna=retorna+"\n---";
		}
		
		return "Estatica [n_equips=" + n_equips + ", primer=" + primer
				+ ", anterior=" + anterior + ", llista="
				+ retorna + ", primerBuit=" + primerBuit + "]";
	}
		
	

}
