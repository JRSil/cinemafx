package br.com.cinemafx.control;

import br.com.cinemafx.bean.Atendente;
import br.com.cinemafx.dao.AtendenteDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLAnchorPaneCadastrosAtendentesController implements Initializable {

    @FXML
    private TableView<Atendente> tableViewAtendentes;
    @FXML
    private TableColumn<Atendente, String> tableColumnAtendenteNome;
    @FXML
    private TableColumn<Atendente, String> tableColumnAtendenteCPF;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Label labelAtendenteCodigo;
    @FXML
    private Label labelAtendenteNome;
    @FXML
    private Label labelAtendenteCPF;
    @FXML
    private Label labelAtendenteDataNasc;
    @FXML
    private Label labelAtendenteEmail;
    @FXML
    private Label labelAtendenteUser;
    @FXML
    private Label labelAtendenteSenha;
    @FXML
    private Label labelAtendenteTipo;
    
    private List<Atendente> listAtendentes;
    private ObservableList<Atendente> observableListAtendentes;
    
    private final AtendenteDAO atendenteDAO = new AtendenteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewAtendente();
        
        tableViewAtendentes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewAtendentes(newValue));
    }    
    
    public void carregarTableViewAtendente(){
        tableColumnAtendenteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnAtendenteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listAtendentes = atendenteDAO.select();
        
        observableListAtendentes = FXCollections.observableArrayList(listAtendentes);
        tableViewAtendentes.setItems(observableListAtendentes);
    }
    
    public void selecionarItemTableViewAtendentes(Atendente a){
        if(a != null){
            labelAtendenteCodigo.setText(String.valueOf(a.getIdAtendente()));
            labelAtendenteNome.setText(a.getNome());
            labelAtendenteCPF.setText(a.getCPF());
            labelAtendenteDataNasc.setText(String.valueOf(a.getNasc()));
            labelAtendenteEmail.setText(a.getEmail());
            labelAtendenteUser.setText(a.getUser());
            labelAtendenteSenha.setText(a.getSenha());
            labelAtendenteTipo.setText(a.getTipo());
        }else{
            labelAtendenteCodigo.setText("");
            labelAtendenteNome.setText("");
            labelAtendenteCPF.setText("");
            labelAtendenteDataNasc.setText("");
            labelAtendenteEmail.setText("");
            labelAtendenteUser.setText("");
            labelAtendenteSenha.setText("");
            labelAtendenteTipo.setText("");
        }
    }
}
