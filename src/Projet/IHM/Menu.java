package Projet.IHM;

import Projet.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.tools.Tool;

/**
 * Créé par BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine le 14/06/2016.
 */
public class Menu extends JPanel implements ActionListener
{
    private Fenetre fenetre;
    private Controleur ctrl;

    private JButton continuer, nouvellePartie, choisirNiveau, creerDefi, defisPersonnalises, aide, scores, quitter;

    public Menu(Controleur ctrl, Fenetre fenetre)
    {
        this.ctrl = ctrl;
        this.fenetre = fenetre;
        this.fenetre.setTitle("Solitaire Chess - Menu");

        this.setLayout(new BorderLayout());

        JPanel espaceLogo = new JPanel(new BorderLayout());
        espaceLogo.add(new JLabel(new ImageIcon("Images/logo.png")));
        espaceLogo.add(new JLabel("Que voulez-vous faire ?", JLabel.CENTER), BorderLayout.SOUTH);
        this.add(espaceLogo);

        JPanel espaceBoutons = new JPanel(new BorderLayout());
        JPanel espaceBoutonsGauche = new JPanel(new GridLayout(3, 1));
        JPanel espaceBoutonsGaucheHaut = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel espaceBoutonsGaucheBas  = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel espaceBoutonsDroite = new JPanel(new GridLayout(3, 1));
        JPanel espaceBoutonsDroiteHaut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel espaceBoutonsDroiteMilieu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel espaceBoutonsDroiteBas = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        espaceBoutons.add(espaceBoutonsGauche, BorderLayout.WEST);
        espaceBoutonsGauche.add(espaceBoutonsGaucheHaut);
        espaceBoutonsGauche.add(espaceBoutonsGaucheBas);
        espaceBoutonsGauche.add(new JPanel());
        espaceBoutons.add(espaceBoutonsDroite, BorderLayout.EAST);
        espaceBoutonsDroite.add(espaceBoutonsDroiteHaut);
        espaceBoutonsDroite.add(espaceBoutonsDroiteMilieu);
        espaceBoutonsDroite.add(espaceBoutonsDroiteBas);

        this.add(espaceBoutons, BorderLayout.SOUTH);

        this.nouvellePartie = new JButton("Nouvelle partie");
        this.nouvellePartie.addActionListener(this);
        espaceBoutonsGaucheHaut.add(this.nouvellePartie);

        this.continuer = new JButton("Continuer");
        this.continuer.addActionListener(this);
        espaceBoutonsGaucheHaut.add(this.continuer);

        this.choisirNiveau = new JButton("Choisir défi");
        this.choisirNiveau.addActionListener(this);
        espaceBoutonsGaucheHaut.add(this.choisirNiveau);


        this.defisPersonnalises = new JButton("Défis personnalisés");
        this.defisPersonnalises.addActionListener(this);
        espaceBoutonsGaucheBas.add(this.defisPersonnalises);

        this.creerDefi = new JButton("Créer un défi");
        this.creerDefi.addActionListener(this);
        espaceBoutonsGaucheBas.add(this.creerDefi);


        this.scores = new JButton("Scores");
        this.scores.setPreferredSize(new Dimension(75, 26));
        this.scores.addActionListener(this);
        espaceBoutonsDroiteHaut.add(this.scores);

        this.aide = new JButton("Aide");
        this.aide.setPreferredSize(new Dimension(75, 26));
        this.aide.addActionListener(this);
        espaceBoutonsDroiteMilieu.add(this.aide);

        this.quitter = new JButton("Quitter");
        this.quitter.setPreferredSize(new Dimension(75, 26));
        this.quitter.addActionListener(this);
        espaceBoutonsDroiteBas.add(this.quitter);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.quitter)
            System.exit(0);

        if (e.getSource() == this.continuer) 
        {
            this.setVisible(false);
            this.ctrl.chargerPartie();
            Jeu j = new Jeu(this.ctrl, this.fenetre);
            this.fenetre.setJeu(j);
            this.fenetre.add(j, BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Jeu");
            this.fenetre.pack();
            this.fenetre.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.fenetre.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.fenetre.getHeight() / 2));
        }
        else if (e.getSource() == this.creerDefi) 
        {
            this.setVisible(false);
            Editeur ed = new Editeur(this.ctrl, this.fenetre);
            this.fenetre.setEditeur(ed);
            this.fenetre.add(ed, BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Editeur");
            this.fenetre.pack();
            this.fenetre.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.fenetre.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.fenetre.getHeight() / 2));
        }
        else if (e.getSource() == this.nouvellePartie)
        {
        	this.setVisible(false);
        	this.ctrl.creerPartie();
        	Jeu j = new Jeu(this.ctrl, this.fenetre);
        	this.fenetre.setJeu(j);
            this.fenetre.add(j, BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Jeu");
            this.fenetre.pack();
            this.fenetre.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.fenetre.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.fenetre.getHeight() / 2));
       
        }
        else if (e.getSource() == this.choisirNiveau)
        {
        	this.setVisible(false);
        	MenuDefi mD = new MenuDefi(this.ctrl, this.fenetre);
        	this.fenetre.setMenuDefi(mD);
            this.fenetre.add(mD, BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Liste Défis");
            this.fenetre.pack();
            this.fenetre.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.fenetre.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.fenetre.getHeight() / 2));
       
        }
    }
}