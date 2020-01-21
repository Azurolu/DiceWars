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
				j.getCarte().toString();
				System.out.println("Indiquer l'attaque a orchestré sous le format : idAttaquant idAttaqué");
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
				check = j.attaquer(attaquant.getId(), attaque.getId());
				System.out.println("Tappez q si vous souhaitez arrêter de jouer sinon tapez n'importe quoi");
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

	private void attaquer(Territoire attaquant, Territoire attaque) {

        	int attaqueTotale = calculTotal(attaquant.getNbDes());
        	int defenseTotale = calculTotal(attaque.getNbDes());

        	System.out.println("Degats infliges : " + attaqueTotale);
        	System.out.println("Degats subits : " + defenseTotale);


        	if(attaqueTotale > defenseTotale){
            		System.out.println(attaquant.getProprio().getListeTerritoire());
            		System.out.println(attaque.getProprio().getListeTerritoire());
            		gagner(attaquant, attaque);
            		System.out.println(attaquant.getProprio().getListeTerritoire());
            		System.out.println(attaque.getProprio().getListeTerritoire());
            		return attaquant;
        	}else{
        	    	perdre(attaquant, attaque);
        	}
    	}
    
	private int calculTotal(int nbdes) {
        	int total = 0;

       	 	for(int i = 0; i < nbdes; i++) {
            		int tmp = new Random().nextInt(6) + 1;
            		total += tmp;
        	}
        	return total;
    	}

	private void gagner(){
    		//Le territoire va dans la liste de territoires du gagnant
    		//Incremente le compteur de des du tour
	}

	private void perdre(){
    		//Set le nombre de des du territoire perdant a 1
	}
	
}
