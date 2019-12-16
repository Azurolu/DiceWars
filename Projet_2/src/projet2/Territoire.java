package projet2;

public class Territoire {
	
	private final int ID;
	private int nbDes;
	private Joueur proprio;
	
	public Territoire(int id) {
		this.ID = id;
		this.nbDes = 0;
		this.proprio = null;
	}

	public int getNbDes() {
		return nbDes;
	}

	public void setNbDes(int nbDes) {
		this.nbDes = nbDes;
	}

	public Joueur getProprio() {
		return proprio;
	}

	public void setProprio(Joueur proprio) {
		this.proprio = proprio;
	}
	
	public int getId() {
		return this.ID;
	}
}
