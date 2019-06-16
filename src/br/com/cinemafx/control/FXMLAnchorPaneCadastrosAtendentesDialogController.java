package br.com.cinemafx.control;

import br.com.cinemafx.bean.Atendente;
import br.com.cinemafx.util.MaskedTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLAnchorPaneCadastrosAtendentesDialogController implements Initializable {

    @FXML
    private TextField textFieldAtendenteNome;
    @FXML
    private MaskedTextField textFieldAtendenteCPF;
    @FXML
    private MaskedTextField textFieldAtendenteDataNasc;
    @FXML
    private TextField textFieldAtendenteEmail;
    @FXML
    private TextField textFieldAtendenteUser;
    @FXML
    private TextField textFieldAtendenteSenha;
    @FXML
    private TextField textFieldAtendenteTipo;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Atendente a;
    
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

    public Atendente getAtendente() {
        return a;
    }

    public void setAtendente(Atendente a) {
        this.a = a;
    }
    
    @FXML
    public void handleButtonConfirmar(){
        if(validarEntradaDeDados()){
            a.setNome(textFieldAtendenteNome.getText());
            a.setCPF(textFieldAtendenteCPF.getText());
            a.setNasc(Date.valueOf(textFieldAtendenteDataNasc.getText()));
            a.setEmail(textFieldAtendenteEmail.getText());
            a.setUser(textFieldAtendenteUser.getText());
            a.setSenha(textFieldAtendenteSenha.getText());
            a.setTipo(textFieldAtendenteTipo.getText());

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
        
        if(textFieldAtendenteNome.getText() == null || textFieldAtendenteNome.getText().length() == 0){
            errorMessage += "Nome invalido!\n";
        }
        if(textFieldAtendenteCPF.getText() == null || textFieldAtendenteCPF.getText().length() == 0){
            errorMessage += "CPF invalido!\n";
        }
        if(textFieldAtendenteDataNasc.getText() == null || textFieldAtendenteDataNasc.getText().length() == 0){
            errorMessage += "Data invalida!\n";
        }
        if(textFieldAtendenteEmail.getText() == null || textFieldAtendenteEmail.getText().length() == 0){
            errorMessage += "Email invalido!\n";
        }
        if(textFieldAtendenteUser.getText() == null || textFieldAtendenteUser.getText().length() == 0){
            errorMessage += "User invalido!\n";
        }
        if(textFieldAtendenteSenha.getText() == null || textFieldAtendenteSenha.getText().length() == 0){
            errorMessage += "Senha invalida!\n";
        }
        if(textFieldAtendenteTipo.getText() == null || textFieldAtendenteTipo.getText().length() == 0){
            errorMessage += "Tipo de usuario invalido!\n";
        }
        
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos invalidos, por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
