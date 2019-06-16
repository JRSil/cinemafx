
package br.com.cinemafx.control;

import br.com.cinemafx.bean.Ingresso;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class FXMLAnchorPaneIngressosController implements Initializable {

    @FXML
    private TableView<Ingresso> tableViewIngresso;
    @FXML
    private TableColumn<Ingresso, String> tableColumnIngressoCodigo;
    @FXML
    private TableColumn<Ingresso, String> tableColumnIngressoSessao;
    @FXML
    private TableColumn<Ingresso, String> tableColumnIngressoTotal;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Label labelIngressoCodigo;
    @FXML
    private Label labelIngressoSessao;
    @FXML
    private Label labelIngressoQuantidade;
    @FXML
    private Label labelIngressoTotal;
    @FXML
    private Label labelIngressoFormaPgmt;
    
    private List<Ingresso> listIngresso;
    private ObservableList<Ingresso> observableListIngresso;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
