package projet2;

import java.nio.file.Paths;
public class Partie {
	
	public static void main(String[] args) {
		Joueur[] joueurs = new Joueur[Integer.parseInt(args[0])];
		for(int i = 0; i < joueurs.length; i++) {
			joueurs[i] = new Joueur();
		}
		Carte carte = new Carte(Paths.get("carte.csv"));
		Jeu j = new Jeu(carte.getNbTerritoires(), joueurs, carte);
		int tour = 0;
		while(!carte.victoire()) {
			System.out.println("C'est à joueur " + joueurs[tour%(joueurs.length)].getId() + " de jouer");
			joueurs[tour%(joueurs.length)].jouer();
			System.out.println(carte);
			tour++;
		}
	}

}
