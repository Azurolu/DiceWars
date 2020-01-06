package projet2;

import java.util.Scanner;

public class Joueur {
	
	private final int ID;
	private static int baseId = 0;
	private int nbDes;
	private int nbTerritoire;
	
	public Joueur() {
		this.ID = baseId;
		baseId += 1;
		this.nbTerritoire = 0;
	}
	
	public int getId() {
		return this.ID;
	}
	
	public void setNbDes(int nb) {
		this.nbDes = nb;
	}
	
	public int getNbDes() {
		return this.nbDes;
	}
	
	public void setNbTerritoire(int nb) {
		this.nbTerritoire = nb;
	}
	
	public int getNbTerritoire() {
		return this.nbTerritoire;
	}
	
	public void jouer() {
		Scanner sc = new Scanner(System.in);
		
		boolean check = false;
		while(!check) {
			if(sc.next().equals("q")) {
				check = true;
			}
		}
		//endTurn;		
	}
	
	public boolean equals(Joueur j) {
		return this.ID == j.ID;
	}

}
