import java.util.ArrayList;

import Excepcions.JugadorExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaPlena;

public class Array_List implements Interficie {
	ArrayList<Jugador> alist;

	/**
	 * Constructor de l'ArrayList que crea on guardar la instancia del jugador
	 */
	public Array_List() {
		alist = new ArrayList<Jugador>();
	}

	/**
	 * Metode per buscar un jugador mitjançant l'identificador
	 * @param id identificador del jugador
	 * @return posicio a la llista de jugadors del jugador en questio
	 */
	public int getPosicio(int id) {
		int i = 0, aux = alist.size();

		while (i < aux) {
			if (alist.get(i).getId() == id)
				return i;
			else
				i++;
		}
		return -1;
	}

	/**
	 * Metode per afegir un jugador a la llista
	 * @param j és l'objecte Jugador que volem afegir
	 * @throws LlistaPlena dona error si la llista està plena
	 * @throws JugadorExisteix dóna error ja existeix un jugador amb aquesta id
	 */
	public void afegirJugador(Jugador j) throws LlistaPlena, JugadorExisteix {
		int posicio = getPosicio(j.getId());

		if (posicio == -1) {
			alist.add(j);
		} else
			throw new JugadorExisteix();
	}

	/**
	 * Metode que retorna una llista amb tots els equips diferents
	 * @return el array d'equips
	 */
	public String[] getEquips() {
		ArrayList<String> equips = new ArrayList<String>();

		for (int i = 0; i < alist.size(); i++) {
			if (!equips.contains(alist.get(i).getEquip())) {
				equips.add(alist.get(i).getEquip());
			}
		}
		return equips.toArray(new String[0]);
	}

	/**
	 * Metode que retorna un array amb totes les posicions diferents
	 * @return el array de posicions
	 */
	public String[] getPosicions() {

		ArrayList<String> posicio = new ArrayList<String>();

		for (int i = 0; i < alist.size(); i++) {
			if (!posicio.contains(alist.get(i).getPosicio())) {
				posicio.add(alist.get(i).getPosicio());
			}
		}
		return posicio.toArray(new String[0]);

	}

	/**
	 * Metode que retorna la instancia del jugador corresponent a la ID passada per parametre
	 * @param id es la ID del Jugador en questio
	 * @return la instancia de l'objecte Jugador
	 * @throws JugadorNoExisteix si no existeix cap Jugador amb la ID introduida
	 */
	public Jugador getJugador(int id) throws JugadorNoExisteix {

		int pos;
		pos = getPosicio(id);
		if (pos == -1)
			throw new JugadorNoExisteix();
		else
			return alist.get(pos);
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
		alist.remove(pos);
	}

	/**
	 * Metode que retorna el numero de jugadors de l'estructura
	 * @return el numero de jugadors guardats dins l'estructura
	 */
	public int getNumJugadors() {
		return alist.size();
	}

	/**
	 * Metode que retorna una array d'enters amb tots els identificadors dels jugadors guardats dins l'estructura
	 * @return un array d'enters amb tots els identificadors dels jugadors guardats dins l'estructura
	 */
	public int[] getAllJugadors() {
		int[] jugadorstotals = new int[alist.size()];
		for (int i = 0; i < alist.size(); i++) {
			jugadorstotals[i] = alist.get(i).getId();
		}
		return jugadorstotals;
	}

}
