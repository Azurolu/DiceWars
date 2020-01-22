package projet2.entiees;

public class Territoire {
	
	private final int ID;
	private int nbDes;
	private Joueur proprio;
	private boolean visite;
	
	public Territoire(int id) {
		this.ID = id;
		this.nbDes = 0;
		this.proprio = null;
		this.visite = false;
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
	
	public boolean getVisite() {
		return this.visite;
	}
	
	public void setVisite(boolean b) {
		this.visite = b;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Territoire other = (Territoire) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	public String toString() {
		if(this.proprio == null) {
			return "Joueur : aucun;" + "Dice : " + Integer.toString(this.nbDes);
		}
		else
			return "Joueur : " + Integer.toString(proprio.getId()) + ";" + "Dice : " + Integer.toString(this.nbDes);
	}
}
