package br.com.cinemafx.control;

import br.com.cinemafx.bean.Filme;
import br.com.cinemafx.bean.Sessao;
import br.com.cinemafx.dao.SessaoDAO;
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

public class FXMLAnchorPaneCadastrosSessoesController implements Initializable {
    
    @FXML
    private TableView<Sessao> tableViewSessoes;
    @FXML
    private TableColumn<Filme, String> tableColumnSessaoIdSessao;
    @FXML
    private TableColumn<Filme, String> tableColumnSessaoIdFilme;
    @FXML
    private Button buttonSessaoInserir;
    @FXML
    private Button buttonSessaoAlterar;
    @FXML
    private Button buttonSessaoRemover;
    @FXML
    private Label labelSessaoCodigoSessao;
    @FXML
    private Label labelSessaoNumeroSala;
    @FXML
    private Label labelSessaoDia;
    @FXML
    private Label labelSessaoHora;
    @FXML
    private Label labelSessaoDublagem;
    @FXML
    private Label labelSessaoCodigoFilme;

    private List<Sessao> listSessoes;
    private ObservableList<Sessao> observableListSessoes;
    
    private final SessaoDAO sessaoDAO = new SessaoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewSessao();
        
        tableViewSessoes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewSessoes(newValue));
    }    
    
    public void carregarTableViewSessao(){
        tableColumnSessaoIdSessao.setCellValueFactory(new PropertyValueFactory<>("idSessao"));
        tableColumnSessaoIdFilme.setCellValueFactory(new PropertyValueFactory<>("idFilme"));
        
        listSessoes = sessaoDAO.select();
        
        observableListSessoes = FXCollections.observableArrayList(listSessoes);
        tableViewSessoes.setItems(observableListSessoes);
    }
    
    public void selecionarItemTableViewSessoes(Sessao s){
        if(s != null){
            labelSessaoCodigoSessao.setText(String.valueOf(s.getIdSessao()));
            labelSessaoNumeroSala.setText(String.valueOf(s.getIdSala()));
            labelSessaoDia.setText(String.valueOf(s.getDia()));
            labelSessaoHora.setText(String.valueOf(s.getHora()));
            labelSessaoDublagem.setText(String.valueOf(s.isDublagem()));
            labelSessaoCodigoFilme.setText(String.valueOf(s.getIdFilme()));
        }else{
            labelSessaoCodigoSessao.setText("");
            labelSessaoNumeroSala.setText("");
            labelSessaoDia.setText("");
            labelSessaoHora.setText("");
            labelSessaoDublagem.setText("");
            labelSessaoCodigoFilme.setText("");
        }
    }
    
}
