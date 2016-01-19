/*
 *
 * @author Emilien
 */
package qcm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user.Utilisateur;

/*
 *Table : Enseignant                                       	
 *
 *  identifiantEns   	int not null
 *  prenom           	varchar(254)
 *  nom              	varchar(254)
 *  mdp                          varchar(254)
 *  primary key (identifiantEns)
 */

/*
 *Table : Etudiant                                         	
 *
 *  identifiant      	int not null
 *  prenom           	varchar(254)
 *  nom              	varchar(254)
 *  mdpetu                     varchar(254)
 *  primary key (identifiant)
 */

/*
 *Table : Qcm                                              	
 *
 *  id               	int not null
 *  identifiantEns   	int not null
 *  titre            	varchar(254)
 *  primary key (id)
 */

/*
 * Table : Question                                         	
 *
 *  id               	int not null
 *  idQ              	int not null
 *  point            	int
 *  primary key (id, idQ)
 */

/*
 * Table : Reponse                                          	
 *
 *  id               	int not null
 *  idQ              	int not null
 *  idR              	int not null
 *  coche            	boolean
 *  juste            	boolean
 *  primary key (id, idQ, idR)
 */

/*
 * Table : EtuQcm                                     	
 *
 *  identifiant      	int not null
 *  id               	int not null
 *  primary key (identifiant, id)
 */
public class SQL {

    private static Connection cnx;

    private static Statement lien;

    public SQL(String file) {
        try {
            // TODO code application logic here

            Class.forName("org.sqlite.JDBC").newInstance();
            System.out.println("chargement driver");
            cnx = DriverManager.getConnection("jdbc:sqlite:" + file + ".sqlite");
            System.out.println("sql bien chargé");
            lien = cnx.createStatement();
            System.out.println("lien crée");

        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            System.out.println("bug");
        }
    }

    public static void ChargerSQL(String file) {

        try {
            // TODO code application logic here

            Class.forName("org.sqlite.JDBC").newInstance();
            System.out.println("chargement driver");
            cnx = DriverManager.getConnection("jdbc:sqlite:" + file);
            System.out.println("sql bien chargé");
            lien = cnx.createStatement();
            System.out.println("lien crée");

        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            System.out.println("bug");

        }

    }

    public static ArrayList getListQuest(int idQCM) {
        ArrayList listQuest = new ArrayList();

        try {

            ResultSet rs = lien.executeQuery("Select * from Question where id like \"" + idQCM + "\"");
            while (rs.next()) {
                Question quest = new Question(rs.getInt("idQ"), rs.getInt("id"), rs.getString("question"));
                listQuest.add(quest);
            }
            if (rs.getRow() != 0) {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mauvais SQL");
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            return null;
        }
        return listQuest;
    }

    public static ArrayList getListRep(int idQuest) {
        ArrayList listRep = new ArrayList();

        try {

            ResultSet rs = lien.executeQuery("Select * from Reponse where idQ like \"" + idQuest + "\"");
            while (rs.next()) {
                Reponse rep = new Reponse(rs.getInt("idR"), rs.getInt("idQ"), rs.getString("Reponse"));
                listRep.add(rep);
            }
            if (rs.getRow() != 0) {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mauvais SQL");
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            return null;
        }
        return listRep;
    }

    public static ResultSet giveSQL(String commande) {
        try {
            return (lien.executeQuery(commande));

        } catch (SQLException e) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erreur : " + e);
            return null;
        }

    }

    public static Utilisateur rechercheUtilisateur(int id, String mdp) {

        try {
            ResultSet rs = lien.executeQuery("Select * from Etudiant where identifiant like \"" + id + "\" AND mdpetu like \"" + mdp + "\"");
            rs.next();
            if (rs.getRow() != 0) {
                return new Utilisateur(id, rs.getString("prenom"), rs.getString("nom"), Utilisateur.typeUser.Etudiant);
            } else {
                rs = lien.executeQuery("Select * from Enseignant where identifiantEns like \"" + id + "\" AND mdp like \"" + mdp + "\"");
                rs.next();
                if (rs.getRow() != 0) {
                    return new Utilisateur(id, rs.getString("prenom"), rs.getString("nom"), Utilisateur.typeUser.Enseignant);
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mauvais SQL");
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            return null;
        }
    }

    public static boolean isUserExist(int id) {
        try {
            ResultSet rs = lien.executeQuery("Select * from Etudiant where identifiant like \"" + id + "\"");
            ResultSet rs2 = lien.executeQuery("Select * from Enseignant where identifiantEns like \"" + id + "\"");

            rs.next();
            rs2.next();
            return (rs.getRow() != 0 || rs2.getRow() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mauvais SQL");
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            return true;
        }
    }

    public static void addUser(int id, String mdp, String nom, String prenom, Utilisateur.typeUser type) {
        try {
            lien = cnx.createStatement();
            String requete;
            if (type == Utilisateur.typeUser.Etudiant) {
                requete = "Insert INTO Etudiant (identifiant, prenom, nom, mdpetu) Values (\"" + id + "\",\"" + prenom + "\",\"" + nom + "\",\"" + mdp + "\")";
            } else {
                requete = "Insert INTO Enseignant (identifiantEns, prenom, nom, mdp) Values (\"" + id + "\",\"" + prenom + "\",\"" + nom + "\",\"" + mdp + "\")";

            }
            System.out.println(requete);
            lien.executeQuery(requete);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
        }
    }

    public static String addQCM(int identifiantP, String titre) {
        try {
            lien = cnx.createStatement();
            String requete = "Insert INTO QCM (identifiantEns, titre) Values (\"" + identifiantP + "\",\"" + titre + "\")";
            lien.executeQuery(requete);
            return lien.executeQuery("Select id From QCM WHERE identifiantEns = " + identifiantP).toString();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
        }
        return "N0N";
    }

    public static ArrayList<QCM> recherchQCMbyUserId(int idUser) {
        ArrayList<QCM> retour = new ArrayList();
        try {
            ResultSet rs = lien.executeQuery("SELECT titre, eq.id, note FROM EtuQcm eq INNER JOIN Qcm q ON eq.id = q.id where identifiant = " + idUser);
            while (rs.next()) {
                retour.add(new QCM(rs.getString("titre"), rs.getInt("id"), rs.getFloat("note")));
            }
            return retour;
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mauvais SQL");
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            return null;

        }
    }

    public static ArrayList<QCM> recherchQCMbyProfId(int idUser) {
        ArrayList<QCM> retour = new ArrayList();
        try {
            ResultSet rs = lien.executeQuery("SELECT titre, id FROM Qcm q where identifiantEns = " + idUser);
            while (rs.next()) {
                retour.add(new QCM(rs.getString("titre"), rs.getInt("id"), 0));
            }
            return retour;
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mauvais SQL");
            JOptionPane.showMessageDialog(null, "Erreur : " + ex);
            return null;

        }
    }

    public static void suppQuestion(int id) {
        try {
            suppReponse(id);
            lien = cnx.createStatement();
            String requete = "DELETE FROM Question WHERE id =  " + id + "";
            lien.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void suppReponse(int id) {
        try {
            lien = cnx.createStatement();
            String requete = "DELETE FROM Reponse WHERE id =  " + id + "";
            lien.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void suppQCM(int id) {
        try {
            suppQuestion(id);
            lien = cnx.createStatement();
            String requete = "DELETE FROM Qcm WHERE id =  " + id + "";
            lien.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
