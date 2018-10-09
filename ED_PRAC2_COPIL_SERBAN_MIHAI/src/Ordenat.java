import Excepcions.JugadorExisteix;
import Excepcions.LlistaPlena;

public class Ordenat extends Equip {

	public Ordenat(String nom){
		super(nom);
	}
	
	/**
	 * Metode que afegeix un nou objecte Jugador a la llista de forma ordenada
	 * @param j és l'objecte Jugador que volem afegir
	 * @throws LlistaPlena dona error si la llista està plena
	 * @throws JugadorExisteix dóna error si ja existeix un jugador amb aquesta id
	 */
	public void afegirJugador(Jugador j) throws LlistaPlena, JugadorExisteix {
		int i = 0;
		if (n_jugadors == 0) {
			llista[n_jugadors++] = j;

		} else {
			if (getPosicio(j.getId()) != -1){
				throw new JugadorExisteix();
			}
			if (llista[n_jugadors - 1].getId() < j.getId()) {
				llista[n_jugadors++] = j;
			}
			else {while ((llista[i].getId()<j.getId()) && (i<n_jugadors)) {
				i++;
			}
			if (i < llista.length) {
				for (int comptador=n_jugadors; comptador>i; comptador--) {
					llista[comptador] = llista[comptador-1];
				}
				llista[i] = j;
				n_jugadors++;
			}
			}

		}
		if (n_jugadors >= llista.length)
			throw new LlistaPlena();
	}
	
	/**
	 * Metode per buscar un jugador mitjançant l'identificador de forma dicotomica 
	 * @param id identificador del jugador
	 * @return posicio a la llista de jugadors del jugador en questio
	 */

	public int getPosicio(int id) {
		int inicio = 0;
		int fin = n_jugadors - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (llista[pos].getId() == id)
				return pos;
			else if (llista[pos].getId() < id) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return -1;
	}



		

}