package projet2;

import java.nio.file.Paths;

public class Partie {
	
	public static void main(String[] args) {
		Joueur[] joueurs = new Joueur[Integer.parseInt(args[0])];
		for(Joueur j : joueurs) {
			j = new Joueur();
		}
		Carte carte = new Carte(Paths.get("carte.csv"));
	}

}
