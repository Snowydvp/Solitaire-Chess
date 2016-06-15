package Projet.IHM;

import Projet.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Créé par BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine le 14/06/2016.
 */
public class Menu extends JPanel implements ActionListener
{
    private Fenetre fenetre;
    private Controleur ctrl;

    private JButton continuer, nouvellePartie, choisirNiveau, creerDefi, defisPersonnalises, aide, scores, quitter;

    private boolean peutContinuer;

    public Menu(Controleur ctrl, Fenetre fenetre)
    {
        this.ctrl = ctrl;
        this.fenetre = fenetre;
        this.fenetre.setTitle("Solitaire Chess - Menu");

        this.setBorder(BorderFactory.createEmptyBorder(25, 75, 25, 75));
        ArrayList<JButton> alButton = new ArrayList<JButton>();

        this.continuer = new JButton("Continuer");
        this.continuer.addActionListener(this);
        if(!peutContinuer) {
            alButton.add(continuer);
        }

        this.nouvellePartie = new JButton("Nouvelle partie");
        this.nouvellePartie.addActionListener(this);
        alButton.add(nouvellePartie);

        this.choisirNiveau = new JButton("Choisir un défi");
        this.choisirNiveau.addActionListener(this);
        if(peutContinuer) {
            alButton.add(choisirNiveau);
        }


        this.creerDefi = new JButton("Créer un défi");
        this.creerDefi.addActionListener(this);
        alButton.add(creerDefi);

        this.defisPersonnalises = new JButton("Défis personnalisés");
        this.defisPersonnalises.addActionListener(this);
        alButton.add(defisPersonnalises);

        this.aide = new JButton("Aide");
        this.aide.addActionListener(this);
        alButton.add(aide);

        this.scores = new JButton("Scores");
        this.scores.addActionListener(this);
        alButton.add(scores);

        this.quitter = new JButton("Quitter");
        this.quitter.addActionListener(this);
        alButton.add(quitter);

        this.setLayout(new GridLayout(alButton.size() + 2, 1, 5, 5));

        JLabel icone = new JLabel();
        icone.setHorizontalAlignment(JLabel.CENTER);
        icone.setVerticalAlignment(JLabel.BOTTOM);
        icone.setIcon(new ImageIcon("Images/crown.png"));
        this.add(icone);

        JLabel question = new JLabel("Que voulez-vous faire ?");
        question.setHorizontalAlignment(JLabel.CENTER);
        this.add(question);

        for(JButton bouton : alButton)
            this.add(bouton);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.quitter)
            System.exit(0);

        if (e.getSource() == this.continuer)
        {
            this.setVisible(false);
            Jeu j = new Jeu(this.ctrl, this.fenetre);
            this.fenetre.setJeu(j);
            this.fenetre.add(j,BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Jeu");
            this.fenetre.pack();
        }
        
        if (e.getSource() == this.creerDefi)
        {
            this.setVisible(false);
            Editeur ed = new Editeur(this.ctrl, this.fenetre);
            this.fenetre.setEditeur(ed);
            this.fenetre.add(ed,BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Editeur");
            this.fenetre.pack();
        }

    }
}