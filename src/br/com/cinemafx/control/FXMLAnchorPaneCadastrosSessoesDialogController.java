package br.com.cinemafx.control;

import br.com.cinemafx.bean.Sessao;
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

public class FXMLAnchorPaneCadastrosSessoesDialogController implements Initializable {

    @FXML
    private MaskedTextField textFieldSessoesNumeroSala;
    @FXML
    private MaskedTextField textFieldSessoesDia;
    @FXML
    private MaskedTextField textFieldSessoesHora;
    @FXML
    private TextField textFieldSessoesDublagem;
    @FXML
    private MaskedTextField textFieldSessoesCodigoFilme;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Sessao sessao;
    
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

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
        this.textFieldSessoesNumeroSala.setText(String.valueOf(sessao.getIdSala()));
        this.textFieldSessoesDia.setText(String.valueOf(sessao.getDia()));
        this.textFieldSessoesHora.setText(String.valueOf(sessao.getHora()));
        this.textFieldSessoesDublagem.setText(String.valueOf(sessao.isDublagem()));
        this.textFieldSessoesCodigoFilme.setText(String.valueOf(sessao.getIdFilme()));
    }
    
    @FXML
    public void handleButtonConfirmar(){
        if(validarEntradaDeDados()){
            sessao.setIdSala(Integer.valueOf(textFieldSessoesNumeroSala.getText()));
            sessao.setDia(Date.valueOf(textFieldSessoesDia.getText()));
            sessao.setHora(textFieldSessoesHora.getText());
            sessao.setDublagem(Boolean.valueOf(textFieldSessoesDublagem.getText()));
            sessao.setIdFilme(Integer.valueOf(textFieldSessoesCodigoFilme.getText()));

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
        
        if(textFieldSessoesNumeroSala.getText() == null || textFieldSessoesNumeroSala.getText().length() == 0){
            errorMessage += "Numero da sala invalido!\n";
        }
        if(textFieldSessoesDia.getText() == null || textFieldSessoesDia.getText().length() == 0){
            errorMessage += "Dia invalido!\n";
        }
        if(textFieldSessoesHora.getText() == null || textFieldSessoesHora.getText().length() == 0){
            errorMessage += "Hora invalida!\n";
        }
        if(textFieldSessoesDublagem.getText() == null || textFieldSessoesDublagem.getText().length() == 0){
            errorMessage += "Informe se ha dublagem!\n";
        }
        if(textFieldSessoesCodigoFilme.getText() == null || textFieldSessoesCodigoFilme.getText().length() == 0){
            errorMessage += "Codigo Filme invalido!\n";
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
