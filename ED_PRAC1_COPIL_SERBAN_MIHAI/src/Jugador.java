public class Jugador {
	private int id;
	private String nom;
	private int dorsal;
	private String posicio;
	private String equip;

	/**
	 * Constructor de la classe Jugador
	 * @param id identificador del jugador
	 * @param nom nom del jugador
	 * @param dorsal dorsal del jugador
	 * @param posicio posicio del jugador
	 * @param equip equip del jugador
	 */
	public Jugador(int id, String nom, int dorsal, String posicio, String equip) {
		this.id = id;
		this.nom = nom;
		this.dorsal = dorsal;
		this.posicio = posicio;
		this.equip = equip;
	}

	/**
	 * Metode que retorna el identificador del jugador
	 * @return el identificador del jugador
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metode que retorna el nom del jugador
	 * @return el nom del jugador
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Metode que retorna el dorsal del jugador
	 * @return el dorsal del jugador
	 */
	public int getDorsal() {
		return dorsal;
	}

	/**
	 * Metode que retorna la posicio del jugador
	 * @return la posicio del jugador
	 */
	public String getPosicio() {
		return posicio;
	}

	/**
	 * Metode que retorna l'equip del jugador
	 * @return l'equip del jugador
	 */
	public String getEquip() {
		return equip;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nom=" + nom + ", dorsal=" + dorsal
				+ ", posicio=" + posicio + ", equip=" + equip + "]";
	}

}
