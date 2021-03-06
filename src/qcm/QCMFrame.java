/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import user.Utilisateur;

/**
 *
 * @author Emilien
 */
public class QCMFrame extends JFrame implements ActionListener {

    //global
    
    private JLabel welcome;
    Utilisateur user;
    JPanel pano;
    JButton deconnexion;

    //admin
    JButton ajoutAdmin;
    JLabel[] signalAdmin;

    //enseignant
    ArrayList<QCMProf> listeQCMprof;
    JButton creer;
    JTextField nomqcm;
    //etudiant
    ArrayList<QCMEtudiant> listeQCMetudiant;


    public class QCMEtudiant{
        
        JPanel pan;
        JLabel title;
        JButton enter;

        public QCMEtudiant(String str){
            pan = new JPanel();
            pan.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            title = new JLabel(str);
            enter = new JButton("Remplir le QCM");
            GridBagConstraints contrainte = new GridBagConstraints();
            pan.setLayout(new GridBagLayout());
            contrainte.gridx = 0;
            contrainte.gridy = 0;
            contrainte.insets = new Insets(10, 5, 5, 10);
            pan.add(title, contrainte);
            contrainte.insets = new Insets(10, 5, 20, 5);
            contrainte.gridy++;
            pan.add(enter, contrainte);
            enter.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    RemplirQCM Formulaire = new RemplirQCM(2);
                    Formulaire.setVisible(true);
                }
            });
        }
        
        
    }
    public class QCMProf{
        JPanel pan;
        JLabel title;
        JButton voir;
        JButton edit;

        public QCMProf(String str){
            pan = new JPanel();
            pan.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
            title = new JLabel(str);
            voir = new JButton("Voir le QCM");
            edit = new JButton("Modifier/Supprimer");
            GridBagConstraints contraintes = new GridBagConstraints();
            pan.setLayout(new GridBagLayout());
            contraintes.gridx = 0;
            contraintes.gridy = 0;
            pan.add(title, contraintes);
            contraintes.gridy++;
            pan.add(voir, contraintes);
            contraintes.gridy++;
            pan.add(edit, contraintes);
        }

            
    }
    public QCMFrame() {
        this.setTitle("QCM");
        pano = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcome = new JLabel("niah");
        welcome.setVisible(false);
        //"Bonjour " + user.getPrenom() + " " + user.getNom() + "(" + user.getId() + ")");

        GridBagConstraints contrainte = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        Connexion b = new Connexion(this);
        user = b.showDialog();
        if (user != null) {
            initGlobal(contrainte);
            switch (user.getType()) {
                case Admin:
                    initAdmin(contrainte);
                    break;
                case Enseignant:
                    initEnseignant(contrainte);
                    break;
                case Etudiant:
                    initEtudiant(contrainte);
                    break;
            }
        } else {
            this.setVisible(false);
            this.dispose();
        }
        this.pack();
    }

    private void initGlobal(GridBagConstraints contrainte) {
        welcome = new JLabel("Bonjour " + user.getPrenom() + " " + user.getNom() + "(" + user.getId() + ")");
        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        this.add(welcome, contrainte);
    }

    private void initAdmin(GridBagConstraints contrainte) {
        ajoutAdmin = new JButton("Ajouter un utilisateur");
        signalAdmin = new JLabel[1];
        signalAdmin[0] = new JLabel();
        contrainte.gridx = 0;
        contrainte.gridy = 1;
        this.add(ajoutAdmin, contrainte);
        contrainte.gridx++;
        this.add(signalAdmin[0], contrainte);

        ajoutAdmin.addActionListener(this);
    }

    private void initEnseignant(GridBagConstraints contrainte) {
        listeQCMprof = new ArrayList();
        listeQCMprof.add(new QCMProf("Rouflaquettes"));
        listeQCMprof.add(new QCMProf("Raclette"));
        contrainte.insets = new Insets(10, 5, 5, 5);
        for(int i = 0; i < listeQCMprof.size(); i++){
            contrainte.gridx++;
            this.add(listeQCMprof.get(i).pan,contrainte);
        }
        contrainte.insets = new Insets(0, 0, 0, 0);
        creer = new JButton("Créer un QCM");
        contrainte.gridx = 1;
        contrainte.gridy = 1;
        nomqcm = new JTextField();
        this.add(nomqcm, contrainte);
        contrainte.gridx++;
        this.add(creer, contrainte);
    }

    private void initEtudiant(GridBagConstraints contrainte) {

        listeQCMetudiant = new ArrayList();
        listeQCMetudiant.add(new QCMEtudiant("niah"));
        listeQCMetudiant.add(new QCMEtudiant("ouais"));
        listeQCMetudiant.add(new QCMEtudiant("pouet"));

        contrainte.gridy++;
        contrainte.insets = new Insets(10, 5, 5, 5);
        this.add(listeQCMetudiant.get(0).pan, contrainte);
        contrainte.gridx++;
        this.add(listeQCMetudiant.get(1).pan, contrainte);
        contrainte.gridx++;
        this.add(listeQCMetudiant.get(2).pan, contrainte);
        contrainte.insets = new Insets(0, 0, 0, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ajoutAdmin) {
            AddUser au = new AddUser(this);
            boolean b = au.showDialog();
            if (b) {
                hideSignal();
                signalAdmin[0].setText("New User!");
                this.pack();
            }
        }
    }

    public void hideSignal() {
        for (JLabel j : signalAdmin) {
            j.setText("");
        }
    }
}
 