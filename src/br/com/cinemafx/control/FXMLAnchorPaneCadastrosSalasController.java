package br.com.cinemafx.control;

import br.com.cinemafx.bean.Sala;
import br.com.cinemafx.dao.SalaDAO;
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

public class FXMLAnchorPaneCadastrosSalasController implements Initializable {

    @FXML
    private TableView<Sala> tableViewSalas;
    @FXML
    private TableColumn<Sala, String> tableColumnSalaTipoImagem;
    @FXML
    private TableColumn<Sala, String> tableColumnSalaLotacao;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Label labelSalaNumero;
    @FXML
    private Label labelSalaTipoImagem;
    @FXML
    private Label labelSalaLotacao;
    
    private List<Sala> listSalas;
    private ObservableList<Sala> observableListSalas;
    
    private final SalaDAO salaDAO = new SalaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewSala();
        
        tableViewSalas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewSalas(newValue));
    }    
    
    public void carregarTableViewSala(){
        tableColumnSalaTipoImagem.setCellValueFactory(new PropertyValueFactory<>("tipoImagem"));
        tableColumnSalaLotacao.setCellValueFactory(new PropertyValueFactory<>("qtdMax"));
        
        listSalas = salaDAO.select();
        
        observableListSalas = FXCollections.observableArrayList(listSalas);
        tableViewSalas.setItems(observableListSalas);
    }
    
    public void selecionarItemTableViewSalas(Sala s){
        if(s != null){
            labelSalaNumero.setText(String.valueOf(s.getIdSala()));
            labelSalaTipoImagem.setText(s.getTipoImagem());
            labelSalaLotacao.setText(String.valueOf(s.getQtdMax()));
        }else{
            labelSalaNumero.setText("");
            labelSalaTipoImagem.setText("");
            labelSalaLotacao.setText("");
        }
    }
}
