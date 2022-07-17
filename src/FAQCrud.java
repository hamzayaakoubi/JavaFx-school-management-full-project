/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.jfoenix.controls.JFXListView;
import Entites.faq;
import DataBase.MyConnection;
import Entites.categorieFAQ;
import Entites.categorieProposees;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author lazre
 */
public class FAQCrud {

    public void ajouterFAQ(faq f) {
        String requete = "INSERT INTO faq(question,reponse,categorie)" + "VALUES ('" + f.getQuestion() + "','" + f.getReponse() + "','" + f.getCategorie() + "')";
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }

    public void ajouterCategorieProposees(categorieProposees c) {
        String requete = "INSERT INTO categorieproposees(nom,upvotes)" + "VALUES ('" + c.getNom() + "','" + 0 + "')";
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterCategorie(categorieFAQ c, int id) {
        String requete = "INSERT INTO categoriefaq(nom)" + "VALUES ('" + c.getNom() + "')";
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String requete3 = "DELETE FROM categorieproposees where id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierFAQ(faq u, int id) {

        String requete2 = "UPDATE  faq SET question=?,reponse=?,categorie=? where id =?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, u.getQuestion());
            pst.setString(2, u.getReponse());
            pst.setString(3, u.getCategorie().getNom());
            pst.setInt(4, id);
            pst.executeUpdate();

            System.out.println("FAQ modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void AddViewFAQ(faq u, int id) {

        String requete2 = "UPDATE  faq SET vues=? where id =?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setInt(1, u.getViews() + 1);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void UpvoteCategorie(int id) {

        String requete2 = "UPDATE  categorieproposees SET upvotes=upvotes +1  where id =?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerFAQ(int id) {
        String requete3 = "DELETE FROM faq where id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("FAQ supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<faq> afficherFAQ() {

        List<faq> myList = new ArrayList();

        String requete4 = "SELECT * FROM faq";

        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                faq f = new faq();
                f.setId(rs.getInt("id"));
                f.setQuestion(rs.getString(2));
                f.setReponse(rs.getString(3));
                categorieFAQ t = new categorieFAQ(rs.getString(4));
                f.setCategorie(t);
                f.setViews(rs.getInt("Vues"));
                myList.add(f);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    public List<categorieProposees> afficherCategorie() {

        List<categorieProposees> myList = new ArrayList();

        String requete4 = "SELECT * FROM categorieproposees";

        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                categorieProposees c = new categorieProposees();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString(2));
                c.setUpvotes(rs.getInt(3));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    public ObservableList<PieChart.Data> Stats() {
        String requete = "SELECT Categorie,COUNT(*) FROM `faq` GROUP BY Categorie";
        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(requete);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            while (rs.next()) {
                pieChartData.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }

            return pieChartData;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public List<categorieFAQ> afficherCategorieFAQ() {
        List<categorieFAQ> myList = new ArrayList();
        String requete4 = "SELECT nom FROM categoriefaq";
        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                categorieFAQ cf = new categorieFAQ();
                cf.setNom(rs.getString(1));
                myList.add(cf);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    public List<faq> TopFAQs() {
        List<faq> myList = new ArrayList();

        String requete4 = "SELECT * FROM faq order by vues desc";

        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                faq f = new faq();
                f.setId(rs.getInt("id"));
                f.setQuestion(rs.getString(2));
                f.setReponse(rs.getString(3));
                categorieFAQ t = new categorieFAQ(rs.getString(4));
                f.setCategorie(t);
                f.setViews(rs.getInt("Vues"));
                myList.add(f);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

}
