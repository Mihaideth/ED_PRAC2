import java.util.Arrays;

import Excepcions.JugadorExisteix;
import Excepcions.LlistaPlena;

public class Desordenat extends Jugadors {

	public Desordenat(int max) {
		super(max);
	}

	/**
	 * Metode que afegeix un nou objecte Jugador a la llista de forma no ordenada
	 * @param j és l'objecte Jugador que volem afegir
	 * @throws LlistaPlena dona error si la llista està plena
	 * @throws JugadorExisteix dóna error si ja existeix un jugador amb aquesta id
	 */													
	public void afegirJugador(Jugador j) throws LlistaPlena, JugadorExisteix {
		if (n_jugadors < llista.length) {
			if (getPosicio(j.getId()) == -1) {
				llista[n_jugadors] = j;
				n_jugadors++;
			} else
				throw new JugadorExisteix();
		} else
			throw new LlistaPlena();

	}

	/**
	 * Metode per buscar un jugador mitjançant l'identificador
	 * @param id identificador del jugador
	 * @return posicio a la llista de jugadors del jugador en questio
	 */
	public int getPosicio(int id) {
		int i, pos;
		pos = 0;
		i = 0;
		boolean trobat;
		trobat = false;
		while ((i < n_jugadors) && (!trobat)) {
			if (id == llista[i].getId()) {
				trobat = true;
				pos = i;
			}
			i++;
		}

		if (trobat) {
			return pos;
		} else {
			return -1;
		}

	}

	@Override
	public String toString() {
		return "Desordenat [llista=" + Arrays.toString(llista)
				+ ", n_jugadors=" + n_jugadors + "]";
	}

}
