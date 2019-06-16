package br.com.cinemafx.control;

import br.com.cinemafx.bean.Sala;
import br.com.cinemafx.dao.SalaDAO;
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
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Sala s = new Sala();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosSalasDialog(s);
        if(buttonConfirmarClicked){
            salaDAO.insert(s);
            carregarTableViewSala();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException {
        Sala s = tableViewSalas.getSelectionModel().getSelectedItem();
        if(s != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosSalasDialog(s);
            if(buttonConfirmarClicked){
                salaDAO.update(s);
                carregarTableViewSala();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma sala na Tabela");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException {
        Sala s = tableViewSalas.getSelectionModel().getSelectedItem();
        if(s != null){
            salaDAO.delete(s);
            carregarTableViewSala();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma sala na Tabela");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosSalasDialog(Sala s) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosSalasDialogController.class.getResource("/br/com/cinemafx/view/FXMLAnchorPaneCadastrosSalasDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //Criando o Stage de Dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Salas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o cliente no Control
        FXMLAnchorPaneCadastrosSalasDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setSala(s);
        
        //Mostra o Dialog e espera ate que o user feche
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }
}
