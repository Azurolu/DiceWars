package projet2.entiees;

import projet2.controlleurs.MainFrameController;
import projet2.vue.MainFrame;

import java.nio.file.Paths;

public class Partie {

    public static void main(String[] args) {

        boolean graphique = false;

        Joueur[] joueurs = new Joueur[Integer.parseInt(args[0])];
        for (int i = 0; i < joueurs.length; i++) {
            joueurs[i] = new Joueur();
        }
        Carte carte = new Carte(Paths.get("carte.csv"));
        System.out.println(carte);

        Jeu jeu = new Jeu(carte.getNbTerritoires(), joueurs, carte);

        // Si le booléen graphique est à true on utilise la partie graphique
        if(graphique) {
            MainFrameController mainFrameController = new MainFrameController(jeu);
            MainFrame mainFrame = new MainFrame(mainFrameController);
            mainFrame.showFrame();
        }
    }

}
