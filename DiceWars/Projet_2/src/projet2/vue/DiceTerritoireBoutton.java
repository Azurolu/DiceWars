package projet2.vue;

import projet2.entiees.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DiceTerritoireBoutton extends JButton {

    // Le joueur propriétaire du térritoire associé au bouton.
    private Joueur proprietaire;

    /**
     * Constructeur de la classe.
     *
     * @param proprietaire Le joueur propriétaire du térritoire associé au bouton.
     * @param text         Le nombre de dés.
     * @param color        La couleur du joueur.
     */
    public DiceTerritoireBoutton(Joueur proprietaire, String text, Color color) {
        this.proprietaire = proprietaire;
        this.setText(text);
        // Le changement du background ne fonctionne pas.
        // this.setBackground(color);
        this.setForeground(color);
    }

    /**
     * Permet d'obtenir le propriétaire du territoire.
     *
     * @return Le joueur propriétaire.
     */
    public Joueur getProprietaire() {
        return proprietaire;
    }

    @Override
    public String toString() {
        return String.format("nbDes: %s | color: %s", this.getText(), this.getBackground());
    }
}
