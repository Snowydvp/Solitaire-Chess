package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Projet.Controleur;

public class Menu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton continuer, nouvellePartie, choisirNiveau, creerNiveau,
	                aide, scores, quitter;
	private Fenetre fenetre;
	
	public Menu(Controleur ctrl, Fenetre fenetre)
	{
		this.ctrl = ctrl;
		this.fenetre = fenetre;
		
		this.setLayout(new GridLayout(8, 1));
		
		continuer = new JButton("Continuer");
		this.continuer.addActionListener(this);
		this.add(continuer);
		
		nouvellePartie = new JButton("Nouvelle partie");
		this.nouvellePartie.addActionListener(this);
		this.add(nouvellePartie);

		choisirNiveau = new JButton("Choisir un niveau");
		this.choisirNiveau.addActionListener(this);
		this.add(choisirNiveau);
		
		aide = new JButton("Aide");
		this.aide.addActionListener(this);
		this.add(aide);
		
		scores = new JButton("Scores");
		this.scores.addActionListener(this);
		this.add(scores);
		

		creerNiveau = new JButton("Créer un niveau");
		this.creerNiveau.addActionListener(this);
		this.add(creerNiveau);
		
		quitter = new JButton("Quitter");
		this.quitter.addActionListener(this);
		this.add(quitter);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == continuer)
		{
			this.setVisible(false);
			Jeu j = new Jeu(this.ctrl, this.fenetre);
			this.fenetre.setJeu(j);
			this.fenetre.add(j,BorderLayout.CENTER);
			this.fenetre.pack();
		}
		
	}
}
