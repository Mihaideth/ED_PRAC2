import java.util.Arrays;

import Excepcions.JugadorExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaPlena;

public abstract class Jugadors implements Interficie {

	protected Jugador[] llista;
	protected int n_jugadors;

	
	/**
	 * Constructor del TAD Jugadors que crea un nou objecte per guardar jugadors
	 * @param max capacitat màxima d'elements que es poden guardar a la llista
	 */
	public Jugadors(int max) {
		llista = new Jugador[max];
		n_jugadors = 0;

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
	 * Metode que retorna una llista amb tots els equips diferents
	 * @return la llista d'equips redimensionada
	 */
	public String[] getEquips() {

		String[] equips = new String[llista.length];
		int n_equips = 0;
		for (int i = 0; i < n_jugadors; i++) {
			if (!repetits(equips, n_equips, llista[i].getEquip())) {
				equips[n_equips] = llista[i].getEquip();
				n_equips++;
			}
		}

		String[] equipsRed = new String[n_equips];
		for (int i = 0; i < n_equips; i++) {
			equipsRed[i] = equips[i];
		}

		return equipsRed;
	}

	/**
	 * Metode per comprobar que no es repetexin equips o posicions (segons on es cridi)
	 * @param equips llista de strings que conte el nom dels equips
	 * @param n_equips nombre d'equips a la llista
	 * @param equip	nom de l'equip a comprobar
	 * @return si el string ja existeix a la llista o no
	 */
	public boolean repetits(String[] equips, int n_equips, String equip) {
		boolean repetit = false;
		int i = 0;
		while ((i < n_equips) && (!repetit)) {
			if (equips[i].equalsIgnoreCase(equip))
				repetit = true;
			i++;
		}
		return repetit;

	}

	/**
	 * Metode que retorna una llista amb totes les posicions diferents
	 * @return la llista de posicions redimensionada
	 */
	public String[] getPosicions() {

		String[] posicions = new String[llista.length];
		int n_pos = 0;
		for (int i = 0; i < n_jugadors; i++) {
			if (!repetits(posicions, n_pos, llista[i].getPosicio()))
				posicions[n_pos++] = llista[i].getPosicio();
		}

		String[] posicionsRed = new String[n_pos];
		for (int i = 0; i < n_pos; i++) {
			posicionsRed[i] = posicions[i];
		}

		return posicionsRed;
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

	@Override
	public String toString() {
		return "Jugadors [llista=" + Arrays.toString(llista) + ", n_jugadors="
				+ n_jugadors + "]";
	}

}
