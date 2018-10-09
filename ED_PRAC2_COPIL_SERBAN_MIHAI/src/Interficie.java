import Excepcions.*;

public interface Interficie {
	void afegirJugador(Jugador j) throws LlistaPlena, JugadorExisteix;

	String getNom();

	Jugador getJugador(int id) throws JugadorNoExisteix;

	void esborrarJugador(int id) throws JugadorNoExisteix;

	int getNumJugadors();

	int[] getAllJugadors();

}
