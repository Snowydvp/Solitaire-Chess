package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Projet.Controleur;
import Projet.Metier.Niveau;

public class MenuDefiEdit extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	private Controleur ctrl;
	private Fenetre fenetre;
	
	private JRadioButton edite;

	private JPanel listeEdite;
	
	public MenuDefiEdit(Controleur ctrl, Fenetre fenetre)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.fenetre = fenetre;
		this.ctrl = ctrl;

		this.listeEdite = initialisePanel(this.ctrl.getNiveauEdite(), "Edite");




		JPanel difficulte = new JPanel();
		difficulte.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Niveaux",0,0,new Font("Dialog", 1, 12),Color.BLACK
		));

		edite = new JRadioButton("Défis édité");
		edite.addActionListener(this);
		edite.setMnemonic(KeyEvent.VK_NUMPAD1);
		edite.setSelected(true);
		difficulte.add(edite);

		this.add(difficulte, BorderLayout.NORTH);

		this.add(listeEdite);

		ButtonGroup group = new ButtonGroup();
		group.add(edite);
	}
	
	public JPanel initialisePanel(ArrayList<Niveau> alNiveau, String s) 
	{
		JPanel tmp = new JPanel(new GridLayout(0, 5,10,10));
		tmp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				s,0,0,new Font("Dialog", 1, 12),Color.BLACK
		));
		for ( int j = 0; j < alNiveau.size(); j++) {
			JButton buttonTmp = new JButton(alNiveau.get(j).getNumNiveau()+"");
			buttonTmp.setName(s);
			buttonTmp.addActionListener(this);
            buttonTmp.setSize(55, 26);
			buttonTmp.setBackground(Color.WHITE);
			tmp.add(buttonTmp);
		}
		return tmp;
	}

	public void actionPerformed(ActionEvent e) 
	{

		this.fenetre.pack();
		
		if ( e.getSource() instanceof JButton)
		{
			JButton tmp = (JButton) e.getSource();
			
			int num = Integer.parseInt(tmp.getText());
			
			this.ctrl.setNiveau(new Niveau(num));
			
        	this.setVisible(false);
        	Jeu j = new Jeu(this.ctrl, this.fenetre);
        	this.fenetre.setJeu(j);
            this.fenetre.add(j, BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Jeu");
            this.fenetre.pack();
            this.fenetre.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.fenetre.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.fenetre.getHeight() / 2));
		}
	}

}
