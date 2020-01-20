package projet2.vue;

import projet2.controlleurs.MainFrameController;
import projet2.entiees.Joueur;
import projet2.entiees.Territoire;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainFrame extends JFrame {

    // Les différents panels.
    private JPanel centerPanel;
    private JPanel downPanel;

    // Le controlleur de la classe.
    private MainFrameController controller;

    /**
     * Constructeur de la classe.
     *
     * @param controller Le controlleur de la classe.
     */
    public MainFrame(MainFrameController controller) {
        this.controller = controller;

        this.setTitle("Dice War");
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.loadComponents();
    }

    /**
     * Chargement des composants de la frame.
     */
    private void loadComponents() {
        this.loadCenterPanel();
        this.loadDownPanel();
    }

    /**
     * Chargement de la partie centrale.
     */
    private void loadCenterPanel() {
        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new GridBagLayout());

        this.creerCarte();

        this.add(this.centerPanel, BorderLayout.CENTER);
    }

    /**
     * Chargement de la partie basse.
     */
    private void loadDownPanel() {
        this.downPanel = new JPanel();
        this.downPanel.setLayout(new FlowLayout());

        JButton btnFinDeTour = new JButton("Fin du tour");
        btnFinDeTour.addActionListener(event -> {
            controller.finDuTour();
        });
        this.downPanel.add(btnFinDeTour);

        this.add(this.downPanel, BorderLayout.PAGE_END);
    }

    /**
     * Création de la matrice de bouttons.
     */
    private void creerCarte() {
        Territoire[][] tabCarte = this.controller.getCarteDuJeu().getCarte();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        for (int i = 0; i < tabCarte.length; i++) {
            for (int j = 0; j < tabCarte[i].length; j++) {
                if (tabCarte[i][j] != null) {
                    String nbDes = String.valueOf(tabCarte[i][j].getNbDes());
                    Joueur proprio = tabCarte[i][j].getProprio();
                    DiceTerritoireBoutton boutton = new DiceTerritoireBoutton(proprio, nbDes, this.controller.getMapJoueurCouleur().get(proprio));
                    boutton.addActionListener(event -> {
                        controller.action(boutton.getProprietaire());
                    });
                    this.centerPanel.add(boutton, gridBagConstraints);
                }
                gridBagConstraints.gridx++;
            }
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy++;
        }
    }

    /**
     * Affichage de la frame.
     */
    public void showFrame() {
        this.setVisible(true);
    }
}
