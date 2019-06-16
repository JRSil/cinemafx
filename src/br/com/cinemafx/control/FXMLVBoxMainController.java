package br.com.cinemafx.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemCadastrosFilmes;
    @FXML
    private MenuItem menuItemCadastrosSessoes;
    @FXML
    private MenuItem menuItemCadastrosAtendentes;
    @FXML
    private MenuItem menuItemCadastrosSalas;
    @FXML
    private MenuItem menuItemVendasIngressos;
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleMenuItemCadastrosFilmes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/cinemafx/view/FXMLAnchorPaneCadastrosFilmes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemCadastrosSessoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/cinemafx/view/FXMLAnchorPaneCadastrosSessoes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemCadastrosSalas() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/cinemafx/view/FXMLAnchorPaneCadastrosSalas.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemCadastrosAtendentes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/cinemafx/view/FXMLAnchorPaneCadastrosAtendentes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}
