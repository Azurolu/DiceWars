package projet2;

public class ErreurJoueurPossesseur extends Exception {
	
	public ErreurJoueurPossesseur() {
		super("Le territoire attaquant n'appartient pas au joueur");
	}

}
