package projet2;

import java.nio.file.Paths;
public class Partie {
	
	public static void main(String[] args) {
		Joueur[] joueurs = new Joueur[Integer.parseInt(args[0])];
		for(int i = 0; i < joueurs.length; i++) {
			joueurs[i] = new Joueur();
		}
		Carte carte = new Carte(Paths.get("carte.csv"));
		int limiteTerritoire = carte.getNbTerritoires();
		while(limiteTerritoire%(joueurs.length) != 0) {
			limiteTerritoire--;
		}
		Jeu j = new Jeu(limiteTerritoire, joueurs, carte);
		int tour = 0;
		while(!carte.victoire()) {
			System.out.println("C'est à joueur " + joueurs[tour%(joueurs.length)].getId() + " de jouer");
			int nbDesDebut = joueurs[tour%(joueurs.length)].getNbDes();
			try {
				joueurs[tour%(joueurs.length)].jouer(j);
			} catch (AttaqueTerritoireJoueur | ErreurAttaqueNonVoisin | ErreurTerritoireDe | ErreurJoueurPossesseur e) {
				e.printStackTrace();
			}
			j.finTour(nbDesDebut, joueurs[tour%(joueurs.length)].getNbDes()); // on recalcule après que les dés aient changés
			System.out.println(carte);
			tour++;
		}
	}

}
