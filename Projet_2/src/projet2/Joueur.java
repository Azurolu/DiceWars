package projet2;

import java.util.Scanner;

public class Joueur {
	
	private final int ID;
	private static int baseId = 0;
	
	public Joueur() {
		this.ID = baseId;
		baseId += 1;
	}
	
	public int getId() {
		return this.ID;
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
