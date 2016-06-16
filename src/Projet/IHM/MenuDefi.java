package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Projet.Controleur;

public class MenuDefi extends JPanel implements ActionListener {
	
	private JPanel listeDefisDebutant;
	private JPanel listeDefisIntermediaire;
	private JPanel listeDefisAvance;
	private JPanel listeDefisExpert;
	private Controleur ctrl;
	private Fenetre fenetre;
	
	public MenuDefi(Controleur ctrl, Fenetre fenetre)
	{
		this.fenetre = fenetre;
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(4,1));
		
		JPanel tmp = new JPanel(new BorderLayout());
		int i = this.ctrl.getNiveauDebutant().size()/5;
		this.listeDefisDebutant = new JPanel(new GridLayout(0,i,10,10));
		listeDefisDebutant.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Défis débutant",0,0,new Font("Dialog", 1, 12),Color.BLACK));
		for ( int j = 0; j < this.ctrl.getNiveauDebutant().size(); j++) {
			this.listeDefisDebutant.add(new JButton(this.ctrl.getNiveauDebutant().get(j).getNumNiveau()+""));
		}
		tmp.add(this.listeDefisDebutant);
		this.add(tmp);
		
		tmp = new JPanel(new BorderLayout());
		i = this.ctrl.getNiveauIntermediaire().size()/5;
		this.listeDefisIntermediaire = new JPanel(new GridLayout(0,i,10,10));
		listeDefisIntermediaire.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Défis intermediaires",0,0,new Font("Dialog", 1, 12),Color.BLACK));
		for ( int j = 0; j < this.ctrl.getNiveauIntermediaire().size(); j++) {
			this.listeDefisIntermediaire.add(new JButton(this.ctrl.getNiveauDebutant().get(j).getNumNiveau()+""));
		}
		tmp.add(this.listeDefisIntermediaire);
		this.add(tmp);
		
		tmp = new JPanel(new BorderLayout());
		i = this.ctrl.getNiveauAvance().size()/5;
		System.out.println(i+"");
		this.listeDefisAvance = new JPanel(new GridLayout(0,i,10,10));
		listeDefisAvance.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Défis avancés",0,0,new Font("Dialog", 1, 12),Color.BLACK));
		for ( int j = 0; j < this.ctrl.getNiveauAvance().size(); j++) {
			this.listeDefisAvance.add(new JButton(this.ctrl.getNiveauDebutant().get(j).getNumNiveau()+""));
		}
		tmp.add(this.listeDefisAvance);
		this.add(tmp);
		
		tmp = new JPanel(new BorderLayout());
		i = this.ctrl.getNiveauExpert().size()/5;
		this.listeDefisExpert = new JPanel(new GridLayout(0,i,10,10));
		listeDefisExpert.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Défis expert",0,0,new Font("Dialog", 1, 12),Color.BLACK));
		for ( int j = 0; j < this.ctrl.getNiveauExpert().size(); j++) {
			this.listeDefisExpert.add(new JButton(this.ctrl.getNiveauDebutant().get(j).getNumNiveau()+""));
		}
		tmp.add(this.listeDefisExpert);
		this.add(tmp);
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
