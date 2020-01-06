package projet2;

import java.util.Random;

public class Jeu {
	
	int nbDesDepart;
	
	public Jeu(int limiteDes, Joueur[] Joueurs, Carte carte) {
		Random rnd = new Random();
		this.nbDesDepart = (rnd.nextInt(limiteDes*8 - carte.getNbTerritoires() + 1) + carte.getNbTerritoires()); //Evite le 0 dés et permet au moins un dé sur chaque territoire
		while(nbDesDepart%(Joueurs.length) != 0) {
			this.nbDesDepart = (rnd.nextInt(limiteDes*8 - carte.getNbTerritoires() + 1) + carte.getNbTerritoires()); //Il faut le même nombre de dés par joueur
		}
		System.out.println(nbDesDepart);
		for(Joueur j : Joueurs) {
			j.setNbDes(nbDesDepart/Joueurs.length);
		}
		int i = 0;
		while(!carte.attributionJoueurs()) {
			Territoire t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
			while(t == null || t.getProprio() != null) {
				t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
			}
			t.setProprio(Joueurs[i%Joueurs.length]);
			i++;
		}
		for(Territoire[] tcrochets : carte.getCarte()) {
			for(Territoire t : tcrochets) {
				if(t != null) {
					t.getProprio().setNbTerritoire(t.getProprio().getNbTerritoire()+1);
				}
			}
		}
		while(!carte.attributionDes()) {
			Territoire t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
				if(t != null && t.getNbDes() == 0) {
					int Des = rnd.nextInt(8)+1;
					if(Des > t.getProprio().getNbDes()) {
						Des = t.getProprio().getNbDes();
					}
					while(t.getProprio().getNbDes() - Des < t.getProprio().getNbTerritoire() - 1) {
						Des--;
					}
					t.getProprio().setNbDes(t.getProprio().getNbDes() - Des);
					t.setNbDes(Des);
					t.getProprio().setNbTerritoire(t.getProprio().getNbTerritoire() - 1);
				}
		}
	}
}
