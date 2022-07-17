/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class AddSalleFXMLController implements Initializable {

	@FXML
	private Pane mainpane;
	@FXML
	private TextField num;
	@FXML
	private Button add;
	@FXML
	private Button add1;
	@FXML
	private Button add2;
	@FXML
	private TableView<?> table;
	@FXML
	private TableColumn<?, ?> idC;
	@FXML
	private TableColumn<?, ?> blocC;
	@FXML
	private TableColumn<?, ?> numC;
	@FXML
	private ComboBox<?> bloc;
	@FXML
	private TextField id;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void add(ActionEvent event) {
	}

	@FXML
	private void delete(ActionEvent event) {
	}

	@FXML
	private void update(ActionEvent event) {
	}

	@FXML
	private void getSalleId(ActionEvent event) {
	}

}
