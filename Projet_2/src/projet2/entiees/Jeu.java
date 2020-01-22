package projet2.entiees;

import java.util.Random;

public class Jeu {
	
	private int nbDesDepart;
	private Carte carte;
	private Joueur[] joueurs;
	
	public Jeu(int limiteDes, Joueur[] joueur, Carte cart) {
		Random rnd = new Random();
		this.carte = cart;
		this.joueurs = joueur;
		this.nbDesDepart = (rnd.nextInt(limiteDes*8 - carte.getNbTerritoires() + 1) + carte.getNbTerritoires()); //Evite le 0 dés et permet au moins un dé sur chaque territoire
		while(nbDesDepart%(joueurs.length) != 0) {
			this.nbDesDepart = (rnd.nextInt(limiteDes*8 - carte.getNbTerritoires() + 1) + carte.getNbTerritoires()); //Il faut le même nombre de dés par joueur
		}
		System.out.println(nbDesDepart);
		for(Joueur j : joueurs) {
			j.setNbDes(nbDesDepart/joueurs.length);
		}
		int i = 0;
		while(!carte.attributionJoueurs()) {
			Territoire t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
			while(t == null || t.getProprio() != null) {
				t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
			}
			t.setProprio(joueurs[i%joueurs.length]);
			i++;
		}
		carte.attributiontTerritoires();
		while(!carte.attributionDes()) {
			Territoire t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
				if(t != null && t.getNbDes() == 0) {
					int Des = rnd.nextInt(8)+1;
					if(t.getProprio().getNbTerritoire() == 1) {
						Des = t.getProprio().getNbDes();
					}
					if(t.getProprio().getNbDes() >= ((t.getProprio().getNbTerritoire()-1)*8)) {
						Des = 8;
					}
					if(Des > t.getProprio().getNbDes()) {
						Des = t.getProprio().getNbDes();
					}
					while(Des > t.getProprio().getNbDes() - (t.getProprio().getNbTerritoire()-1)) {
						Des--;
					}
					t.getProprio().setNbDes(t.getProprio().getNbDes() - Des);
					t.setNbDes(Des);
					t.getProprio().setNbTerritoire(t.getProprio().getNbTerritoire() - 1);
				}
		}
		for(Joueur j : this.joueurs) {
			j.setNbDes(this.nbDesDepart/this.joueurs.length);
		}
		this.carte.attributiontTerritoires();//On réinitialise nos dés et nos territoires
	}
	
	public void miseAJour(Territoire gagnant, Territoire perdant, Territoire attaque) {
		if(gagnant == attaque) { //Si le territoire attaqué gagne, rien ne change pour lui
			perdant.getProprio().setNbDes(perdant.getProprio().getNbDes()-(perdant.getNbDes()-1));
			gagnant.getProprio().setNbDes(perdant.getProprio().getNbDes()+(perdant.getNbDes()-1));
			perdant.setNbDes(1);
			gagnant.setNbDes(gagnant.getNbDes() + perdant.getProprio().getNbTerritoire()-(perdant.getNbDes()-1));
		}
		else if(perdant == attaque) { //Sinon tout change
			gagnant.setNbDes(1);
			attaque.setNbDes(attaque.getNbDes() + (perdant.getNbDes()-1));
			attaque.setProprio(gagnant.getProprio());
			gagnant.getProprio().setNbTerritoire(gagnant.getProprio().getNbTerritoire()+1);
			perdant.getProprio().setNbTerritoire(perdant.getProprio().getNbTerritoire()-1);
		}
		if(carte.victoire())
			System.out.println("Victoire du joueur : " + carte.getCarte()[0][0].getProprio());
	}
	
	public void finTour(int desDebut, int desFin) {
		int desRapportes = desFin - desDebut;
		System.out.println("Nombre de dés rapportés : " + desRapportes);
		Random rnd = new Random();
		for (Joueur j : joueurs) {
			while(!carte.reattributionDes(j)) {
				Territoire t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
					if(t != null && t.getVisite() == false) {
						int Des = rnd.nextInt(8)+1;
						if(t.getProprio().getNbTerritoire() == 1) {
							Des = t.getProprio().getNbDes();
						}
						if(t.getProprio().getNbDes() >= ((t.getProprio().getNbTerritoire()-1)*8)) {
							Des = 8;
						}
						if(Des > t.getProprio().getNbDes()) {
							Des = t.getProprio().getNbDes();
						}
						while(Des > t.getProprio().getNbDes() - (t.getProprio().getNbTerritoire()-1)) {
							Des--;
						}
						t.getProprio().setNbDes(t.getProprio().getNbDes() - Des);
						t.setNbDes(Des);
						t.getProprio().setNbTerritoire(t.getProprio().getNbTerritoire() - 1);
					}
			}
		}
		
	}
	
	private int aleatoire() {
		return new Random().nextInt(6)+1;
	}
	
	private static int sommeTableau(int[] t) {
		int somme = 0;
		for(int i : t) {
			somme += i;
		}
		return somme;
	}
	
	public boolean attaquer(int territoireAttaquant, int territoireAttaque) {
		boolean gagner = false;
		int[] attaquant = new int[carte.getTerritoire(territoireAttaquant).getNbDes()-1];
		int[] attaque = new int[carte.getTerritoire(territoireAttaque).getNbDes()];
		for(int i = 0; i < attaquant.length; i++) {
			attaquant[i] = this.aleatoire();
		}
		for(int i = 0; i < attaque.length; i++) {
			attaque[i] = this.aleatoire();
		}
		if(Jeu.sommeTableau(attaquant) > Jeu.sommeTableau(attaque)) {
			this.miseAJour(carte.getTerritoire(territoireAttaquant), carte.getTerritoire(territoireAttaque), carte.getTerritoire(territoireAttaque));
			gagner = true;
		}
		else {
			this.miseAJour(carte.getTerritoire(territoireAttaque), carte.getTerritoire(territoireAttaquant), carte.getTerritoire(territoireAttaque));
		}
		return gagner;
	}
	
	public Carte getCarte() {
		return this.carte;
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}
}
