package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Projet.Controleur;
import Projet.Metier.Niveau;

public class MenuDefi extends JPanel implements ActionListener {
	
	private JPanel debutant;
	private JPanel intermediaire;
	private JPanel avance;
	private JPanel expert;
	private Controleur ctrl;
	private Fenetre fenetre;
	
	public MenuDefi(Controleur ctrl, Fenetre fenetre)
	{
		this.fenetre = fenetre;
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(4,1));
		
		debutant = this.initialisePanel(this.ctrl.getNiveauDebutant(), "Défis débutant");
		this.add(debutant);
		
		intermediaire = this.initialisePanel(this.ctrl.getNiveauIntermediaire(), "Défis intermediaire");
		this.add(intermediaire);
		
		avance = this.initialisePanel(this.ctrl.getNiveauAvance(), "Défis avancé");
		this.add(avance);
		
		expert = this.initialisePanel(this.ctrl.getNiveauExpert(), "Défis expert");
		this.add(expert);
	}
	
	public JPanel initialisePanel(ArrayList<Niveau> alNiveau, String s) {
		int i = alNiveau.size()/5;
		JPanel tmp = new JPanel(new GridLayout(i,0,10,10));
		tmp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				s,0,0,new Font("Dialog", 1, 12),Color.BLACK));
		for ( int j = 0; j < alNiveau.size(); j++) {
			JButton buttonTmp = new JButton(alNiveau.get(j).getNumNiveau()+"");
			if ( this.ctrl.getPartie().peutJouerNiveau(0,alNiveau.get(j).getNumNiveau()-1))
				buttonTmp.setBackground(Color.GREEN);
			else
				buttonTmp.setBackground(Color.RED);
			tmp.add(buttonTmp);
		}
		
		return tmp;
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
