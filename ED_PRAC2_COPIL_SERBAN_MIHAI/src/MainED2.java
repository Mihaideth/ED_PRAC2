import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import Excepcions.EquipExisteix;
import Excepcions.EquipNoExisteix;
import Excepcions.JugadorExisteix;
import Excepcions.JugadorNoExisteix;
import Excepcions.LlistaBuida;
import Excepcions.LlistaPlena;



public class MainED2 {

	static Scanner teclat = new Scanner(System.in);
	static InterficieCompeticio llista;
	static long start, end, res;

	public static void Menu() {
		System.out.println("\n\nMENU:");
		System.out.println("\n\t1. Consultar tots els jugadors d’un equip");
		System.out.println("\t2. Consultar la informació d’un jugador");
		System.out.println("\t3. Consultar l’últim equip afegit i l’equip que té més jugadors a la plantilla");
		System.out.println("\t4. Esborrar un equip i tots els seus jugadors");
		System.out.println("\t5. Mostrar tots els jugadors (de tots els equips) que juguen a una posició");
		System.out.println("\t6. Sortir");
		System.out.print("\n\t\t\tQuina opcio vols escollir?:\n");
	}
	
	/**
	 * Mètode que mostra tots els jugadors d'un mateix equip introduit per teclat
	 */
	public static void jugadorsEquip() {
		Jugador j = null;
		int[] id=null;
		System.out.println("Introdueix el nom de l'equip en questio");
		String equip = teclat.next();
		
		System.out.println("Els jugadors de l'equip:" + equip);
		start = System.nanoTime();
		try{
			id = llista.getJugadorsEquip(equip);
			for (int i = 0; i < id.length; i++) {
				try {
					j = llista.getJugador(id[i]);
					System.out.println("" + j.toString());
				} catch (JugadorNoExisteix evt) {
					System.out.println(evt);
				}
			}
		}catch(EquipNoExisteix e){
			System.out.println(e);
		}
		
	
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);

	}



	/**
	 * Mètode que mostra el jugador segons la id introduida per teclat
	 */
	public static void consultarInformacio() {

		System.out.println("Introdueix l'identificador del jugador que vols buscar");
		int codi = llegirEnter();
		try {
			start = System.nanoTime();
			System.out.println(llista.getJugador(codi).toString());
			

		} catch (JugadorNoExisteix e) {
			System.out.println(e);
		}
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);

	}
	
	
	/**
	 * Metode que mostra l'ultim equip afegit i l'equip que te mes jugadors
	 */
	public static void ultimEquip(){
		
		//Ultim equip afegit
		Equip e=null;
		try{
			start = System.nanoTime();
			try{
				e=llista.getUltim();
			}catch(LlistaBuida e2){
				System.out.println(e2);
			}
			System.out.println("L'ultim equip afegit es:"+e.getNom());
						
		}catch(EquipNoExisteix evt){
			System.out.println(evt);
		}
		
		
		//Equip mes jugadors
		String[] equips=llista.getNomEquips();
		
		int maxim=-1;
		for(int i=0; i<llista.getNumEquips(); i++){
			try{
				if(maxim<llista.getEquip(equips[i]).getAllJugadors().length){
					maxim=llista.getEquip(equips[i]).getAllJugadors().length;
					try{
						e=llista.getEquip(equips[i]);
					}catch(EquipNoExisteix evt3){
						System.out.println(evt3);
					}
				
				}
			}catch(EquipNoExisteix evt2){
				System.out.println(evt2);
			}
		}
		end = System.nanoTime();
		res = end - start;
		System.out.println("El equip amb mes jugadors es:" +e.getNom());
		System.out.println("Temps en nanosegons: "+res);
	}
	
	
	/**
	 * Mètode que borra un equip i tots els seus jugadors
	 */
	public static void esborrarEquip() {
		System.out.println("Introdueix el nom de l'equip que vols esborrar");
		String equip = teclat.next();
		try {
			start = System.nanoTime();
			llista.esborrarEquip(equip);
			end = System.nanoTime();
			res = end - start;
			System.out.println("Temps en nanosegons: "+res);
			System.out.println("El equip ha estat esborrat");
		} catch (EquipNoExisteix e) {
			System.out.println(e);
		} catch (LlistaBuida e2){
			System.out.println(e2);
		}

	}
	
	/**
	 * Metode que mostra tots els jugadors que juguen en un certa posicio
	 */
	public static void jugadorsPosicio(){
		System.out.println("Introdueix el nom de la posicio en questio");
		String posicio = teclat.next();
		String[] equips=llista.getNomEquips();
		Jugador[] llistajugadors=new Jugador[llista.getNumEquips()*100];
		int cont=0;
		int[] id=null;
		start = System.nanoTime();
		for(int i=0; i<llista.getNumEquips(); i++){
			try {
				id=llista.getEquip(equips[i]).getAllJugadors();
			} catch (EquipNoExisteix e) {
				System.out.println(e);
			}
			int cont2=0;
			while(cont2<id.length){
				try{
					if(llista.getJugador(id[cont2]).getPosicio().equalsIgnoreCase(posicio)){
						llistajugadors[cont]=llista.getJugador(id[cont2]);
						cont=cont+1;
					}
					cont2=cont2+1;
				}catch(JugadorNoExisteix e){
					System.out.println(e);
				}
			
			
		}
	}
		//Redimensionament de la taula
		Jugador[] jugadorsRed = new Jugador[cont];
		for (int i = 0; i < cont ; i++) {
			jugadorsRed[i] = llistajugadors[i];
		}
		System.out.println("El jugadors de la posicio:" + posicio);
		for(int i=0; i<jugadorsRed.length; i++){
			System.out.println(""+jugadorsRed[i]);
		}
		end = System.nanoTime();
		res = end - start;
		System.out.println("Temps en nanosegons: "+res);
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
	
	
	
	/**
	 * Mètode que carrega els jugadors des d'un fitxer
	 * @param ordenat boolea que indica si afegeixo de forma ordenada o no
	 * @throws IOException Fitxer no trobat
	 */
	public static void ImportarArxiu(Boolean ordenat) throws IOException {
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
				Jugador j = new Jugador(id, nom, dorsal, posicio);
							
				Equip aux = new Ordenat(equip);
				try{
					aux.afegirJugador(j);
				} catch (JugadorExisteix e) {
					System.out.println("El jugador ja existeix, per tant no s'afegeix");
				}
				try{
					llista.afegirEquip(aux, ordenat);
				}catch(EquipExisteix e){
					try {
						llista.getEquip(equip).afegirJugador(j);
					} catch (JugadorExisteix | EquipNoExisteix e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				linea = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fitxer no trobat");
		} catch (LlistaPlena e) {
			System.out.println("La llista ja esta plena, no es pot continuar afegint");
		} finally {
			br.close();
		}

	}

	public static void main(String[] args) {
		
		

		int opcio;
		int capacitat=0;
		boolean ordenat=false;


		System.out.println("\n Indica si vols una llista ordenada alfabeticament o no?:");
		System.out.println("\n\t1. Llista Ordenada");
		System.out.println("\n\t2. Llista No Ordenada");
		int n = llegirEnter();
		while((n!=1)&&(n!=2)){
			System.out.println("Has d'introduir un numero entre el 1 i el 2");
			System.out.println("\n Indica si vols una llista ordenada alfabeticament o no?:");
			System.out.println("\n\t1. Llista Ordenada");
			System.out.println("\n\t2. Llista No Ordenada");
			n = llegirEnter();
		}
		if(n==1) ordenat=true;
		else ordenat=false;
		
		
		System.out.println("\n A quina de les 3 implementacions vols tenir acces?:");
		System.out.println("\n\t1. Llista Dinamica");
		System.out.println("\n\t2. Llista Estatica");
		System.out.println("\n\t3. Llista amb element fantasma");
		System.out.println("\n\t4. Sortir");
		n = llegirEnter();

		while (n != 4) {
			while ((n < 0) || (n > 4)) {
				System.out
				.println("\n A quina de les 3 implementacions vols tenir acces?:");
				System.out.println("\n\t1. Llista Dinamica");
				System.out.println("\n\t2. Llista Estatica");
				System.out.println("\n\t3. Llista amb element fantasma");
				System.out.println("\n\t4. Sortir");
				n = llegirEnter();

			}
			
			if (n == 2) {
				System.out.println("Introdueix la capacitat maxima del conjunt");
				capacitat = llegirEnter();
			}


			if (n == 1) {
				llista = new Dinamica();
				System.out.println("S'ha creat correctament");

			}

			if (n == 2) {
				llista = new Estatica(capacitat);
				System.out.println("S'ha creat correctament");
			}

			if (n == 3) {
				llista = new Fantasma();
				System.out.println("S'ha creat correctament");

			}
			try {
				start = System.nanoTime();
				ImportarArxiu(ordenat);
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
					jugadorsEquip();
					break;
				case 2:
					consultarInformacio();
					break;
				case 3:
					ultimEquip();
					break;
				case 4:
					esborrarEquip();
					break;
				case 5:
					jugadorsPosicio();
					break;
				}

				Menu();
				opcio = llegirEnter();
			}
		}
		


		}
	}


