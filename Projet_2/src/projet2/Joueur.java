package projet2;

import java.util.Scanner;

public class Joueur {
	
	private final int ID;
	private static int baseId = 0;
	private int nbDes;
	private int nbTerritoire;
	
	public Joueur() {
		this.ID = baseId;
		baseId += 1;
		this.nbTerritoire = 0;
	}
	
	public int getId() {
		return this.ID;
	}
	
	public void setNbDes(int nb) {
		this.nbDes = nb;
	}
	
	public int getNbDes() {
		return this.nbDes;
	}
	
	public void setNbTerritoire(int nb) {
		this.nbTerritoire = nb;
	}
	
	public int getNbTerritoire() {
		return this.nbTerritoire;
	}
	
	public void jouer(Jeu j) throws AttaqueTerritoireJoueur, ErreurAttaqueNonVoisin, ErreurTerritoireDe, ErreurJoueurPossesseur {
		Scanner sc = new Scanner(System.in);
		boolean check = false;
		while(!check) {
			if(sc.next().equals("q")) {
				check = true;
			}
			else {
				System.out.println("Indiquer l'attaque a orchestré sous le format : idAttaquant idAttaqué");
				String[] reponse = sc.nextLine().split(" ");
				Territoire attaquant = j.getCarte().getTerritoire(Integer.parseInt(reponse[0]));
				Territoire attaque = j.getCarte().getTerritoire(Integer.parseInt(reponse[1]));
				if(attaquant.getProprio() == attaque.getProprio()) {
					throw new AttaqueTerritoireJoueur();
				}
				else if(Joueur.appartientVoisins(attaquant, j.getCarte().getVoisins(attaquant))) {
					throw new ErreurAttaqueNonVoisin();
				}
				else if(attaquant.getNbDes() == 1) {
					throw new ErreurTerritoireDe();
				}
				else if(attaquant.getProprio() != this) {
					throw new ErreurJoueurPossesseur();
				}
				check = j.attaquer(attaquant.getId(), attaque.getId());
				System.out.println("Tappez q si vous souhaitez arrêter de jouer");
			}
		}
		//endTurn;		
	}
	
	private static boolean appartientVoisins(Territoire attaque, Territoire[] voisins) {
		for(Territoire t : voisins) {
			if(t.equals(attaque))
				return true;
		}
		return false;
	}
	
	public boolean equals(Joueur j) {
		return this.ID == j.ID;
	}

}
