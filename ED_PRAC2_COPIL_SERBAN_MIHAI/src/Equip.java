import Excepcions.JugadorExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaPlena;

public abstract class Equip implements Interficie {

	protected Jugador[] llista;
	protected int n_jugadors;
	protected String nom;

	
	/**
	 * Constructor del TAD Jugadors que crea un nou objecte per guardar jugadors
	 * @param nom nom de l'equip
	 */
	public Equip(String nom) {
		llista = new Jugador[100];
		n_jugadors = 0;
		this.nom=nom;

	}

	/**
	 * Metode per afegir un jugador a la llista
	 */
	public abstract void afegirJugador(Jugador j) throws LlistaPlena,
			JugadorExisteix;

	
	/**
	 * Metode per buscar un jugador mitjançant l'identificador
	 * @param id identificador del jugador
	 * @return posicio a la llista de jugadors del jugador en questio
	 */
	public abstract int getPosicio(int id);
	
	/**
	 * Metode que retorna el nom de l'equip
	 * @return el nom de l'equip
	 */
		public String getNom(){
		return (nom);
	}
	

	/**
	 * Metode que retorna la instancia del jugador corresponent a la ID passada per parametre
	 * @param id identificador del jugador
	 * @return la instancia de l'objecte Jugador
	 * @throws JugadorNoExisteix si no existeix cap Jugador amb la ID introduida
	 */
	public Jugador getJugador(int id) throws JugadorNoExisteix {
		int pos;
		pos = getPosicio(id);
		if (pos == -1)
			throw new JugadorNoExisteix();
		else
			return llista[getPosicio(id)];
	}


	/**
	 * Metode que esborra un jugador segons la ID que li passem per parametre
	 * @param id del Jugador que volem esborrar
	 * @throws JugadorNoExisteix si no existeix cap Jugador amb la ID introduida
	 */
	public void esborrarJugador(int id) throws JugadorNoExisteix {
		int pos;
		pos = getPosicio(id);
		if (pos == -1)
			throw new JugadorNoExisteix();
		for (int i = pos; i < n_jugadors - 1; i++)
			llista[i] = llista[i + 1];

		n_jugadors--;
		llista[n_jugadors] = null;
	}

	/**
	 * Metode que retorna el numero de jugadors de l'estructura
	 * @return el numero de jugadors guardats dins l'estructura
	 */
	public int getNumJugadors() {
		return (n_jugadors);

	}

	/**
	 * Metode que retorna una llista d'enters amb tots els identificadors dels jugadors guardats dins l'estructura
	 * @return una llista d'enters amb tots els identificadors dels jugadors guardats dins l'estructura
	 */
	public int[] getAllJugadors() {

		int[] jugadorstotals = new int[n_jugadors];

		for (int i = 0; i < n_jugadors; i++) {
			jugadorstotals[i] = llista[i].getId();
		}
		return jugadorstotals;

	}

	/**
	 * Metode toString de Equip
	 */
	public String toString() {
		String retorna="";
		for(int i=0;i<n_jugadors;i++){
			retorna=retorna+llista[i].toString();
			retorna=retorna+"\n---";
		}
		return "Equip nom=" + nom + ", n_jugadors="
				+ n_jugadors + " llista=" + retorna ;
	}
	
	


}
