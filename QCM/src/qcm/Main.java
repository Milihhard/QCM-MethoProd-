/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emilien
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            Connection c = DriverManager.getConnection("jdbc:sqlite:src\\res\\QCM.sqlite");
            Statement st = c.createStatement();
            ResultSet rs;
            //rs = st.executeQuery("INSERT INTO Etudiant(identifiant, prenom, nom, mdpetu) VALUES (1,'Emilien','Nicolas','test');");
            //rs = st.executeQuery("INSERT INTO Etudiant(identifiant, prenom, nom, mdpetu) VALUES (2,'Axel','Turlier','test');");
            //rs = st.executeQuery("INSERT INTO Enseignant(identifiantEns, prenom, nom, mdp) VALUES (3,'Axxxxxxel','Tuturlier','test');");

            //rs = st.executeQuery("DELETE FROM etudiant WHERE prenom like 'Emilien';");
            rs = st.executeQuery("SELECT * FROM Etudiant");
            int i = 0;

            while (rs.next()) {
                System.out.println(rs.getString("prenom"));
                i++;
            }
            rs = st.executeQuery("SELECT * FROM Enseignant");
            i = 0;

            while (rs.next()) {
                System.out.println(rs.getString("prenom"));
                i++;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Mauvaise connection");
        }
        System.out.println("test");
        
        SQL.ChargerSQL("src\\res\\QCM.sqlite");
        
        QCMFrame c = new QCMFrame();
        c.setVisible(true);
    }

}
