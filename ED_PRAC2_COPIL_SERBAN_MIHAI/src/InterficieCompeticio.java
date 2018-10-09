import Excepcions.EquipExisteix;
import Excepcions.EquipNoExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaBuida;
import Excepcions.LlistaPlena;


public interface InterficieCompeticio {

	void afegirEquip(Equip equip, boolean ordenat) throws LlistaPlena, EquipExisteix;
	void esborrarEquip(String nom) throws EquipNoExisteix, LlistaBuida;
	Equip getEquip(String nom) throws EquipNoExisteix;
	int getNumEquips();
	int[] getJugadorsEquip(String nom) throws EquipNoExisteix;
	Jugador getJugador(int id) throws JugadorNoExisteix;
	Equip getUltim() throws EquipNoExisteix, LlistaBuida;
	String[] getNomEquips();

}
