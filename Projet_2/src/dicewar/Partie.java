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
		
		//Il faut entrer la difficulte de l'IA et le joueur que remplace l'IA
		boolean cont = true;
		int difficulte = 0;
		int x = 0;
		Brain bruh = new DumbBrain(joueurs[0]); //Remplacer 0 par l'id du joueur IA selon la partie
		if(difficulte == 1) {
			bruh = new PlayerBrain(joueurs[0]); //Remplacer 0 par l'id du joueur IA selon la partie
		}
		if(difficulte == 2) {
			bruh = new LevelBrain(joueurs[0]); //Remplacer 0 par l'id du joueur IA selon la partie
			x = 20;
		}
		if(difficulte == 3) {
			bruh = new LevelBrain(joueurs[0]); //Remplacer 0 par l'id du joueur IA selon la partie
			x = 40;
		}
		if(difficulte == 4) {
			bruh = new LevelBrain(joueurs[0]); //Remplacer 0 par l'id du joueur IA selon la partie
			x = 60;
		}
		if(difficulte == 5) {
			bruh = new LevelBrain(joueurs[0]); //Remplacer 0 par l'id du joueur IA selon la partie
			x = 80;
		}
		if(difficulte == 6) {
			bruh = new BigBrain(joueurs[0]); //Remplacer 0 par l'id du joueur IA selon la partie
		}
		do {
			cont = bruh.action(carte, j, x);
		}while(cont== true);
		
		int tour = 0;
		while(!carte.victoire()) {
			System.out.println("C'est au joueur " + joueurs[tour%(joueurs.length)].getId() + " de jouer");
			int nbDesDebut = joueurs[tour%(joueurs.length)].getNbDes();
			try {
				joueurs[tour%(joueurs.length)].jouer(j);
			} catch (AttaqueTerritoireJoueur | ErreurAttaqueNonVoisin | ErreurTerritoireDe | ErreurJoueurPossesseur e) {
				e.printStackTrace();
			}
			j.finTour(nbDesDebut, joueurs[tour%(joueurs.length)].getNbDes()); // on recalcule apres le changement des des
			System.out.println(carte);
			tour++;
		}
	}
}
