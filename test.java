import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Carte {
	
	private Territoire[][] carte;
	
	public Carte(Path path){
		ArrayList<String> lignes = new ArrayList<String>();
		try {
			Scanner lecture = new Scanner(path);
			while(lecture.hasNextLine()) {
				lignes.add(lecture.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.carte = new Territoire[lignes.size()][lignes.get(0).length()];
		int i = 0; 
		for(String s : lignes) {
			int j = 0;
			for(String sbis : s.split(";")) {
				if(sbis != null) { //Du coup j'ai considéré que y avait soit quelque chose, soit une case vide, si vide, pas de territoire
					carte[i][j] = new Territoire(i*lignes.get(0).length() + j);
				}
				j++;
			}
			i++;
		}
		lignes.clear();
	}
}

//////////////////////////////////////////////////////

public class Territoire {
	
	private int ID;
	private int nbDes;
	private Joueur proprio;
	
	public Territoire(int id) {
		this.ID = id;
		this.nbDes = 0;
		this.proprio = null;
	}

}

/////////////////////////////////

import java.util.Scanner;

public class Joueur {
	
	private static int ID = 0;
	
	public Joueur() {
		this.ID += 1;
	}
	
	public void jouer() {
		Scanner sc = new Scanner(System.in);
		
		//if(sc.next().equals("q"))
			
	}

}
