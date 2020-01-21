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
		do{
			String input = sc.nextLine();
			System.out.println("Entrez 'q' pour mettre fin au tour et 'a' pour attaquer.");
			switch(input){
				case "q":
					System.out.println("Fin du tour");
                    			end = true;
                    			break;
				case "a":
					initiateAttack();
					break;
				default:
					System.out.println("Veuillez recommencer.");
					break;
			}
		}while(!check);
		sc.close();		
	}
	
	private static void initiateAttack(){
				j.getCarte().toString();
				System.out.println("Entrez l'attaque a effectuer sous le format : idAttaquant idAttaqué");
				Scanner att = new Scanner(System.in);
				String[] reponse = att.nextLine().split(" ");
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
				j.attaquer(attaquant.getId(), attaque.getId());
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
