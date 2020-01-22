package projet2;

public abstract class Brain {

	Joueur player;
	
	public Brain (Joueur j) {
		this.player = j;
	}
	
	public boolean action(Carte map, Jeu j, int x) {
		return false;
	}
}
