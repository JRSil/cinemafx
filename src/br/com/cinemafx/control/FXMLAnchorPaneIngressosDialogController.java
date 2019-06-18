
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
    private TextField textFieldIngressosSessao;
    @FXML
    private TextField textFieldIngressosQtd;
    @FXML
    private TextField textFieldIngressosFormaPgmt;
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
        this.textFieldIngressosSessao.setText(String.valueOf(ingresso.getIdSessao()));
        this.textFieldIngressosQtd.setText(String.valueOf(ingresso.getQuantidade()));
        this.textFieldIngressosFormaPgmt.setText(String.valueOf(ingresso.getFormaPgmt()));
    }
    
    @FXML
    public void handleButtonConfirmar(){
        if(validarEntradaDeDados()){
            ingresso.setIdSessao(Integer.valueOf(textFieldIngressosSessao.getText()));
            ingresso.setQuantidade(Integer.valueOf(textFieldIngressosQtd.getText()));
            ingresso.setFormaPgmt(String.valueOf(textFieldIngressosFormaPgmt.getText()));

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
        
        if(textFieldIngressosSessao.getText() == null || textFieldIngressosSessao.getText().length() == 0){
            errorMessage += "Numero da sessão invalido!\n";
        }
        if(textFieldIngressosQtd.getText() == null || textFieldIngressosQtd.getText().length() == 0){
            errorMessage += "Quantidade invalida!\n";
        }
        if(textFieldIngressosFormaPgmt.getText() == null || textFieldIngressosFormaPgmt.getText().length() == 0){
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
