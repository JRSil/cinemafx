
package br.com.cinemafx.control;

import br.com.cinemafx.bean.Ingresso;
import br.com.cinemafx.bean.Sessao;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLAnchorPaneIngressosDialogController implements Initializable {
    
    @FXML
    private TextField textFieldIngressoSessao;
    @FXML
    private TextField textFieldIngressoQtd;
    @FXML
    private TextField textFieldIngressoFormaPgmt;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Ingresso ingresso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
        this.textFieldIngressoSessao.setText(String.valueOf(ingresso.getIdSessao()));
        this.textFieldIngressoQtd.setText(String.valueOf(ingresso.getQuantidade()));
        this.textFieldIngressoFormaPgmt.setText(String.valueOf(ingresso.getFormaPgmt()));
    }
    
    @FXML
    public void handleButtonConfirmar(){
        if(validarEntradaDeDados()){
            ingresso.setIdSessao(Integer.valueOf(textFieldIngressoSessao.getText()));
            ingresso.setQuantidade(Integer.valueOf(textFieldIngressoQtd.getText()));
            ingresso.setFormaPgmt(String.valueOf(textFieldIngressoFormaPgmt.getText()));

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
    private boolean validarEntradaDeDados(){
        String errorMessage = "";
        
        if(textFieldIngressoSessao.getText() == null || textFieldIngressoSessao.getText().length() == 0){
            errorMessage += "Numero da sessão invalido!\n";
        }
        if(textFieldIngressoQtd.getText() == null || textFieldIngressoQtd.getText().length() == 0){
            errorMessage += "Quantidade invalida!\n";
        }
        if(textFieldIngressoFormaPgmt.getText() == null || textFieldIngressoFormaPgmt.getText().length() == 0){
            errorMessage += "Forma de pagamento invalida!\n";
        }
        
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no venda");
            alert.setHeaderText("Campos invalidos, por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
