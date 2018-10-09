import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Excepcions.JugadorExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaPlena;

public class Main {

	static Scanner teclat = new Scanner(System.in);
	static Interficie llista;
	static long start, end, res;

	public static void Menu() {
		System.out.println("\n\nMENU:");
		System.out.println("\n\t1. Esborrar les dades d’un jugador");
		System.out.println("\t2. Mostrar les dades d’un jugador");
		System.out
				.println("\t3. Llistar tots els equips i les posicions que hi ha dins l’estructura ");
		System.out.println("\t4. Llistar tots els jugadors d’un equip");
		System.out
				.println("\t5. Llistar tots els jugadors que juguen en una posició");
		System.out.println("\t6. Sortir");
		System.out.print("\n\t\t\tQuina opcio vols escollir?:\n");
	}

	/**
	 * Mètode que borra un jugador segons la id introduida per teclat
	 */
	public static void esborrarJugador() {
		System.out
				.println("Introdueix l'identificador del jugador que vols esborrar");
		int codi = llegirEnter();
		try {
			start = System.nanoTime();
			llista.esborrarJugador(codi);
			end = System.nanoTime();
			res = end - start;
			System.out.println("Temps en nanosegons: "+res);
			System.out.println("El jugador ha estat esborrat");
		} catch (JugadorNoExisteix e) {
			System.out.println(e);
		}

	}

	/**
	 * Mètode que mostra el jugador segons la id introduida per teclat
	 */
	public static void mostraInformacio() {

		System.out
				.println("Introdueix l'identificador del jugador que vols buscar");
		int codi = llegirEnter();
		try {
			start = System.nanoTime();
			System.out.println(llista.getJugador(codi).toString());
			end = System.nanoTime();
			res = end - start;
			System.out.println("Temps en nanosegons: "+res);

		} catch (JugadorNoExisteix e) {
			System.out.println(e);
		}

	}

	/**
	 * Mètode que mostra tots els equips i posicions que hi ha dins l'estructura
	 */
	public static void llistaEquipsPosicions() {
		
		start = System.nanoTime();
		String[] equips = llista.getEquips();
		String[] posicions = llista.getPosicions();
		System.out.println("Tots els equips: ");
		for (int i = 0; i < equips.length; i++) {
			System.out.println(equips[i]);
		}
		System.out.println("Totes les posicions: ");
		for (int i = 0; i < posicions.length; i++) {
			System.out.println(posicions[i]);
		}
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);

	}
	

	/**
	 * Mètode que mostra tots els jugadors d'un mateix equip introduit per teclat
	 */
	public static void llistaJugadorsEquip() {
		Jugador j = null;
		System.out.println("Introdueix el nom de l'equip en questio");
		String equip = teclat.next();

		int[] id = llista.getAllJugadors();
		System.out.println("El jugadors de l'equip:" + equip);
		start = System.nanoTime();
		for (int i = 0; i < id.length; i++) {
			try {
				j = llista.getJugador(id[i]);
			} catch (JugadorNoExisteix e) {
				System.out.println(e);
			}

			if (j.getEquip().equalsIgnoreCase(equip)) {
				System.out.println("" + j.getNom());
			}

		}
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);

	}

	/**
	 * Mètode que mostra tots els jugadors d'una mateixa posicio introduida per teclat
	 */
	public static void llistaJugadorsPosicio() {

		Jugador j = null;
		System.out.println("Introdueix el nom de la posicio en questio");
		String posicio = teclat.next();

		int[] id = llista.getAllJugadors();
		System.out.println("El jugadors que juguen en la posicio:" + posicio);
		start = System.nanoTime();
		for (int i = 0; i < id.length; i++) {
			try {
				j = llista.getJugador(id[i]);
			} catch (JugadorNoExisteix e) {
				System.out.println(e);
			}

			if (j.getPosicio().equalsIgnoreCase(posicio)) {
				System.out.println("" + j.getNom());
			}

		}
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);

	}
	
	/**
	 * Mètode que carrega els jugadors des d'un fitxer
	 * @throws IOException Fitxer no trobat
	 */
	public static void ImportarArxiu() throws IOException {
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader("file.txt"));
			String linea = "";
			linea = br.readLine();
			while (linea != null) {
				StringTokenizer st = new StringTokenizer(linea, ",");
				int id = Integer.parseInt(st.nextToken());
				String equip = st.nextToken();
				int dorsal = Integer.parseInt(st.nextToken());
				String nom = st.nextToken();
				String posicio = st.nextToken();
				Jugador j = new Jugador(id, nom, dorsal, posicio, equip);
				try {
					llista.afegirJugador(j);
				} catch (JugadorExisteix e) {
					System.out
							.println("El jugador ja existeix, per tant no s'afegeix");
				}
				linea = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fitxer no trobat");
		} catch (LlistaPlena e) {
			System.out
					.println("La llista ja esta plena, no es pot continuar afegint");
		} finally {
			br.close();
		}

	}

	/**
	 * Metode que comproba que el valor introduit sigui un Enter;
	 * @return retorna el valor que estem comprobant si aquest es valid;
	 */
	public static int llegirEnter() {
		boolean comprobat = false;
		int valor = 0;
		while (!comprobat) {
			try {
				String s = teclat.next();
				valor = Integer.parseInt(s);
				comprobat = true;
			} catch (NumberFormatException e) {
				System.out.print("Error en el format del codi\n");
			}
		}
		return valor;
	}

	public static void main(String[] args) {

		int opcio;
		int capacitat = 0;

		System.out
				.println("\n A quina de les 3 implementacions vols tenir acces?:");
		System.out.println("\n\t1. Llista Ordenada");
		System.out.println("\n\t2. Llista No Ordenada");
		System.out.println("\n\t3. ArrayList");
		System.out.println("\n\t4. Sortir");
		int n = llegirEnter();

		while (n != 4) {
			while ((n < 0) || (n > 4)) {
				System.out
						.println("\n A quina de les 3 implementacions vols tenir acces?:");
				System.out.println("\n\t1. Llista Ordenada");
				System.out.println("\n\t2. Llista No Ordenada");
				System.out.println("\n\t3. ArrayList");
				System.out.println("\n\t4. Sortir");
				n = llegirEnter();

			}

			if ((n == 1) || (n == 2)) {
				System.out
						.println("Introdueix la capacitat maxima del conjunt");
				capacitat = llegirEnter();
			}

			if (n == 1) {
				llista = new Ordenat(capacitat);
				System.out.println("S'ha creat correctament");

			}

			if (n == 2) {
				llista = new Desordenat(capacitat);
				System.out.println("S'ha creat correctament");
			}

			if (n == 3) {
				llista = new Array_List();
				System.out.println("S'ha creat correctament");

			}
			try {
				start = System.nanoTime();
				ImportarArxiu();
			} catch (IOException e) {
				System.out.println("Error al tractar l'arxiu");

			}
			end = System.nanoTime();
			res = end - start;
			System.out.println("Temps en nanosegons: "+res);

			Menu();
			opcio = llegirEnter();

			while (opcio != 6) {
				switch (opcio) {
				case 1:
					esborrarJugador();
					break;
				case 2:
					mostraInformacio();
					break;
				case 3:
					llistaEquipsPosicions();
					break;
				case 4:
					llistaJugadorsEquip();
					break;
				case 5:
					llistaJugadorsPosicio();
					break;
				}

				Menu();
				opcio = llegirEnter();
			}
		}
	}
}
