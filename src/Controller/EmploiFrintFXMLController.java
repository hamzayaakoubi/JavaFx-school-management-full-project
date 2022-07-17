/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;    
import Utils.MyDBcon;
import Entites.EmploiTemps;
import Services.MatiereService;
import Services.TempService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmploiFrintFXMLController implements Initializable {
      @FXML
    private ListView<EmploiModel> emplois;
    public ObservableList<EmploiModel> data;
          // private ObservableList<EmploiModel> listEmplois;
    ObservableList<EmploiModel> list;
    ObservableList<EmploiTemps>listt=FXCollections.observableArrayList();
   
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
 
            
            ArrayList<EmploiModel> list = new ArrayList<>();
            TempService ts = new TempService();
            //es2lou houwa 9olou kifeh najem nekhedh el user connécté ok
            // w ely y9olik 3liha hotha fi blaset LoginFXMLController.user
            if(LoginController.user.getRole().equals("enseignant"))
            for (EmploiTemps emp : ts.getTempsByEns(LoginController.user.getId())) {
                EmploiModel empMo = new EmploiModel();
                empMo.setClasse(emp.getIdClasse());
                empMo.setDateDebut(emp.getDateDebut());
                empMo.setDateFin(emp.getDateFin());
                empMo.setEnsiegnant(emp.getIdEnseignant());
                empMo.setMatiere(new MatiereService().getById(emp.getIdMatiere()).getNom());
                empMo.setSalle(emp.getSalle());
                empMo.setId(emp.getId());
                empMo.setIdClasse(emp.getIdClasse());
                empMo.setIdSalle(emp.getSalle());
                empMo.setIdEnseignant(emp.getIdEnseignant());
                empMo.setIdMatiere(emp.getIdMatiere());
                list.add(empMo);
            }
            //w badelha lena zeda  ok
            
            //ok zid fil user idClasse w fil classe user java w fi service ygeti zeda idClasse ok w fi login ysoubou fi session el user bel idClasse ok
             if(LoginController.user.getRole().equals("etudiant"))
            for (EmploiTemps emp : ts.getTempsByClasse(LoginController.user.getIdClasse())) {
                EmploiModel empMo = new EmploiModel();
                empMo.setClasse(emp.getIdClasse());
                empMo.setDateDebut(emp.getDateDebut());
                empMo.setDateFin(emp.getDateFin());
                empMo.setEnsiegnant(emp.getIdEnseignant());
                empMo.setMatiere(new MatiereService().getById(emp.getIdMatiere()).getNom());
                empMo.setSalle(emp.getSalle());
                empMo.setId(emp.getId());
                empMo.setIdClasse(emp.getIdClasse());
                empMo.setIdSalle(emp.getSalle());
                empMo.setIdEnseignant(emp.getIdEnseignant());
                empMo.setIdMatiere(emp.getIdMatiere());
                list.add(empMo);
            }
            
            //  System.out.println(Integer.parseInt(sujetid.getText()));
            emplois.setCellFactory(new Callback<ListView<EmploiModel>, ListCell<EmploiModel>>() {

                @Override
                public ListCell<EmploiModel> call(ListView<EmploiModel> param) {
                    return new Poules();
                }
            } );
            
            data = FXCollections.observableArrayList(list);
            
            emplois.setItems(data);
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(EmploiFrintFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmploiFrintFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
            
          

    @FXML
    private void CreatePdf(ActionEvent event) {
    }
     public class Poules extends ListCell<EmploiModel> { // liste view test7a9 class  t'extendi mel listCell 

        private final GridPane gridPane = new GridPane();
        private final ImageView CoeurIcon = new ImageView();
        private final Label noteVote = new Label();
        private final Label username = new Label();
        private final ImageView cup = new ImageView();
        private final Label descriptionLabel = new Label();
        private final ImageView Coeur = new ImageView();
        private final AnchorPane content = new AnchorPane();
        Label date = new Label();

        public Poules() { // 
            Coeur.setFitWidth(75);
            Coeur.setPreserveRatio(true);
            GridPane.setConstraints(Coeur, 0, 0, 1, 3);//positionnement mta el COEUR <3
            GridPane.setValignment(Coeur, VPos.TOP);
          
            date.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;");
             GridPane.setConstraints(date, 1, 2); // positionnement mta date

            GridPane.setValignment(date, VPos.CENTER);//positionnement mta el date
            username.setStyle("-fx-font-weight: bold; -fx-font-size: 1.2em;");
            GridPane.setConstraints(username, 0, 1); // positionnement mta el USERNAME
            noteVote.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;");
            GridPane.setConstraints(noteVote, 1, 0); //positionnement Text note vote
            CoeurIcon.setFitWidth(22);
            CoeurIcon.setPreserveRatio(true);
            GridPane.setConstraints(CoeurIcon, 3, 0); //positionnement mta el coeur <3
            GridPane.setValignment(CoeurIcon, VPos.CENTER);//positionnement mta el coeur <3

            File file = new File("C:\\xampp\\htdocs\\image\\cup.png");
            Image image = new Image(file.toURI().toString());
            cup.setImage(image);
            cup.setFitWidth(15);
            cup.setFitHeight(15);
            descriptionLabel.setStyle("-fx-opacity: 0.75;"); //commentaire text
            descriptionLabel.setGraphic(cup); //commentaire text
            GridPane.setConstraints(descriptionLabel, 1, 1); //commentaire text
            GridPane.setColumnSpan(descriptionLabel, Integer.MAX_VALUE); //commentaire text

            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
            gridPane.setHgap(6);
            gridPane.setVgap(6);
            gridPane.getChildren().setAll(Coeur, username, noteVote, CoeurIcon, descriptionLabel, date);
            AnchorPane.setTopAnchor(gridPane, 0d);
            AnchorPane.setLeftAnchor(gridPane, 0d);
            AnchorPane.setBottomAnchor(gridPane, 0d);
            AnchorPane.setRightAnchor(gridPane, 0d);

            content.getChildren().add(gridPane); //content hia el anchor pane get children nekhthou beha les objet tena lkol w baed khtarna specifiquement el gridpane besh nhotouha
        }
        @Override
      protected void updateItem(EmploiModel item, boolean empty) { //pour updater el hajet ( reponses ) eli fost el list view mtei

            super.updateItem(item, empty);
            setGraphic(null);
            setText(null);
            setContentDisplay(ContentDisplay.LEFT);

            if (!empty && item != null) {
                noteVote.setText("Classe: "+item.getClasse());
                username.setText(item.getMatiere());
                
                date.setText("Enseignant: " + item.getEnsiegnant());
              
                descriptionLabel.setText("Date Debut: "+item.getDateDebut().toString()+"  Date Fin : "+item.getDateFin().toString());
                setText(null);
                setGraphic(content);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                
		
	}
            }
          // private ObservableList<EmploiModel> listEmplois;

   /* @FXML
    private TableView<EmploiModel> emploi;
    @FXML
    private TableColumn<EmploiModel, Timestamp> dateDebut;
    @FXML
    private TableColumn<EmploiModel, Timestamp> DateFin;
    @FXML
    private TableColumn<EmploiModel, Integer> matiere;

    @FXML
    private TableColumn<EmploiModel, Integer> classe;
    @FXML
    private TableColumn<EmploiModel, Integer> salle;
    @FXML
    private TableColumn<EmploiModel, Integer> idEnseignant;
    ObservableList<EmploiModel>listt=FXCollections.observableArrayList();*/
   
 


    /**
     * Initializes the controller class.
     */
    
    
    
   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       try {
           TempService ts = new TempService();
           if(LoginFXMLController.user.getRole().equals("etudiant"))
             for (EmploiTemps emp : ts.getTempsByClasse(LoginFXMLController.user.getId())) {
               EmploiModel empMo = new EmploiModel();
           
           // TempService tss=new TempService();
           matiere.setCellValueFactory(new PropertyValueFactory<EmploiModel,Integer>("idMatiere"));
           salle.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("salle"));
           classe.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idClasse"));
           //dateDebut.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateDebut"));
           //DateFin.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateFin"));
           // id.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("id"));
           // idClasse.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idClasse"));
           idEnseignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idEnseignant"));
           // idSalle.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idSalle"));
           emploi.setItems(listt);
             } } catch (SQLException ex) {
           Logger.getLogger(EmploiFrintFXMLController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(EmploiFrintFXMLController.class.getName()).log(Level.SEVERE, null, ex);
       }


            }*/
    
    
    
    
    
    
    
    
    
    
    
        

           @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
   
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\rabia\\Documents\\NetBeansProjects\\Esprit12\\src\\PDF\\emploie.pdf"));
   
       document.open();
       PdfPTable table=new PdfPTable(7);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("id_Emploie");
       table.addCell("Matiere");
       table.addCell("Date Debut");
              table.addCell("Date Fin");
                     table.addCell("Salle");
                            table.addCell("Classe");
                                   table.addCell("Enseignant");
    
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Emploie de temps");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:/Users/rabia/Documents/NetBeansProjects/Esprit12/src/Image/SchoolInfo.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/symfony", "root", "");
       Statement st=con.createStatement();
  
       ResultSet rs=st.executeQuery("SELECT * FROM `emploi_temps`  ");
    
       while(rs.next()){
            TempService ts = new TempService();
       
            for (EmploiTemps emp1 : ts.getTempsByClasse(LoginController.user.getId())) {
       table.addCell(rs.getString("id"));
            table.addCell(rs.getString("idMatiere"));

                   table.addCell(rs.getString("dateDebut"));
table.addCell(rs.getString("dateFin"));
table.addCell(rs.getString("salle"));
table.addCell(rs.getString("idClasse"));
table.addCell(rs.getString("idEnseignant"));
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
        } } }}
     

            
    
    