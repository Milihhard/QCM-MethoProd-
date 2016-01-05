/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import user.Utilisateur;

/**
 *
 * @author Axel
 */
public class AddUser extends JDialog implements ActionListener {

    private final JLabel utilisateur, mdp, confirmMdp, nom, prenom;
    private final JTextField t_utilisateur, t_mdp, t_confirmMdp, t_nom, t_prenom;
    private final JButton create;
    private final JPanel pano;
    private final JRadioButton radioEtu, radioProf;
    private final ButtonGroup groupeRadio;

    private JLabel error;

    public AddUser(QCMFrame owner) {
        super(owner, true);
        this.setTitle("Connexion");
        pano = new JPanel();

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        utilisateur = new JLabel("Nom d'utilisateur :");
        t_utilisateur = new JTextField();
        mdp = new JLabel("Mot de passe :");
        t_mdp = new JTextField();
        confirmMdp = new JLabel("Confirmation du mot de passe :");
        t_confirmMdp = new JTextField();
        nom = new JLabel("Nom :");
        t_nom = new JTextField();
        prenom = new JLabel("Prénom :");
        t_prenom = new JTextField();
        create = new JButton("Creation de l'utilisateur");
        error = new JLabel();
        radioEtu = new JRadioButton("Etudiant");
        radioProf = new JRadioButton("Enseignant");
        groupeRadio = new ButtonGroup();
        groupeRadio.add(radioEtu);
        groupeRadio.add(radioProf);
        radioEtu.setSelected(true);

        GridBagConstraints contraintes = new GridBagConstraints();
        init();
        this.pack();
        create.addActionListener(this);
    }

    private void init() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        this.add(utilisateur, contrainte);
        contrainte.gridy++;
        this.add(t_utilisateur, contrainte);
        contrainte.gridy++;
        this.add(mdp, contrainte);
        contrainte.gridy++;
        this.add(t_mdp, contrainte);
        contrainte.gridy++;
        this.add(confirmMdp, contrainte);
        contrainte.gridy++;
        this.add(t_confirmMdp, contrainte);
        contrainte.gridy++;
        this.add(prenom, contrainte);
        contrainte.gridy++;
        this.add(t_prenom, contrainte);
        contrainte.gridy++;
        this.add(nom, contrainte);
        contrainte.gridy++;
        this.add(t_nom, contrainte);
        contrainte.gridy++;
        contrainte.gridwidth = 2;
        this.add(create, contrainte);
        contrainte.gridx += 2;
        contrainte.gridwidth = 1;
        this.add(error, contrainte);
        contrainte.gridy = 4;
        contrainte.gridx--;
        this.add(radioEtu, contrainte);
        contrainte.gridy++;
        this.add(radioProf, contrainte);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == create) {
            if (!t_utilisateur.getText().equals("") && !t_mdp.getText().equals("") && !t_confirmMdp.getText().equals("") && !t_nom.getText().equals("") && !t_prenom.getText().equals("")) {
                if (!t_utilisateur.getText().equals("42")) {
                    if (t_mdp.getText().equals(t_confirmMdp.getText())) {
                        try {
                            Integer.parseInt(t_utilisateur.getText());
                            if (!SQL.isUserExist(Integer.parseInt(t_utilisateur.getText()))) {
                                createUser();
                            } else {
                                changeError(0);
                            }
                        } catch (NumberFormatException ex) {
                            changeError(2);
                        }
                    } else {
                        changeError(3);
                    }
                } else {
                    changeError(0);
                }
            } else {
                changeError(1);
            }
        }
    }

    public void createUser() {
        Utilisateur.typeUser type;
        if (radioEtu.isSelected()) {
            type = Utilisateur.typeUser.Etudiant;
        } else {
            type = Utilisateur.typeUser.Enseignant;
        }
        SQL.addUser(Integer.parseInt(t_utilisateur.getText()), t_mdp.getText(), t_prenom.getText(), t_nom.getText(), type);
        this.setVisible(false);
    }

    public boolean showDialog() {
        this.setVisible(true);
        //System.out.println("id: " + user.getId());
        return true;
    }

    public void changeError(int flag) {
        switch (flag) {
            case 0:
                error.setText("Utilisateur déjà utilisé!");
                break;
            case 1:
                error.setText("Remplissez tous les champs!");
                break;
            case 2:
                error.setText("L'id de l'utilisateur doit être un entier!");
                break;
            case 3:
                error.setText("Les mots de passes doivent être les mêmes!");
                break;
            default:
                error.setText("Erreur inconnue!");
        }
        this.pack();
    }
}
