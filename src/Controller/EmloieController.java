/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;

import Utils.MyDBcon;
import Entites.EmploiTemps;
import Entites.Evenement;
import Entites.User;
import Services.MatiereService;
import Services.TempService;
import Services.UserService;
import Utils.Database2;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author rabia
 */
public class EmloieController implements Initializable {

    /**
     * Initializes the controller class.
     */
        private ObservableList<EmploiModel> listEmplois;

    @FXML
    private TableView<EmploiModel> emploi;
    @FXML
    private TableColumn<EmploiModel, Timestamp> dateDebut;
    @FXML
    private TableColumn<EmploiModel, Timestamp> DateFin;
    @FXML
    private TableColumn<EmploiModel, String> matiere;

    @FXML
    private TableColumn<EmploiModel, String> classe;
    @FXML
    private TableColumn<EmploiModel, String> salle;
    @FXML
    private TableColumn<EmploiModel, Integer> id;
    @FXML
    private TableColumn<EmploiModel, Integer> idEnseignant;
    @FXML
    private TableColumn<EmploiModel, Integer> idClasse;
    @FXML
    private TableColumn<EmploiModel, Integer> idSalle;
    ObservableList<EmploiModel> list;
    ObservableList<EmploiTemps>listt=FXCollections.observableArrayList();
    public ObservableList<EmploiModel> data;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         try {
//           
//            TempService ts = new TempService();
//             int id=LoginController.test;
//          User p = new User();
//         UserService us =new UserService();
////         
//            if(LoginController.user.getRole().equals("enseignant"))
//            for (EmploiTemps emp : ts.getTempsByEns(LoginController.user.getId())) {
//                EmploiModel empMo = new EmploiModel();
//    
//           TempService tss=new TempService();

        matiere.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("idMatiere"));
        salle.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("salle"));
        classe.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("idClasse"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateDebut"));
        DateFin.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateFin"));
       // id.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("id"));
       // idClasse.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idClasse"));
        //idEnseignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idEnseignant"));
       // idSalle.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idSalle"));
//       list= tss.getTempsBy(LoginController.user.getId());
//emploi.setItems(list);
       }
        public void populate() {
        String sql = "SELECT * FROM emploi_temps ;";
        Connection con = Database2.connect();
        listt.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
//                list.add(new EmploiModel(rs.getInt("idMatiere"), rs.getTimestamp("dateDebut"),rs.getTimestamp("dateFin"),rs.getString("salle"),rs.getInt("idClasse")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        emploi.setItems(list);
    }
   @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
   
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\rabia\\Documents\\NetBeansProjects\\Esprit12\\src\\PDF\\emploieProf.pdf"));
   
       document.open();
       PdfPTable table=new PdfPTable(6);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("id_Emploie");
       table.addCell("Matiere");
       table.addCell("Date Debut");
              table.addCell("Date Fin");
                     table.addCell("Salle");
                            table.addCell("Classe");
                                 
    
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Emploie de temps");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:/Users/rabia/Documents/NetBeansProjects/Esprit12/src/Image/SchoolInfo.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecole", "root", "");
       Statement st=con.createStatement();
  
       ResultSet rs=st.executeQuery("SELECT * FROM `emploi_temps` ");
    
       while(rs.next()){
            TempService ts = new TempService();
       
            for (EmploiTemps emp1 : ts.getTempsByEns(LoginController.user.getId())) {
       table.addCell(rs.getString("id"));
            table.addCell(rs.getString("idMatiere"));

                   table.addCell(rs.getString("dateDebut"));
table.addCell(rs.getString("dateFin"));
table.addCell(rs.getString("salle"));
table.addCell(rs.getString("idClasse"));

            }
       document.add(table);
        JOptionPane.showMessageDialog(
                null, " données exportées en pdf avec succés ");
               document.close();
           
            
            }
        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        } }
}

