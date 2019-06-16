
package br.com.cinemafx.control;

import br.com.cinemafx.bean.Filme;
import br.com.cinemafx.bean.Ingresso;
import br.com.cinemafx.dao.IngressoDAO;
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
    
    private final IngressoDAO ingressoDAO = new IngressoDAO();
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewIngresso();
        
        //Listen acionado diante de quaisquer alteracoes na selecao de itens do TableView
        tableViewIngresso.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewIngresso(newValue));
    }
    
    public void carregarTableViewIngresso(){
        tableColumnIngressoCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tableColumnIngressoSessao.setCellValueFactory(new PropertyValueFactory<>("sessao"));
        tableColumnIngressoTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        listIngresso = ingressoDAO.select();
        
        observableListIngresso = FXCollections.observableArrayList(listIngresso);
        tableViewIngresso.setItems(observableListIngresso);
    }
    
     public void selecionarItemTableViewIngresso(Ingresso i){
        if(i != null){
            labelIngressoCodigo.setText(String.valueOf(i.getIdIngresso()));
            labelIngressoSessao.setText(String.valueOf(i.getIdSessao()));
            labelIngressoQuantidade.setText(String.valueOf(i.getQuantidade()));
            labelIngressoTotal.setText(String.valueOf(i.getTotal()));
            labelIngressoFormaPgmt.setText(String.valueOf(i.getFormaPgmt()));
        }else{
            labelIngressoCodigo.setText("");
            labelIngressoSessao.setText("");
            labelIngressoQuantidade.setText("");
            labelIngressoTotal.setText("");
            labelIngressoFormaPgmt.setText("");
        }
    }
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Ingresso i = new Ingresso();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosIngressoDialog(i);
        if(buttonConfirmarClicked){
            ingressoDAO.insert(i);
            carregarTableViewIngresso();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException {
        Ingresso i = tableViewIngresso.getSelectionModel().getSelectedItem();
        if(i != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosIngressoDialog(i);
            if(buttonConfirmarClicked){
                ingressoDAO.update(i);
                carregarTableViewIngresso();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma venda na Tabela");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException {
        Ingresso i = tableViewIngresso.getSelectionModel().getSelectedItem();
        if(i != null){
            ingressoDAO.delete(i);
            carregarTableViewIngresso();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma venda na Tabela");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosIngressoDialog(Ingresso i) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneIngressosDialogController.class.getResource("/br/com/cinemafx/view/FXMLAnchorPaneIngressosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //Criando o Stage de Dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Ingressos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o cliente no Control
        FXMLAnchorPaneIngressosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setIngresso(i);
        
        //Mostra o Dialog e espera ate que o user feche
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }
    
}