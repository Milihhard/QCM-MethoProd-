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
    //etudiant
    ArrayList<QCMEtudiant> listeQCMetudiant;

    public class QCMEtudiant {

        JPanel pan;
        JLabel title, note;
        JButton enter;

        public QCMEtudiant(final QCM qcm) {
            pan = new JPanel();
            pan.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            title = new JLabel(qcm.getTitle());
            if (qcm.getNote() == -1) {
                enter = new JButton("Remplir le QCM");
            } else {
                note = new JLabel("note : " + Float.toString(qcm.getNote()));
            }
            GridBagConstraints contrainte = new GridBagConstraints();
            pan.setLayout(new GridBagLayout());
            contrainte.gridx = 0;
            contrainte.gridy = 0;
            contrainte.insets = new Insets(10, 5, 5, 10);
            pan.add(title, contrainte);
            contrainte.insets = new Insets(10, 5, 20, 5);
            contrainte.gridy++;
            if (qcm.getNote() == -1) {
                pan.add(enter, contrainte);
                enter.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        RemplirQCM rqcm = new RemplirQCM(qcm.getID());
                    }
                });
            } else {
                pan.add(note, contrainte);
            }
        }

    }

    public class QCMProf {

        JPanel pan;
        JLabel title;
        JButton voir;
        JButton edit;

        public QCMProf(final QCM qcm) {
            pan = new JPanel();
            pan.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            title = new JLabel(qcm.getTitle());
            voir = new JButton("Voir/Modifier");
            edit = new JButton("Supprimer");
            GridBagConstraints contraintes = new GridBagConstraints();
            pan.setLayout(new GridBagLayout());
            contraintes.gridx = 0;
            contraintes.gridy = 0;
            pan.add(title, contraintes);
            contraintes.gridy++;
            pan.add(voir, contraintes);
            contraintes.gridy++;
            pan.add(edit, contraintes);

            edit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SQL.suppQCM(qcm.getID());
                    init(user);
                }
            });
        }

    }

    public QCMFrame() {
        
        this.setTitle("QCM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Connexion b = new Connexion(this);
        user = b.showDialog();
        init(user);
    }
    private void init(Utilisateur user){
        pano = new JPanel();

        welcome = new JLabel("niah");
        welcome.setVisible(false);
        //"Bonjour " + user.getPrenom() + " " + user.getNom() + "(" + user.getId() + ")");

        GridBagConstraints contrainte = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        if (user != null) {
            initGlobal(contrainte, pano);
            switch (user.getType()) {
                case Admin:
                    initAdmin(contrainte, pano);
                    break;
                case Enseignant:
                    initEnseignant(contrainte, pano);
                    break;
                case Etudiant:
                    initEtudiant(contrainte, pano);
                    break;
            }
        } else {
            this.setVisible(false);
            this.dispose();
        }
        this.setContentPane(pano);
        this.pack();
    }

    private void initGlobal(GridBagConstraints contrainte, JPanel pano) {
        welcome = new JLabel("Bonjour " + user.getPrenom() + " " + user.getNom() + "(" + user.getId() + ")");
        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        pano.add(welcome, contrainte);
    }

    private void initAdmin(GridBagConstraints contrainte, JPanel pano) {
        ajoutAdmin = new JButton("Ajouter un utilisateur");
        signalAdmin = new JLabel[1];
        signalAdmin[0] = new JLabel();
        contrainte.gridx = 0;
        contrainte.gridy = 1;
        pano.add(ajoutAdmin, contrainte);
        contrainte.gridx++;
        pano.add(signalAdmin[0], contrainte);

        ajoutAdmin.addActionListener(this);
    }

    private void initEnseignant(GridBagConstraints contrainte, JPanel pano) {

        listeQCMprof = new ArrayList();
        ArrayList<QCM> qcms = SQL.recherchQCMbyProfId(user.getId());
        for (QCM qcm : qcms) {
            listeQCMprof.add(new QCMProf(qcm));
        }
        contrainte.gridy++;
        contrainte.insets = new Insets(10, 5, 5, 5);
        contrainte.insets = new Insets(0, 0, 0, 0);
        creer = new JButton("Créer un QCM");
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        pano.add(creer, contrainte);
        contrainte.gridy++;
        contrainte.gridx = -1;
        for (int i = 0; i < listeQCMprof.size(); i++) {
            if (contrainte.gridx < 5) {
                contrainte.gridx++;
            } else {
                contrainte.gridx = 0;
                contrainte.gridy++;
            }
            pano.add(listeQCMprof.get(i).pan, contrainte);

        }
        contrainte.insets = new Insets(10, 5, 5, 5);

    }
    private void initEtudiant(GridBagConstraints contrainte, JPanel pano) {

        listeQCMetudiant = new ArrayList();
        ArrayList<QCM> qcms = SQL.recherchQCMbyUserId(user.getId());
        for (QCM qcm : qcms) {
            listeQCMetudiant.add(new QCMEtudiant(qcm));
        }
        /*
         listeQCMetudiant.add(new QCMEtudiant("niah", (float) 5));
         listeQCMetudiant.add(new QCMEtudiant("ouais", null));
         listeQCMetudiant.add(new QCMEtudiant("pouet", (float) 10.5));
         */
        //contrainte.anchor = GridBagConstraints.BOTH;
        contrainte.gridy++;
        contrainte.insets = new Insets(10, 5, 5, 5);
        contrainte.gridx = -1;
        contrainte.gridy++;
        for (int i = 0; i < listeQCMetudiant.size(); i++) {
            if (contrainte.gridx < 5) {
                contrainte.gridx++;
            } else {
                contrainte.gridx = 0;
                contrainte.gridy++;
            }
            pano.add(listeQCMetudiant.get(i).pan, contrainte);

        }
        /*
         this.add(listeQCMetudiant.get(0).pan, contrainte);
         contrainte.gridx++;
         this.add(listeQCMetudiant.get(1).pan, contrainte);
         contrainte.gridx++;
         this.add(listeQCMetudiant.get(2).pan, contrainte);
         */
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
