package br.com.cinemafx.control;

import br.com.cinemafx.bean.Sessao;
import br.com.cinemafx.dao.SessaoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLAnchorPaneCadastrosSessoesController implements Initializable {
    
    @FXML
    private TableView<Sessao> tableViewSessoes;
    @FXML
    private TableColumn<Sessao, String> tableColumnSessaoNumeroSala;
    @FXML
    private TableColumn<Sessao, String> tableColumnSessaoIdFilme;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
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
        tableColumnSessaoNumeroSala.setCellValueFactory(new PropertyValueFactory<>("idSala"));
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
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Sessao s = new Sessao();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosSessoesDialog(s);
        if(buttonConfirmarClicked){
            sessaoDAO.insert(s);
            carregarTableViewSessao();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException {
        Sessao s = tableViewSessoes.getSelectionModel().getSelectedItem();
        if(s != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosSessoesDialog(s);
            if(buttonConfirmarClicked){
                sessaoDAO.update(s);
                carregarTableViewSessao();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma sessao na Tabela");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException {
        Sessao s = tableViewSessoes.getSelectionModel().getSelectedItem();
        if(s != null){
            sessaoDAO.delete(s);
            carregarTableViewSessao();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma sessao na Tabela");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosSessoesDialog(Sessao s) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosSessoesDialogController.class.getResource("/br/com/cinemafx/view/FXMLAnchorPaneCadastrosSessoesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //Criando o Stage de Dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Sessoes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o cliente no Control
        FXMLAnchorPaneCadastrosSessoesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setSessao(s);
        
        //Mostra o Dialog e espera ate que o user feche
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }
}
